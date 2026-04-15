package Q_0001_Two_Sum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode: https://leetcode.com/problems/two-sum/
 * NeetCode: https://neetcode.io/problems/two-sum
 * LeetCode #1: Two Sum
 *
 * <h2>Problem Statement</h2>
 * <p>
 * Given an array of integers {@code nums} and an integer {@code target}, return
 * <em>indices</em> of the two numbers that add up to {@code target}. Each input
 * has exactly one solution, and you may not use the same element twice.
 * You may assume that you will never receive invalid input (i.e. a solution is
 * always guaranteed to exist).
 *
 * <h2>Overview of Approaches</h2>
 * <p>
 * Three approaches are implemented in this class, listed from optimal to naive:
 * <ol>
 *   <li><b>HashMap (one-pass)</b> — optimal O(n) solution</li>
 *   <li><b>Binary Search</b> — sort-then-search at O(n log n)</li>
 *   <li><b>Brute Force</b> — check every pair at O(n²)</li>
 * </ol>
 *
 * <h2>Complexity Comparison</h2>
 * <pre>
 * | Approach         | Time Complexity | Space Complexity | Notes                                       |
 * |------------------|-----------------|------------------|----------------------------------------------|
 * | Brute Force      | O(n²)           | O(1)             | Simplest; checks every pair                  |
 * | Binary Search    | O(n log n)      | O(n)             | Sort then binary search complement           |
 * | HashMap (1-pass) | O(n)            | O(n)             | Optimal; single pass with hash map           |
 * </pre>
 *
 * <h2>Design Rationale</h2>
 *
 * <h3>Brute Force</h3>
 * <p>
 * The naive baseline. Enumerate all pairs {@code (i, j)} where {@code i < j}.
 * No extra memory is required. Good for understanding the problem but
 * impractical for large inputs. We start {@code j} at {@code i + 1} to avoid
 * self-pairing and duplicate pair checks.
 *
 * <h3>Binary Search</h3>
 * <p>
 * Key insight: if we sort the array, for each element we can binary-search for
 * its complement ({@code target - element}). The challenge is that sorting
 * destroys original indices, so we wrap each value in a {@link Pair} record
 * that preserves the original index. Sorting costs O(n log n), then n binary
 * searches each costing O(log n) for a total of O(n log n). The
 * duplicate-handling logic (when complement equals the current value) is
 * necessary because the problem guarantees exactly one solution, so we must
 * correctly handle the case where {@code target = 2 * value}.
 *
 * <h3>HashMap (one-pass)</h3>
 * <p>
 * The optimal solution. As we iterate, for each number we ask "have I already
 * seen the complement?" If yes, we're done. If no, store this number in the
 * map. This is O(n) time because each lookup/insert is O(1) amortized, and
 * O(n) space for the map. The one-pass variant is preferred over two-pass
 * because it avoids a second iteration and handles duplicates naturally — we
 * only check backwards (already-seen elements), so we never pair an element
 * with itself.
 */
