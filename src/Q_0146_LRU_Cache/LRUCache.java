package Q_0146_LRU_Cache;

/**
 * <h2><a href="https://leetcode.com/problems/lru-cache/">LeetCode #146: LRU Cache</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Linked List</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/lru-cache">neetcode.io/problems/lru-cache</a></p>
 *
 * <h3>Problem</h3>
 * <p>Design a data structure that follows the constraints of a <strong><a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a></strong>.</p>
 * <p>Implement the <code>LRUCache</code> class:</p>
 * <ul>
 * <li><code>LRUCache(int capacity)</code> Initialize the LRU cache with <strong>positive</strong> size <code>capacity</code>.</li>
 * <li><code>int get(int key)</code> Return the value of the <code>key</code> if the key exists, otherwise return <code>-1</code>.</li>
 * <li><code>void put(int key, int value)</code> Update the value of the <code>key</code> if the <code>key</code> exists. Otherwise, add the <code>key-value</code> pair to the cache. If the number of keys exceeds the <code>capacity</code> from this operation, <strong>evict</strong> the least recently used key.</li>
 * </ul>
 * <p>The functions <code>get</code> and <code>put</code> must each run in <code>O(1)</code> average time complexity.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre>
 * <strong>Input</strong>
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * <strong>Output</strong>
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * <strong>Explanation</strong>
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= capacity &lt;= 3000</code></li>
 * <li><code>0 &lt;= key &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
 * <li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>get</code> and <code>put</code>.</li>
 * </ul>
 */

class LRUCache {
/**
 * Implementation Notes (Standardized Deep-Dive)
 * =============================================
 *
 * Design rationale
 * ----------------
 * This solution prioritizes correctness first, then readability, then optimization.
 * The algorithmic strategy is encoded directly in the method-level control flow
 * and helper abstractions below.
 *
 * Pros
 * ----
 * - Clear step-by-step control flow and predictable behavior.
 * - Uses standard Java data structures and idiomatic patterns.
 * - Easy to validate with examples and edge-case reasoning.
 *
 * Cons
 * ----
 * - May not be the asymptotically optimal variant for every input shape.
 * - Can trade some runtime efficiency for readability and maintainability.
 * - Data-structure overhead (boxing/object metadata) may affect large inputs.
 *
 * Time complexity
 * ---------------
 * Let n be input size and m be auxiliary structure size where applicable.
 * Complexity depends on the selected control-flow path and operations below.
 * The dominant terms come from loops, recursion depth, sorting/heap operations,
 * and map/set lookups used in the implemented path.
 *
 * Space complexity
 * ----------------
 * Auxiliary space depends on helper data structures and recursion stack depth
 * used by this implementation path.
 *
 * Overhead discussion
 * -------------------
 * - Object allocation and collection growth can contribute constant-factor costs.
 * - Hash-based and tree-based containers trade memory for operational speed.
 * - Intermediate containers improve clarity but can add temporary allocation cost.
 *
 * Future optimizations
 * --------------------
 * - Replace full-order operations with top-k or partial-order variants when possible.
 * - Reduce temporary allocations by reusing buffers/containers.
 * - Consider primitive-specialized structures for hot paths to reduce boxing overhead.
 *
 * ASCII execution model
 * ---------------------
 * Input -> Preprocess -> Core Transform -> Optional Ordering/Selection -> Output
 *
 *   [raw input]
 *        |
 *        v
 *   [normalize/prepare]
 *        |
 *        v
 *   [main algorithm steps]
 *        |
 *        v
 *   [construct result]
 */


    public LRUCache(int capacity) {
        
    }
    
    public int get(int key) {
        return 0;
    }
    
    public void put(int key, int value) {
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
