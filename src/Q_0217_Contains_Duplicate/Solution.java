package Q_0217_Contains_Duplicate;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <h1><a href="https://leetcode.com/problems/contains-duplicate/">LeetCode #217: Contains Duplicate</a></h1>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Array, Hash Table, Sorting</p>
 *
 * <h2>Problem</h2>
 * <p>Given an integer array <code>nums</code>, return <code>true</code> if any value appears <strong>at least twice</strong> in the array, and return <code>false</code> if every element is distinct.</p>
 *
 * <h2>Examples</h2>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1,2,3,1]
 * <strong>Output:</strong> true
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1,2,3,4]
 * <strong>Output:</strong> false
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1,1,1,3,3,4,3,2,4,2]
 * <strong>Output:</strong> true
 * </pre>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 *   <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 */

class Solution {
	/*
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

	public boolean containsDuplicate(int[] nums) {
		return sortFirstSolution(nums);
	}

	private boolean hashSetSolution(int[] nums) {
		HashSet<Integer> integers = new HashSet<Integer>();

		for (int num : nums) {
			if (integers.contains(num)) return true;
			integers.add(num);
		}

		return false;
	}

	private boolean pairsLookupSolution(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == nums[j]) return true;
			}
		}

		return false;
	}

	private boolean sortFirstSolution(int[] nums) {
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) return true;
		}

		return false;
	}
}