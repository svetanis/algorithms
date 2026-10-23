package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 1234. Replace the Substring for Balanced String
//
// Input: a string of the letters Q, W, E and R whose length n is a multiple of 4.
// Return: the length of the shortest substring that could be replaced by any string
// of the same length so that each of the four letters appears exactly n/4 times;
// 0 when the string is already balanced.
//
// The one idea: the window is the part that gets replaced, so it can be rewritten
// into whatever letters are missing -- only the letters OUTSIDE it matter. A window
// works when no letter outside it appears more than n/4 times. The map starts with
// the counts of the whole string, so it always holds the counts outside the window:
// a letter entering the window on the right is subtracted, a letter leaving it on
// the left is added back. A longer window only lowers the outside counts, so once a
// window works every longer one does too. That is the shortest-window loop: shrink
// while the window still works, measuring before each left letter goes back outside.
//
// Siblings: BalancedStringSubmit -- the same window over an int[4] indexed through
//   "QWER".indexOf(c) instead of a map; it throws on any other letter, while this
//   one counts whatever letters appear.
//
// Time: O(n) -- each letter enters the window once and leaves at most once, and
//   notExceed reads at most four counts.
// Space: O(1) -- the map holds at most the four letters.

public final class BalancedString {

	public static int balancedStr(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.merge(c, 1, Integer::sum); // START: the whole string is outside the window
		}
		int n = s.length();
		int target = n / 4;
		if (notExceed(map, target)) {
			return 0; // RETURN: already balanced, nothing to replace
		}
		int min = n;
		int left = 0;
		for (int right = 0; right < n; right++) {
			char c = s.charAt(right);
			map.merge(c, -1, Integer::sum); // ADD: c enters the window, so it leaves the outside
			while (left <= right && notExceed(map, target)) { // SHRINK: while the outside is within quota
				min = Math.min(min, right - left + 1); // MEASURE: before the left letter goes back outside
				map.merge(s.charAt(left), 1, Integer::sum);
				left++;
			}
		}
		return min;
	}

	// true when no letter outside the window is over its quota of n/4
	private static boolean notExceed(Map<Character, Integer> map, int target) {
		for (char c : map.keySet()) {
			if (map.get(c) > target) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(balancedStr("QWER")); // 0
		System.out.println(balancedStr("QQWE")); // 1
		System.out.println(balancedStr("QQQW")); // 2
		System.out.println(balancedStr("QQQWWWEEEERRRR")); // 2
	}
}
