#!/usr/bin/env dotnet-script
// ============================================================
// LeetCode Problem Scraper — Standalone C# Script
// ============================================================
// Usage: dotnet script LeetCodeScraper.csx
// Requires: dotnet tool install -g dotnet-script
//
// CONFIGURATION: Set the question index(es) below.
// ============================================================

#r "nuget: Microsoft.Playwright, 1.59.0"

using Microsoft.Playwright;
using System.Text;
using System.Text.Json;
using System.Text.Json.Nodes;
using System.Text.RegularExpressions;

// ---- INLINE CONFIG ----
// Set the LeetCode problem indices to scrape
var questionIndices = new[] { 1, 48, 53 };
// -----------------------

string repoRoot = FindRepoRoot();
string srcDir = Path.Combine(repoRoot, "src");

Console.WriteLine($"Repo root: {repoRoot}");
Console.WriteLine($"Questions to process: [{string.Join(", ", questionIndices)}]");
Console.WriteLine();

// Scan existing directories
Console.WriteLine("=== Scanning existing directories ===");
var existingDirs = new Dictionary<int, string>();
if (Directory.Exists(srcDir))
{
    foreach (var dir in Directory.GetDirectories(srcDir))
    {
        var dirName = Path.GetFileName(dir);
        var match = Regex.Match(dirName, @"^Q_(\d{4})_(.+)$");
        if (match.Success)
        {
            int idx = int.Parse(match.Groups[1].Value);
            string slug = match.Groups[2].Value.ToLower().Replace("_", "-").Replace(" ", "-");
            existingDirs[idx] = slug;
        }
    }
}
Console.WriteLine($"Found {existingDirs.Count} existing problem directories.");

var toProcess = questionIndices
    .Where(i => existingDirs.ContainsKey(i))
    .Select(i => new { Index = i, Slug = existingDirs[i] })
    .ToList();

Console.WriteLine($"Questions to process: {toProcess.Count}");
Console.WriteLine();

// Launch Playwright (headed mode for Cloudflare bypass reliability)
Console.WriteLine("=== Launching Playwright browser (headed) ===");
var playwright = await Playwright.CreateAsync();
var browser = await playwright.Chromium.LaunchAsync(new() { Headless = false });
var context = await browser.NewContextAsync(new()
{
    UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"
});
var page = await context.NewPageAsync();

Console.WriteLine("Navigating to leetcode.com to establish session...");
await page.GotoAsync("https://leetcode.com");
await page.WaitForLoadStateAsync(LoadState.NetworkIdle);
Console.WriteLine("Session established.");
Console.WriteLine();

