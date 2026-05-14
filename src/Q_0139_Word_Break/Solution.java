package Q_0139_Word_Break;

import java.util.List;

/**
 * <h1><a href="https://leetcode.com/problems/word-break/">LeetCode #139: Word Break</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> 1-D Dynamic Programming</p>
 *
 * <h2>Problem</h2>
 * <p>Given a string <code>s</code> and a dictionary of strings <code>wordDict</code>, return <code>true</code> if <code>s</code> can be segmented into a space-separated sequence of one or more dictionary words.</p>
 * <p><strong>Note</strong> that the same word in the dictionary may be reused multiple times in the segmentation.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> s = "leetcode", wordDict = ["leet","code"]
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> Return true because "leetcode" can be segmented as "leet code".
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> s = "applepenapple", wordDict = ["apple","pen"]
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * <strong>Output:</strong> false
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 300</code></li>
 * <li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
 * <li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li>
 * <li><code>s</code> and <code>wordDict[i]</code> consist of only lowercase English letters.</li>
 * <li>All the strings of <code>wordDict</code> are <strong>unique</strong>.</li>
 * </ul>
 */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }
}
