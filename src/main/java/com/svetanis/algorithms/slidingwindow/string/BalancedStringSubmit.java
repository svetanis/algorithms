package com.svetanis.algorithms.slidingwindow.string;

// 1234. Replace the Substring for Balanced String
//
// Input: a string of the letters Q, W, E and R whose length n is a multiple of 4.
// Return: the length of the shortest substring that could be replaced by any string
// of the same length so that each of the four letters appears exactly n/4 times;
// 0 when the string is already balanced.
//
// The one idea: the window is the part that gets replaced, so it can be rewritten
// into whatever letters are missing -- only the letters OUTSIDE it matter. A window
// works when no letter outside it appears more than n/4 times. The counts start as
// the counts of the whole string, so they always describe the outside of the
// window: a letter entering the window on the right is subtracted, a letter leaving
// it on the left is added back. A longer window only lowers the outside counts, so
// once a window works every longer one does too. That is the shortest-window loop:
// shrink while the window still works, measuring before each left letter goes back
// outside.
//
// Siblings: BalancedString -- the same window over a HashMap, which accepts any
//   letter. This one indexes an int[4] through "QWER".indexOf(c), so any other
//   letter gives index -1 and throws.
//
// Time: O(n) -- each letter enters the window once and leaves at most once, and
//   notExceed reads four counts.
// Space: O(1) -- four counts.

public final class BalancedStringSubmit {

	private static final String QWER = "QWER";

	public static int balancedStr(String s) {
		int[] count = new int[4];
		for (char c : s.toCharArray()) {
			count[QWER.indexOf(c)]++; // START: the whole string is outside the window
		}
		int n = s.length();
		int target = n / 4;
		if (notExceed(count, target)) {
			return 0; // RETURN: already balanced, nothing to replace
		}
		int min = n;
		int left = 0;
		for (int right = 0; right < n; right++) {
			char c = s.charAt(right);
			count[QWER.indexOf(c)]--; // ADD: c enters the window, so it leaves the outside
			while (left <= right && notExceed(count, target)) { // SHRINK: while the outside is within quota
				min = Math.min(min, right - left + 1); // MEASURE: before the left letter goes back outside
				count[QWER.indexOf(s.charAt(left))]++;
				left++;
			}
		}
		return min;
	}

	// true when no letter outside the window is over its quota of n/4
	private static boolean notExceed(int[] count, int target) {
		for (int i = 0; i < count.length; i++) {
			if (count[i] > target) {
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
