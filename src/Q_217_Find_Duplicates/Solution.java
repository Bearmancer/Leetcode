package Q_217_Find_Duplicates;

import java.util.Arrays;
import java.util.HashSet;

class Solution {
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