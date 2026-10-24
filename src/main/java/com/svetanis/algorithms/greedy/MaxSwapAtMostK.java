package com.svetanis.algorithms.greedy;

// Largest Number in K Swaps -- not a LeetCode problem; the rules below are read from the code and
// its main.
//
// Input: s -- a string of digits; k -- the most swaps allowed, where one swap exchanges the digits
// at two positions. Returns the largest digit string that at most k swaps can make; fewer swaps, or
// none, are allowed. The code assumes k >= 0. The search grows like (n^2)^k, so it is meant for
// short strings and small k.
//
// The one idea: this file does not commit to one swap per step; it searches. From the current
// string it tries every swap that moves a bigger digit to an earlier position (chars[i] < chars[j]
// with i < j), records the string if it is the largest seen so far, searches on with one swap
// fewer, and then swaps back. Leaving out every other swap loses nothing. Call the best reachable
// string T, and turn the start into T one position at a time from the left: at the first position
// where they differ, bring in T's digit from further right. That digit is always bigger than the
// one it replaces -- if it were smaller, the string at that moment would already be larger than T,
// made with fewer swaps, which cannot be. So T is reachable by swaps that each move a bigger digit
// earlier, and the search tries all of those. The best is recorded after every swap, so stopping
// before k swaps is covered too. Strings of the same length compare as text in the same order as
// the numbers they spell.
//
// Time: O((n^2)^k * n) -- each level of the search tries up to n(n - 1)/2 swaps, there are k
// levels, and each try builds two strings of length n.
// Space: O(k * n) -- k nested calls, each holding two strings of length n while it compares.
//
// Related: MaxSwap is the one-swap version on a number (LeetCode 670) and picks the swap directly.

public final class MaxSwapAtMostK {

	public static String maxNum(String s, int k) {
		char[] chars = s.toCharArray(); // CURRENT digits, changed and restored by the search
		char[] max = s.toCharArray(); // BEST so far; making no swap at all is allowed
		maxNum(chars, max, k);
		return String.valueOf(max); // RETURN: the best string
	}

	private static void maxNum(char[] chars, char[] max, int k) {
		if (k == 0) { // BASE: no swaps left
			return;
		}
		int n = chars.length;
		for (int i = 0; i < n - 1; i++) { // TRY every pair of positions i < j
			for (int j = i + 1; j < n; j++) {
				if (chars[i] < chars[j]) { // SKIP unless a bigger digit moves earlier
					swap(chars, i, j);
					String s1 = new String(chars);
					String s2 = new String(max);
					if (s1.compareTo(s2) > 0) { // RECORD a new best
						copy(max, chars);
					}
					maxNum(chars, max, k - 1); // RECURSE with one swap fewer
					swap(chars, i, j); // UNDO the swap before the next try
				}
			}
		}
	}

	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	private static void copy(char[] one, char[] two) { // COPY two into one
		for (int i = 0; i < one.length; i++) {
			one[i] = two[i];
		}
	}

	public static void main(String[] args) {
		System.out.println(maxNum("129814999", 4)); // 999984211
		System.out.println(maxNum("2736", 1)); // 7236
		System.out.println(maxNum("9973", 1)); // 9973
	}
}
