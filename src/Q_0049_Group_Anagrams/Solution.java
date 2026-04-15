package Q_0049_Group_Anagrams;

import java.util.*;

/**
 * LeetCode: https://leetcode.com/problems/group-anagrams/
 * NeetCode: https://neetcode.io/problems/group-anagrams
 * LeetCode #49: Group Anagrams
 *
 * Given an array of strings {@code strs}, group the anagrams together. You can return the answer in any order.
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 *
 * - There is no string in strs that can be rearranged to form {@code "bat"}.
 * - The strings {@code "nat"} and {@code "tan"} are anagrams as they can be rearranged to form each other.
 * - The strings {@code "ate"}, {@code "eat"}, and {@code "tea"} are anagrams as they can be rearranged to form each other.
 *
 * Example 2:
 *
 * Input: strs = [""]
 *
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 *
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * - {@code 1 <= strs.length <= 10^4}
 * - {@code 0 <= strs[i].length <= 100}
 * - {@code strs[i]} consists of lowercase English letters.
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

	public List<List<String>> groupAnagrams(String[] strs) {
		return hashMap(strs);

	}

	private List<List<String>> hashMap(String[] strs) {
		HashMap<String, List<String>> hashMap = new HashMap<>();
		for (String str : strs) {
			String key = getKey(str);
			if (hashMap.containsKey(key)) {
				hashMap.get(key).add(str);
				continue;
			}

			ArrayList<String> strings = new ArrayList<>();
			strings.add(str);
			hashMap.put(key, strings);
		}

		return new ArrayList<>(hashMap.values());
	}

	// Theta(k log k) where k is the length of the string
	private String getKey(String str) {
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}

	// Theta(k) time where k is the length of the string
	private String getKey2(String str) {
		int[] counts = countCharArray(str); // eat - [1, 0, 0, 0, 1, 0, 0, .. ]
		return Arrays.toString(counts); // "[1, 0, 0, 0, 1, 0, 0, .. ]"
	}

	private List<List<String>> bruteForce(String[] strs) {
		List<List<String>> answer = new ArrayList<>();

		int length = strs.length;
		HashSet<String> wordsAccounted = new HashSet<>();
		for (int i = 0; i < length; i++) {
			var word = strs[i];
			if (wordsAccounted.contains(word)) continue;
			List<String> anagrams = new ArrayList<>();
			anagrams.add(word);
			wordsAccounted.add(word);
			for (int j = i + 1; j < length; j++) {
				String otherWord = strs[j];
				if (isAnagram(word, otherWord)) {
					anagrams.add(otherWord);
					wordsAccounted.add(otherWord);
				}
			}

			answer.add(anagrams);
		}

		return answer;
	}

	private boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		return lowerCaseSolution(s, t);
	}

	public boolean lowerCaseSolution(String s, String t) {
		var sCharArr = countCharArray(s);
		var tCharArr = countCharArray(t);

		return Arrays.equals(sCharArr, tCharArr);
	}

	private int[] countCharArray(String s) {
		int[] letterFrequency = new int[26];

		for (int i = 0; i < s.length(); i++) {
			var letterValue = s.charAt(i) - 'a';
			letterFrequency[letterValue]++;
		}

		return letterFrequency;
	}
}