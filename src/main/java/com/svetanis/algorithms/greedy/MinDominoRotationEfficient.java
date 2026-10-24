package com.svetanis.algorithms.greedy;

// 1007. Minimum Domino Rotations For Equal Row
//
// Input: tops[i] and bottoms[i] -- the two halves of domino i, each a number from 1 to 6. Rotating
// a domino swaps its top and bottom. Returns the fewest rotations that make every top the same
// value, or every bottom the same value; -1 if neither can be done. On LeetCode: 2 <= tops.length
// <= 2 * 10^4, bottoms.length == tops.length, 1 <= tops[i], bottoms[i] <= 6.
//
// The one idea: a domino has only six possible values, so try each one as the value to fill a row
// with, and keep the cheapest that works. For one value, a single pass decides each domino on its
// own: if neither half shows the value, the value cannot fill a row (-1); if only the top shows
// it, filling the bottom row costs one rotation (botSwaps); if only the bottom shows it, filling
// the top row costs one (topSwaps); if both halves show it, neither row needs a rotation. No
// domino's choice affects another's, so the counts are exact, and the cheaper row is the answer
// for that value.
//
// Time: O(n) -- six passes over the dominoes, one per value.
// Space: O(1) -- a few counters.
//
// Siblings: MinDominoRotation tries only the value seen most often, after an argument that no
// other value can do better; it makes two passes instead of six.

public final class MinDominoRotationEfficient {

	public static int mdr(int[] tops, int[] bottoms) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 6; i++) { // TRY every value a domino can show
			int swaps = rotations(tops, bottoms, i);
			if (swaps != -1) {
				min = Math.min(min, swaps); // RECORD the cheapest value that works
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min; // RETURN: -1 when no value works
	}

	private static int rotations(int[] tops, int[] bottoms, int value) {
		int topSwaps = 0;
		int botSwaps = 0;
		int n = tops.length;
		for (int i = 0; i < n; i++) {
			if (tops[i] != value && bottoms[i] != value) { // STOP: this domino cannot show it
				return -1;
			} else if (bottoms[i] != value) { // COUNT: only the top shows it -- rotate to fill the bottom
				botSwaps++;
			} else if (tops[i] != value) { // COUNT: only the bottom shows it -- rotate to fill the top
				topSwaps++;
			}
		}
		return Math.min(botSwaps, topSwaps); // RETURN: the cheaper row
	}

	public static void main(String[] args) {
		int[] t1 = { 2, 1, 2, 4, 2, 2 };
		int[] b1 = { 5, 2, 6, 2, 3, 2 };
		System.out.println(mdr(t1, b1)); // 2

		int[] t2 = { 3, 5, 1, 2, 3 };
		int[] b2 = { 3, 6, 3, 3, 4 };
		System.out.println(mdr(t2, b2)); // -1

		int[] t3 = { 2, 2, 2, 4, 4, 4 };
		int[] b3 = { 4, 3, 4, 2, 2, 2 };
		System.out.println(mdr(t3, b3)); // 3

	}
}
