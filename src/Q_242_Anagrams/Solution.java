package Q_242_Anagrams;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
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
		var sCharMap = countCharHashMap(s);
		var tCharMap = countCharHashMap(t);

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