import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class topkelements {
	/**
	 * LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
	 * NeetCode: https://neetcode.io/problems/top-k-frequent-elements
	 * LeetCode #347: Top K Frequent Elements
	 * <p>
	 * Given an integer array {@code nums} and an integer {@code k}, return the {@code k} most frequent elements. You may return the answer in any order.
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
	 * - {@code k} is in the range {@code [1, the number of unique elements in the array]}.
	 * - It is guaranteed that the answer is unique.
	 * <p>
	 * <p>
	 * Follow up: Your algorithm's time complexity must be better than {@code O(n log n)}, where n is the array's size.
	 */

	public int[] topKFrequent(int[] nums, int k) {
		return hashMapSolution2(nums, k);
	}

	private record Tuple(int number, int frequency) implements Comparable<Tuple> {
		@Override
		public int compareTo(Tuple o) {
			return -(frequency - o.frequency);
		}

		@Override
		public String toString() {
			return "number: " + number + " - frequency: " + frequency;
		}
	}

	private int[] hashMapSolution2(int[] nums, int k) {
		return new int[0];
	}
}