// NOTE on method ordering: IntelliJ IDEA does NOT enforce any internal method
// ordering — Java allows methods in any order and the compiler resolves forward
// references regardless of textual position. The ordering here is deliberate for
// readability: public API first, private implementations below, data structures
// last. If one enables "Rearrange Code" in IntelliJ's Code → Rearrange Code
// action with a configured code style scheme, IntelliJ CAN auto-sort methods by
// visibility/modifier, but this is opt-in and not enforced by default.
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
	 * One-pass HashMap solution — the optimal approach.
	 *
	 * <p>Iterates through {@code nums} once, using a hash map to look up
	 * previously seen values and find the complement in constant time.
	 *
	 * <h3>Algorithm</h3>
	 * <ol>
	 *   <li>Create an empty map from value → index</li>
	 *   <li>For each element {@code nums[i]}:
	 *     <ol type="a">
	 *       <li>Compute {@code complement = target - nums[i]}</li>
	 *       <li>If {@code complement} exists in the map, return its index and
	 *           the current index {@code i}</li>
	 *       <li>Otherwise, store {@code nums[i] → i} in the map</li>
	 *     </ol>
	 *   </li>
	 * </ol>
	 *
	 * <h3>Complexity</h3>
	 * <ul>
	 *   <li><b>Time:</b> O(n) — single pass; each HashMap {@code get/put} is
	 *       O(1) amortized</li>
	 *   <li><b>Space:</b> O(n) — in the worst case we store every element in
	 *       the map before finding the pair</li>
	 * </ul>
	 *
	 * <h3>Design Rationale</h3>
	 * <p>
	 * We use {@code getOrDefault} instead of {@code containsKey} + {@code get}
	 * to collapse two map operations into one, reducing constant overhead. We
	 * check the map <em>before</em> inserting the current element so that we
	 * never pair an element with itself — only previously-seen elements are
	 * considered as complement candidates. This naturally handles the case
	 * where the two solution elements are duplicates (e.g. {@code [3, 3]},
	 * {@code target = 6}): the first {@code 3} is stored, and when we
	 * encounter the second {@code 3} we find the first one already in the map.
	 *
	 * @param nums   the input array of integers
	 * @param target the target sum
	 * @return a two-element array containing the indices of the two numbers, or
	 * {@code null} if no solution exists (should not happen per problem
	 * constraints)
	 */
	private static int[] hashMapSolution(int[] nums, int target) {
		HashMap<Integer, Integer> valueToIndexMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			var num = nums[i];
			// The complement is the value we need to have already seen
			var complement = target - num;
			// getOrDefault avoids a separate containsKey call
			int complementIndex = valueToIndexMap.getOrDefault(complement, -1);
			if (complementIndex != -1) {
				// We found a previously-seen value that pairs with the current one
				return new int[]{complementIndex, i};
			}
			// Store current value so future elements can find it as their complement
			valueToIndexMap.put(num, i);
		}
		return null;
	}

	/**
	 * Binary Search solution — sorts the array, then searches for each
	 * element's complement.
	 *
	 * <p>Wraps values in {@link Pair} records to preserve original indices
	 * across sorting, then for each element binary-searches for its complement.
	 *
	 * <h3>Algorithm</h3>
	 * <ol>
	 *   <li>Wrap each {@code nums[i]} in a {@code Pair(i, nums[i])}</li>
	 *   <li>Sort the {@code Pair} array by value</li>
	 *   <li>For each sorted element at index {@code valueIndex}:
	 *     <ol type="a">
	 *       <li>Compute {@code complement = target - pairValue}</li>
	 *       <li>Binary search for {@code complement} in the sorted array</li>
	 *       <li>If found, handle the special case where
	 *           {@code complement == pairValue} (duplicate values) by checking
	 *           adjacent elements</li>
	 *       <li>Return the two original indices</li>
	 *     </ol>
	 *   </li>
	 * </ol>
	 *
	 * <h3>Complexity</h3>
	 * <ul>
	 *   <li><b>Time:</b> O(n log n) — sorting dominates (O(n log n)); each of
	 *       the n binary searches costs O(log n)</li>
	 *   <li><b>Space:</b> O(n) — the auxiliary {@code Pair[]} array</li>
	 * </ul>
	 *
	 * <h3>Design Rationale</h3>
	 * <p>
	 * Sorting destroys original indices, which is why we need the {@link Pair}
	 * wrapper. The duplicate-handling branch (when {@code target == 2 * pairValue})
	 * checks adjacent elements in the sorted array — since the array is sorted,
	 * any duplicate values will be contiguous. This avoids incorrectly returning
	 * the same index twice. After the duplicate check, we return immediately
	 * because the problem guarantees exactly one valid pair.
	 *
	 * @param nums   the input array of integers
	 * @param target the target sum
	 * @return a two-element array containing the indices of the two numbers, or
	 * {@code null} if no solution exists
	 */
	private static int[] binarySearchSolution(int[] nums, int target) {
		int n = nums.length;
		// Preserve original indices across sorting by wrapping in Pair records
		Pair[] pairs = new Pair[n];
		for (int i = 0; i < n; i++) {
			pairs[i] = new Pair(i, nums[i]);
		}
		Arrays.sort(pairs);
		for (int valueIndex = 0; valueIndex < n; valueIndex++) {
			var pairValue = pairs[valueIndex].value;
			// Search for the complement in the sorted array
			int complementIndex = binarySearch(pairs, target - pairValue);
			if (complementIndex == -1) continue;
			// Special case: complement equals current value (target = 2 * value).
			// Since duplicates are contiguous after sorting, check neighbours.
			if (target == 2 * pairValue) {
				// Check the element right after for a duplicate
				if (complementIndex < n - 1 && pairs[complementIndex + 1].value == pairValue) {
					return new int[]{pairs[complementIndex].originalIndex, pairs[complementIndex + 1].originalIndex};
				}
				// Check the element right before for a duplicate
				if (complementIndex > 0 && pairs[complementIndex - 1].value == pairValue) {
					return new int[]{pairs[complementIndex - 1].originalIndex, pairs[complementIndex].originalIndex};
				}
			}
			// Found a distinct complement — return both original indices
			return new int[]{pairs[valueIndex].originalIndex, pairs[complementIndex].originalIndex};
		}
		return null;
	}

	/**
	 * Performs a binary search for {@code value} in a sorted {@code Pair}
	 * array.
	 *
	 * <h3>Algorithm</h3>
	 * <ol>
	 *   <li>Initialize {@code lo = 0}, {@code hi = pairs.length - 1}</li>
	 *   <li>While {@code lo <= hi}:
	 *     <ol type="a">
	 *       <li>Compute midpoint (overflow-safe: {@code (hi - lo) / 2 + lo})</li>
	 *       <li>If {@code pairs[midpoint].value == value}, return
	 *           {@code midpoint}</li>
	 *       <li>If {@code value < pairs[midpoint].value}, search left half
	 *           ({@code hi = midpoint - 1})</li>
	 *       <li>Otherwise search right half ({@code lo = midpoint + 1})</li>
	 *     </ol>
	 *   </li>
	 *   <li>Return {@code -1} if not found</li>
	 * </ol>
	 *
	 * <h3>Complexity</h3>
	 * <ul>
	 *   <li><b>Time:</b> O(log n) — halves the search space each iteration</li>
	 *   <li><b>Space:</b> O(1) — iterative; no recursion stack</li>
	 * </ul>
	 *
	 * <h3>Design Rationale</h3>
	 * <p>
	 * The midpoint is computed as {@code (hi - lo) / 2 + lo} rather than
	 * {@code (lo + hi) / 2} to prevent integer overflow when {@code lo} and
	 * {@code hi} are both large. An iterative loop is preferred over recursion
	 * to avoid stack overflow on very large arrays and to eliminate method-call
	 * overhead. Note that if duplicate values exist, this may return any one of
	 * their indices — the caller ({@link #binarySearchSolution}) handles this.
	 *
	 * @param pairs the sorted array of Pairs to search
	 * @param value the value to find
	 * @return the index of a Pair with the given value, or {@code -1} if absent
	 */
	private static int binarySearch(Pair[] pairs, int value) {
		var lo = 0;
		var hi = pairs.length - 1;
		while (lo <= hi) {
			// Overflow-safe midpoint calculation
			var midpoint = (hi - lo) / 2 + lo;
			if (pairs[midpoint].value == value) return midpoint;
			if (value < pairs[midpoint].value) hi = midpoint - 1;
			else lo = midpoint + 1;
		}
		return -1;
	}

	/**
	 * Brute Force solution — checks every possible pair.
	 *
	 * <p>Enumerates all index pairs {@code (i, j)} where {@code i < j} and
	 * returns the first pair whose values sum to {@code target}.
	 *
	 * <h3>Algorithm</h3>
	 * <ol>
	 *   <li>For each index {@code i} from 0 to n-1:
	 *     <ol type="a">
	 *       <li>For each index {@code j} from {@code i + 1} to n-1:
	 *         <ol type="i">
	 *           <li>If {@code nums[i] + nums[j] == target}, return
	 *               {@code {i, j}}</li>
	 *         </ol>
	 *       </li>
	 *     </ol>
	 *   </li>
	 *   <li>Return {@code null} if no pair found</li>
	 * </ol>
	 *
	 * <h3>Complexity</h3>
	 * <ul>
	 *   <li><b>Time:</b> O(n²) — checks all n(n-1)/2 pairs</li>
	 *   <li><b>Space:</b> O(1) — no auxiliary data structures</li>
	 * </ul>
	 *
	 * <h3>Design Rationale</h3>
	 * <p>
	 * The inner loop starts at {@code j = i + 1} rather than {@code 0} for two
	 * reasons: (1) it avoids pairing an element with itself (the problem
	 * forbids using the same element twice), and (2) it avoids checking both
	 * {@code (i, j)} and {@code (j, i)}, halving the work compared to a naive
	 * double loop starting at 0. This is the simplest correct solution and
	 * serves as a baseline for verifying the correctness of more sophisticated
	 * approaches.
	 *
	 * @param nums   the input array of integers
	 * @param target the target sum
	 * @return a two-element array containing the indices of the two numbers, or
	 * {@code null} if no solution exists
	 */
	private static int[] bruteForceSolution(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {
			// j starts at i + 1 to avoid self-pairing and duplicate checks
			for (int j = i + 1; j < nums.length; j++) {
				var sum = nums[i] + nums[j];
				if (sum == target) return new int[]{i, j};
			}
		}
		return null;
	}

	/**
	 * Returns the indices of the two numbers in {@code nums} that add up to
	 * {@code target}.
	 */
	public int[] twoSum(int[] nums, int target) {
		return hashMapSolution(nums, target);
	}

	/**
	 * A value wrapper that preserves the original array index across sorting.
	 *
	 * <p>Implements {@link Comparable} so that an array of Pairs can be sorted
	 * by value using {@link Arrays#sort(Object[])}.
	 *
	 * <h3>Design Rationale</h3>
	 * <p>
	 * A Java record is used because it automatically provides an immutable
	 * carrier for the two fields ({@code originalIndex} and {@code value}),
	 * along with {@code equals}, {@code hashCode}, and {@code toString}. The
	 * natural ordering is by {@code value} only — original indices are not
	 * compared because they are irrelevant to the sort order needed for binary
	 * search.
	 *
	 * @param originalIndex the index of this value in the original (unsorted) array
	 * @param value         the integer value from the array
	 */
	record Pair(int originalIndex, int value) implements Comparable<Pair> {
		@Override
		public int compareTo(Pair pair) {
			return value - pair.value;
		}
	}
}