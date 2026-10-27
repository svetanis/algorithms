package com.svetanis.algorithms.prefixsum;

// 3096. Minimum Levels to Gain More Points
//
// Input: an array a (possible on LeetCode) of n levels; a[i] is 1 when level i can be cleared and
// 0 when it cannot. Clearing a level earns 1 point, failing one costs 1 point. Alice plays levels
// 0, 1, ... in order up to some level, and Bob plays all the rest; each must play at least one.
// Returns the fewest levels Alice must play to end with more points than Bob, or -1 when no split
// does. On LeetCode: 2 <= n <= 10^5, a[i] is 0 or 1.
//
// The one idea: one level is worth +1 when a[i] is 1 and -1 when it is 0, and nothing a player
// does changes that. If Alice plays the first i levels, her points are the running total of the
// first i levels' worths, and Bob's are everything else: (total of all levels) - (Alice's points).
// So one pass over the split points i = 1 .. n - 1, adding one level's worth to Alice each step,
// finds the first i where her points beat his.
//
// Time: O(n) -- one pass for the total, one for the splits.
// Space: O(1) -- the total and Alice's running points.
//
// Siblings: none.

public final class MinLevelsToGainMorePoints {

	public static int minLevels(int[] a) {
		int total = totalScore(a); // ALL levels' worths
		int score = 0; // ALICE: the worths of levels 0 .. i - 1
		for (int i = 1; i < a.length; i++) { // SPLIT: Alice plays i levels; i < n leaves Bob at least one
			score += (a[i - 1] == 0) ? -1 : 1; // WORTH of level i - 1, now Alice's
			if (score > total - score) { // BEATS Bob, who has the rest
				return i;
			}
		}
		return -1; // NO split works
	}

	private static int totalScore(int[] a) {
		int total = 0;
		for (int num : a) {
			total += (num == 0) ? -1 : 1; // WORTH: +1 cleared, -1 failed
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 1, 0 };
		System.out.println(minLevels(a1)); // 1
		int[] a2 = { 1, 1, 1, 1, 1 };
		System.out.println(minLevels(a2)); // 3
		int[] a3 = { 0, 0 };
		System.out.println(minLevels(a3)); // -1
		int[] a4 = { 1, 1 };
		System.out.println(minLevels(a4)); // -1
	}
}
