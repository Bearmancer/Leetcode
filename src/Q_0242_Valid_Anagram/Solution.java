package Q_0242_Valid_Anagram;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode: https://leetcode.com/problems/valid-anagram/
 * NeetCode: https://neetcode.io/problems/valid-anagram
 * LeetCode #242: Valid Anagram
 *
 * Given two strings {@code s} and {@code t}, return {@code true} if {@code t} is an anagram of {@code s}, and {@code false} otherwise.
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 *
 * Output: false
 *
 *
 * Constraints:
 *
 * - {@code 1 <= s.length, t.length <= 5 * 10^4}
 * - {@code s} and {@code t} consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
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

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		return lowerCaseSolution(s, t);
	}


	public boolean sortFirst(String s, String t) {
		char[] sCharArray = s.toCharArray();
		char[] tCharArray = t.toCharArray();
		Arrays.sort(sCharArray);
		Arrays.sort(tCharArray);

		for (int i = 0; i < sCharArray.length; i++) {
			if (sCharArray[i] != tCharArray[i]) return false;
		}

		return true;
	}

	public boolean strikeThrough(String s, String t) {
		var sCharMap = countCharHashMap(s); // eat {e: 1, a: 1, t: 1}
		var tCharMap = countCharHashMap(t); // tea {t: 1, e: 1, a: 1}

		return sCharMap.equals(tCharMap);
	}

	private HashMap<Character, Integer> countCharHashMap(String s) {
		HashMap<Character, Integer> charHashMap = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			charHashMap.put(c, charHashMap.getOrDefault(c, 0) + 1);
		}

		return charHashMap;
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