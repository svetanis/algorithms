package com.svetanis.algorithms.string;

// 3019. Number of Changing Keys
//
// Input: a string s typed on a keyboard. Using a key different from the one used last is a change;
// pressing shift or caps lock does not count, so 'a' then 'A' is the same key. Returns the number
// of changes. On LeetCode: 1 <= s.length <= 100, English letters, upper or lower case.
//
// The one idea: a change happens between two neighbouring characters, so look at every pair
// (s[i], s[i + 1]) and count those whose lowercase forms differ. Lowercasing both sides is what
// makes 'a' and 'A' the same key. A string of n characters has n - 1 pairs.
//
// Time: O(n) -- one pass over the pairs
// Space: O(1)

public final class CountChangingKeys {

	public static int countKeyChanges(String s) {
		int count = 0;
		for (int i = 0; i < s.length() - 1; i++) { // WALK: every neighbouring pair
			char curr = Character.toLowerCase(s.charAt(i)); // KEY: shift state ignored
			char next = Character.toLowerCase(s.charAt(i + 1));
			if (curr != next) {
				count++; // RECORD: a different key was pressed
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countKeyChanges("aAbBcC")); // 2
		System.out.println(countKeyChanges("AaAaAaaA")); // 0
	}
}
