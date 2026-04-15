package Q_0347_Top_K_Frequent_Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
 * NeetCode: https://neetcode.io/problems/top-k-frequent-elements
 * LeetCode #347: Top K Frequent Elements
 * <p>
 * Given an integer array {@code nums} and an integer {@code k}, return the
 * {@code k} most frequent elements. You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * <p>
 * Output: [1,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * <p>
 * Output: [1]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * <p>
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * - {@code 1 <= nums.length <= 10^5}
 * - {@code -10^4 <= nums[i] <= 10^4}
 * - {@code k} is in the range
 * {@code [1, the number of unique elements in the array]}.
 * - It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * Follow up: Your algorithm's time complexity must be better than
 * {@code O(n log n)}, where n is the array's size.
 */

class Solution {
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
	 * Delegates to the production-oriented HashMap approach.
	 *
	 * We keep two HashMap-based approaches in this file:
	 * 1) {@link #hashMapSolution(int[], int)}  - educational/debug style
	 * 2) {@link #hashMapSolution2(int[], int)} - direct LeetCode-ready return path
	 */
	public int[] topKFrequent(int[] nums, int k) {
		return hashMapSolution2(nums, k);
	}

	static void main() {
		hashMapSolution(new int[] { 1, 4, 5, 1, 3 }, 2);
	}

	/**
	 * HashMap Solution #1 (Educational Pipeline)
	 * =========================================
	 *
	 * Design rationale
	 * ----------------
	 * This version emphasizes readability of the frequency-building pipeline:
	 *
	 *   nums[] -> HashMap<number, frequency> -> List<Tuple(number, frequency)> -> sort -> inspect
	 *
	 * It is intentionally "transparent": each stage has a visible intermediate
	 * artifact that is easy to print and reason about during interviews or debugging.
	 *
	 * ASCII visualization
	 * -------------------
	 * Example: nums = [1,1,1,2,2,3], k = 2
	 *
	 * Stage A: count with HashMap
	 *
	 *   +-------+-------+
	 *   | value | count |
	 *   +-------+-------+
	 *   |   1   |   3   |
	 *   |   2   |   2   |
	 *   |   3   |   1   |
	 *   +-------+-------+
	 *
	 * Stage B: convert to Tuple list
	 *
	 *   [(1,3), (2,2), (3,1)]
	 *
	 * Stage C: sort by frequency
	 *
	 *   ascending via compareTo -> [(3,1), (2,2), (1,3)]
	 *   descending if reversed comparator -> [(1,3), (2,2), (3,1)]
	 *
	 * Pros
	 * ----
	 * - Very easy to inspect each step and print intermediate state.
	 * - Great for debugging and explanation-heavy walkthroughs.
	 * - Uses standard Java collections with minimal conceptual overhead.
	 *
	 * Cons
	 * ----
	 * - Sorts all unique elements, even if k is tiny.
	 * - As written, this method prints instead of returning top-k values.
	 * - If used directly in production, one would still need extraction logic.
	 *
	 * Time complexity
	 * ---------------
	 * Let:
	 * - n = nums.length
	 * - u = number of unique values
	 *
	 * 1) Build frequency map: O(n)
	 * 2) Convert map to list: O(u)
	 * 3) Sort list: O(u log u)
	 *
	 * Total: O(n + u log u)
	 * In worst case (all unique): O(n log n)
	 *
	 * Space complexity
	 * ----------------
	 * - HashMap: O(u)
	 * - Tuple list: O(u)
	 *
	 * Total auxiliary: O(u)
	 *
	 * Overhead discussion
	 * -------------------
	 * - HashMap overhead includes bucket array + node/object overhead.
	 * - Tuple record list adds another object layer per unique key.
	 * - Sorting incurs comparator + internal array operations.
	 *
	 * Future optimizations
	 * --------------------
	 * - Replace full sort with a size-k min-heap (O(n + u log k)).
	 * - Use bucket frequency indexing (O(n) average for bounded frequencies).
	 * - If value range is tightly bounded, replace HashMap with counting array.
	 */
	private static void hashMapSolution(int[] nums, int k) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();

		for (int num : nums) {
			hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
		}

		List<Tuple> frequencies = new ArrayList<>();

		hashMap.forEach((key, value) -> frequencies.add(new Tuple(key, value)));

		Collections.sort(frequencies);

		System.out.println(frequencies);

	}

	/**
	 * HashMap Solution #2 (Production Return Path)
	 * ============================================
	 *
	 * Design rationale
	 * ----------------
	 * This is the practical "return result" counterpart of Solution #1:
	 *
	 * 1) Build frequency map from the input.
	 * 2) Materialize tuples (number, frequency).
	 * 3) Sort tuples by frequency descending.
	 * 4) Take first k numbers.
	 *
	 * This approach optimizes for implementation simplicity and deterministic
	 * correctness with straightforward control flow.
	 *
	 * ASCII visualization
	 * -------------------
	 * Example: nums = [5,5,5,2,2,7,7,7,7], k = 2
	 *
	 * Frequency map:
	 *
	 *   5 -> 3
	 *   2 -> 2
	 *   7 -> 4
	 *
	 * Tuple list before sort:
	 *
	 *   [(5,3), (2,2), (7,4)]
	 *
	 * Tuple list after descending sort:
	 *
	 *   [(7,4), (5,3), (2,2)]
	 *
	 * Extract first k=2 numbers:
	 *
	 *   result = [7, 5]
	 *
	 * Pros
	 * ----
	 * - Compact and interview-friendly implementation.
	 * - Easy to validate and reason about.
	 * - Reuses frequency map and tuple model cleanly.
	 *
	 * Cons
	 * ----
	 * - Still sorts all unique entries even when k is very small.
	 * - Does not exploit the follow-up target beyond O(n log n) worst case.
	 * - Memory includes both map and tuple list.
	 *
	 * Time complexity
	 * ---------------
	 * - Build map: O(n)
	 * - Build tuple list: O(u)
	 * - Sort tuple list: O(u log u)
	 * - Copy top-k: O(k)
	 *
	 * Total: O(n + u log u + k)
	 * Worst case u=n: O(n log n)
	 *
	 * Space complexity
	 * ----------------
	 * - Frequency map: O(u)
	 * - Tuple list: O(u)
	 * - Result array: O(k)
	 *
	 * Total auxiliary: O(u + k)
	 *
	 * Overhead discussion
	 * -------------------
	 * - The main cost center is sorting all unique keys.
	 * - Tuple materialization creates extra objects but keeps code readable.
	 * - For large u and small k, work is over-provisioned compared to heap-based selection.
	 *
	 * Future optimizations
	 * --------------------
	 * - HashMap + min-heap(size k): O(n + u log k), ideal when k << u.
	 * - HashMap + buckets by frequency: O(n + u), often fastest in practice for this problem.
	 * - Quickselect on tuple frequencies: average O(u), but trickier and less stable.
	 */
	private int[] hashMapSolution2(int[] nums, int k) {
		HashMap<Integer, Integer> frequencyMap = new HashMap<>();
		for (int num : nums) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}

		List<Tuple> frequencies = new ArrayList<>();
		frequencyMap.forEach((number, frequency) -> frequencies.add(new Tuple(number, frequency)));
		frequencies.sort((a, b) -> Integer.compare(b.frequency(), a.frequency()));

		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = frequencies.get(i).number();
		}
		return result;
	}

	/**
	 * Lightweight pair used by both HashMap solutions.
	 *
	 * Compare semantics:
	 * - Natural ordering (compareTo) is ascending by frequency.
	 * - Solution #2 uses an explicit descending comparator in the sort call.
	 */
	private record Tuple(int number, int frequency) implements Comparable<Tuple> {
		@Override
		public int compareTo(Tuple o) {
			return frequency - o.frequency;
		}

		@Override
		public String toString() {
			return "number: " + number + " - frequency: " + frequency;
		}
	}
}