// Process each question
foreach (var q in toProcess)
{
    Console.WriteLine($"=== Processing Q_{q.Index:D4} ({q.Slug}) ===");
    try
    {
        var query = new
        {
            query = @"query questionData($titleSlug: String!) {
                question(titleSlug: $titleSlug) {
                    questionId, title, titleSlug, difficulty, content,
                    topicTags { name }, exampleTestcases, sampleTestCase, metaData
                }
            }",
            variables = new { titleSlug = q.Slug },
            operationName = "questionData"
        };

        var jsonPayload = JsonSerializer.Serialize(query);
        Console.WriteLine($"  Querying GraphQL...");

        var result = await page.EvaluateAsync<JsonObject>(
            $"fetch('https://leetcode.com/graphql', {{ method: 'POST', headers: {{ 'Content-Type': 'application/json' }}, body: JSON.stringify({jsonPayload}) }}).then(r => r.json())"
        );

        var question = result?["data"]?["question"];
        if (question == null)
        {
            Console.WriteLine($"  No data returned for '{q.Slug}'");
            continue;
        }

        string title = question["title"]?.GetValue<string>() ?? q.Slug;
        string difficulty = question["difficulty"]?.GetValue<string>() ?? "Unknown";
        string content = question["content"]?.GetValue<string>() ?? "";

        var tags = new List<string>();
        if (question["topicTags"] is JsonArray tagArray)
            foreach (var tag in tagArray)
                if (tag?["name"] != null) tags.Add(tag["name"]!.GetValue<string>());
        string category = string.Join(", ", tags);

        // Generate Javadoc
        string javadoc = GenerateJavadoc(q.Index, q.Slug, title, difficulty, category, content);

        // Update Solution.java
        string dirName = $"Q_{q.Index:D4}_{ToPascalCase(q.Slug)}";
        string solutionPath = Path.Combine(srcDir, dirName, "Solution.java");

        if (!File.Exists(solutionPath))
        {
            Console.WriteLine($"  File not found: {solutionPath}");
            continue;
        }

        string fileContent = File.ReadAllText(solutionPath);
        var javadocMatch = Regex.Match(fileContent, @"^(/\*\*[\s\S]*?\*/)");
        if (javadocMatch.Success)
        {
            fileContent = javadocMatch.Result(javadoc);
        }
        else
        {
            var classMatch = Regex.Match(fileContent, @"(?<=package .*?;\s*\n)(\s*)(?=class\s)");
            if (classMatch.Success)
                fileContent = fileContent.Insert(classMatch.Index, javadoc + "\n");
            else
            {
                Console.WriteLine($"  Could not find class declaration in {solutionPath}");
                continue;
            }
        }
        File.WriteAllText(solutionPath, fileContent);
        Console.WriteLine($"  ✓ Updated {solutionPath}");
    }
    catch (Exception ex)
    {
        Console.WriteLine($"  ✗ FAILED: {ex.Message}");
    }
    Console.WriteLine();
}

// Cleanup
await browser.CloseAsync();
playwright.Dispose();
Console.WriteLine("=== Done ===");

// ============================================================
// Helper: Generate LeetCode-only Javadoc HTML
// ============================================================
string GenerateJavadoc(int index, string slug, string title, string difficulty, string category, string contentHtml)
{
    var sb = new StringBuilder();
    sb.AppendLine("/**");
    sb.AppendLine($" * <h1><a href=\"https://leetcode.com/problems/{slug}/\">LeetCode #{index}: {title}</a></h1>");
    sb.AppendLine($" * <p><b>Difficulty:</b> {difficulty} &nbsp;|&nbsp; <b>Category:</b> {category}</p>");
    sb.AppendLine(" *");
    sb.AppendLine(" * <h2>Problem</h2>");

    if (!string.IsNullOrEmpty(contentHtml))
    {
        string cleaned = CleanContent(contentHtml);
        foreach (var line in cleaned.Split('\n'))
        {
            if (string.IsNullOrWhiteSpace(line))
                sb.AppendLine(" * <p>&nbsp;</p>");
            else
                sb.AppendLine($" * {line.TrimEnd()}");
        }
    }
    sb.AppendLine(" */");
    return sb.ToString();
}

string CleanContent(string html)
{
    if (string.IsNullOrEmpty(html)) return "";
    html = Regex.Replace(html, @"<script[^>]*>[\s\S]*?</script>", "", RegexOptions.IgnoreCase);
    html = Regex.Replace(html, @"<style[^>]*>[\s\S]*?</style>", "", RegexOptions.IgnoreCase);
    html = Regex.Replace(html, @"\s+", " ");
    html = Regex.Replace(html, @"</?p[^>]*>", "\n", RegexOptions.IgnoreCase);
    html = Regex.Replace(html, @"<br\s*/?>", "\n", RegexOptions.IgnoreCase);
    return html.Trim();
}

string ToPascalCase(string slug)
{
    if (string.IsNullOrEmpty(slug)) return "";
    return string.Join("_", slug.Split('-')
        .Select(part => char.ToUpper(part[0]) + part.Substring(1)));
}

string FindRepoRoot()
{
    var dir = new DirectoryInfo(Directory.GetCurrentDirectory());
    while (dir != null)
    {
        if (File.Exists(Path.Combine(dir.FullName, "AGENTS.md")))
            return dir.FullName;
        dir = dir.Parent;
    }
    return Directory.GetCurrentDirectory();
}
