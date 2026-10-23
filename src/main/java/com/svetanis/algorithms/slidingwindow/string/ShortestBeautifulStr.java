package com.svetanis.algorithms.slidingwindow.string;

// 2904. Shortest and Lexicographically Smallest Beautiful String
//
// Input: a binary string s (only '0' and '1') and a number k >= 1.
// Return: the shortest substring holding exactly k ones; among several of that
// length, the lexicographically smallest. "" when no substring holds k ones.
// k = 0 is outside the problem; no rule for it is known, and the result for it
// is not meaningful ("" for "101", "0" for "0").
//
// The one idea: a shortest answer never starts with a 0 -- dropping that 0 keeps
// the same ones in a shorter window -- and never holds more than k ones. So the
// shrink loop drops letters from the left while the window starts with a 0 or
// holds too many ones. When the window then holds exactly k ones, it starts at
// the k-th one counting back from the newest letter, so it is the shortest window
// with k ones that ends there. It replaces the best one when it is shorter, or the
// same length and lexicographically smaller.
// right++ comes before the measurement, so the window measured is [left, right):
// it holds left but not right, and its length is right - left.
//
// Siblings: none -- no other file in the repo solves 2904.
//
// Time: O(n * n) -- each letter enters and leaves once, but every step copies the
//   window with substring and may compare it with compareTo, each up to n letters.
// Space: O(n) -- the window copy and the best substring.

public final class ShortestBeautifulStr {

	public static String sbs(String s, int k) {
		int left = 0;
		int right = 0;
		int ones = 0;
		String sbs = "";
		while (right < s.length()) {
			ones += s.charAt(right) - '0'; // ADD: '1' adds one, '0' adds nothing
			while ((left < right && s.charAt(left) == '0') || ones > k) { // SHRINK: a leading 0, or too many ones
				ones -= s.charAt(left) - '0';
				left++;
			}
			right++; // the window is now [left, right)
			String ss = s.substring(left, right);
			boolean one = sbs.isEmpty(); // nothing recorded yet
			boolean two = right - left < sbs.length(); // strictly shorter
			boolean three = right - left == sbs.length(); // same length ...
			boolean four = ss.compareTo(sbs) < 0; // ... and lexicographically smaller
			if (ones == k && (one || two || (three && four))) {
				sbs = ss; // MEASURE: exactly k ones and better than the best so far
			}
		}
		return sbs;
	}

	public static void main(String args[]) {
		System.out.println(sbs("100011001", 3)); // 11001
		System.out.println(sbs("1011", 2)); // 11
		System.out.println(sbs("000", 1)); // ""
	}
}