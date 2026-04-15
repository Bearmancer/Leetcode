package Q_0155_Min_Stack;

import java.util.Stack;

/**
 * <h2><a href="https://leetcode.com/problems/min-stack/">LeetCode #155: Min Stack</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Stack</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/min-stack">https://neetcode.io/problems/min-stack</a></p>
 *
 * <h3>Problem</h3>
 * <p>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.</p>
 * <p>Implement the <code>MinStack</code> class:</p>
 * <ul>
 * <li><code>MinStack()</code> initializes the stack object.</li>
 * <li><code>void push(int val)</code> pushes the element <code>val</code> onto the stack.</li>
 * <li><code>void pop()</code> removes the element on the top of the stack.</li>
 * <li><code>int top()</code> gets the top element of the stack.</li>
 * <li><code>int getMin()</code> retrieves the minimum element in the stack.</li>
 * </ul>
 * <p>You must implement a solution with <code>O(1)</code> time complexity for each function.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre>
 * <strong>Input</strong>
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * <strong>Output</strong>
 * [null,null,null,null,-3,null,0,-2]
 *
 * <strong>Explanation</strong>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>Methods <code>pop</code>, <code>top</code> and <code>getMin</code> operations will always be called on <strong>non-empty</strong> stacks.</li>
 * <li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>push</code>, <code>pop</code>, <code>top</code>, and <code>getMin</code>.</li>
 * </ul>
 */

class MinStack {
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

    /**
     * Core design: single-stack bit-packing approach.
     *
     * We store both values in one 64-bit long per push:
     * - high 32 bits: minimum so far at this stack depth
     * - low  32 bits: actual pushed value
     *
     * Why this design?
     * - Guarantees O(1) for push/pop/top/getMin.
     * - Avoids second auxiliary min-stack.
     * - Keeps each depth self-contained (min snapshot travels with the node).
     */
    private final Stack<Long> stack = new Stack<>();

    public MinStack() {
    }
    
    /**
     * Push with packed `(minSoFar, value)` representation.
     *
     * ASCII visualization
     * -------------------
     * Suppose pushes are: -2, 0, -3
     *
     * Depth 1 push(-2): minSoFar = -2
     *   packed = [high32=min=-2 | low32=val=-2]
     *
     * Depth 2 push(0): minSoFar = min(-2, 0) = -2
     *   packed = [high32=min=-2 | low32=val=0]
     *
     * Depth 3 push(-3): minSoFar = min(-2, -3) = -3
     *   packed = [high32=min=-3 | low32=val=-3]
     *
     * Stack top now carries both:
     *   top()    -> low32  -> -3
     *   getMin() -> high32 -> -3
     *
     * Time complexity: O(1)
     * Space complexity: O(n)
     */
    public void push(int val) {
        int min = (stack.isEmpty() || val < getMin()) ? val : getMin();
        long packed = (((long) min) << 32) | (val & 0xFFFFFFFFL);
        stack.push(packed);
    }
    
    /**
     * Pop top packed snapshot.
     *
     * Time complexity: O(1)
     * Space complexity: O(1) extra
     */
    public void pop() {
        stack.pop();
    }
    
    /**
     * Read the low 32 bits (actual value) from top packed long.
     *
     * Bit logic:
     * - mask: 0xFFFFFFFFL keeps only low 32 bits
     * - cast to int restores signed 32-bit value
     *
     * Time complexity: O(1)
     */
    public int top() {
        return (int) (stack.peek() & 0xFFFFFFFFL);
    }
    
    /**
     * Read the high 32 bits (minimum at this depth).
     *
     * Bit logic:
     * - arithmetic shift right by 32 moves high 32 bits into int position
     * - cast to int restores signed 32-bit minimum value
     *
     * Time complexity: O(1)
     */
    public int getMin() {
        return (int) (stack.peek() >> 32);
    }
}

/**
 * Design trade-offs and engineering notes
 * =======================================
 *
 * Pros
 * ----
 * - Single container for both value and min snapshot.
 * - Strict O(1) operations for all required APIs.
 * - Cache-friendly contiguous storage through Stack<Long> backing.
 *
 * Cons
 * ----
 * - Bit-manipulation logic is less intuitive than two-stack solution.
 * - Uses boxing (`Long`) because Java Stack is generic object-based.
 * - Slight readability overhead for maintainers unfamiliar with packing.
 *
 * Runtime/space characteristics
 * -----------------------------
 * Let n be number of pushed elements currently in stack.
 * - push/pop/top/getMin: O(1)
 * - total memory: O(n)
 *
 * Overhead
 * --------
 * - Each entry stores one `Long` object (object overhead + 8-byte payload).
 * - Compared to two-stack int approach:
 *   - this solution uses one stack but stores a 64-bit composite.
 *   - two-stack approach uses two structures but simpler semantics.
 *
 * Future optimizations
 * --------------------
 * - Replace `Stack<Long>` with `ArrayDeque<Long>` for modern non-synchronized stack semantics.
 * - For performance-critical paths, use primitive long arrays to avoid boxing.
 * - If clarity is preferred over compactness, switch to explicit two-stack design:
 *   valueStack + minStack.
 */

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
