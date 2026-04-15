<#
.SYNOPSIS
    Delete all generated Python artifacts from leetcode-extractor/.
.DESCRIPTION
    Removes __pycache__/, .pytest_cache/, build/, dist/, and *.pyc files.
    Run from any directory — paths are resolved relative to the repo root.
.EXAMPLE
    .\Clear-Artifacts.ps1
    .\Clear-Artifacts.ps1 -WhatIf
#>
[CmdletBinding(SupportsShouldProcess)]
param()

$RepoRoot = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$ExtractorRoot = Join-Path $RepoRoot "leetcode-extractor"

if (-not (Test-Path $ExtractorRoot)) {
    Write-Error "leetcode-extractor/ not found at: $ExtractorRoot"
    exit 1
}

$Patterns = @(
    "__pycache__"
    ".pytest_cache"
    "build"
    "dist"
    "*.egg-info"
)

$Removed = 0

foreach ($Pattern in $Patterns) {
    Get-ChildItem -Path $ExtractorRoot -Recurse -Force -Filter $Pattern -ErrorAction SilentlyContinue |
        ForEach-Object {
            if ($PSCmdlet.ShouldProcess($_.FullName, "Remove")) {
                Remove-Item $_.FullName -Recurse -Force
                Write-Host "Removed: $($_.FullName)"
                $Removed++
            }
        }
}

# Remove stray .pyc files not caught by __pycache__ sweep
Get-ChildItem -Path $ExtractorRoot -Recurse -Force -Filter "*.pyc" -ErrorAction SilentlyContinue |
    ForEach-Object {
        if ($PSCmdlet.ShouldProcess($_.FullName, "Remove")) {
            Remove-Item $_.FullName -Force
            Write-Host "Removed: $($_.FullName)"
            $Removed++
        }
    }

Write-Host "`nDone. $Removed item(s) removed."
