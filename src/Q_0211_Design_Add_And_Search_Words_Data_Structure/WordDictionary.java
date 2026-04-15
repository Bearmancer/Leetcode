package Q_0211_Design_Add_And_Search_Words_Data_Structure;

/**
 * <h2><a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">LeetCode #211: Design Add and Search Words Data Structure</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Tries</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/design-add-and-search-words-data-structure">neetcode.io/problems/design-add-and-search-words-data-structure</a></p>
 *
 * <h3>Problem</h3>
 * <p>Design a data structure that supports adding new words and finding if a string matches any previously added string.</p>
 * <p>Implement the <code>WordDictionary</code> class:</p>
 * <ul>
 * <li><code>WordDictionary()</code> Initializes the object.</li>
 * <li><code>void addWord(word)</code> Adds <code>word</code> to the data structure, it can be matched later.</li>
 * <li><code>bool search(word)</code> Returns <code>true</code> if there is any string in the data structure that matches <code>word</code> or <code>false</code> otherwise. <code>word</code> may contain dots <code>'.'</code> where dots can be matched with any letter.</li>
 * </ul>
 * <p> </p>
 * <p><strong class="example">Example:</strong></p>
 * <pre>
 * <strong>Input</strong>
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * <strong>Output</strong>
 * [null,null,null,null,false,true,true,true]
 *
 * <strong>Explanation</strong>
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= word.length &lt;= 25</code></li>
 * <li><code>word</code> in <code>addWord</code> consists of lowercase English letters.</li>
 * <li><code>word</code> in <code>search</code> consist of <code>'.'</code> or lowercase English letters.</li>
 * <li>There will be at most <code>2</code> dots in <code>word</code> for <code>search</code> queries.</li>
 * <li>At most <code>10<sup>4</sup></code> calls will be made to <code>addWord</code> and <code>search</code>.</li>
 * </ul>
 */

class WordDictionary {

    public WordDictionary() {
        
    }
    
    public void addWord(String word) {
        
    }
    
    public boolean search(String word) {
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
