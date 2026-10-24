package com.svetanis.algorithms.greedy;

// 1007. Minimum Domino Rotations For Equal Row
//
// Input: tops[i] and bottoms[i] -- the two halves of domino i, each a number from 1 to 6. Rotating
// a domino swaps its top and bottom. Returns the fewest rotations that make every top the same
// value, or every bottom the same value; -1 if neither can be done. On LeetCode: 2 <= tops.length
// <= 2 * 10^4, bottoms.length == tops.length, 1 <= tops[i], bottoms[i] <= 6.
//
// The one idea: a row can only be filled with a value that appears on every domino, and this file
// tries just one value: the one seen most often across both rows. That choice is safe. Suppose some
// value f works. Then every domino shows f, so each domino adds at most one to any other value's
// count: any other value is seen at most n times, while f is seen at least n times. So the most
// frequent value is f itself or a value tied with it, and a tie means every domino is exactly the
// pair {f, v} -- then v works too and costs the same, because the two counts swap places. With the
// value chosen, one pass counts the dominoes whose top must be rotated to bring the value to the
// bottom (botSwaps) and the dominoes whose bottom must be rotated to bring it to the top
// (topSwaps); a domino showing the value on both halves needs neither. A domino without the value
// ends the pass with -1, and otherwise the cheaper row wins.
//
// Time: O(n) -- one pass to count the values, one pass to count the rotations.
// Space: O(1) -- seven counters.
//
// Siblings: MinDominoRotationEfficient skips the argument and tries every value from 1 to 6, six
// passes of the same counting, and keeps the cheapest.

public final class MinDominoRotation {

	public static int mdr(int[] tops, int[] bottoms) {
		int mfe = mostFrequent(tops, bottoms); // CANDIDATE: the one value it will try
		int n = tops.length;
		int min = n + 1;
		int topSwaps = 0;
		int botSwaps = 0;
		for (int i = 0; i < n; i++) {
			if (tops[i] != mfe && bottoms[i] != mfe) { // STOP: this domino cannot show the value
				return -1;
			}
			if (tops[i] == mfe && bottoms[i] != mfe) { // COUNT a rotation to fill the bottom row
				botSwaps++;
			}
			if (bottoms[i] == mfe && tops[i] != mfe) { // COUNT a rotation to fill the top row
				topSwaps++;
			}
		}
		min = Math.min(min, Math.min(botSwaps, topSwaps)); // TAKE the cheaper row
		return min;
	}

	private static int mostFrequent(int[] tops, int[] bottoms) {
		int[] counts = new int[7];
		for (int i = 0; i < tops.length; i++) {
			counts[tops[i]]++; // COUNT both halves of every domino
			counts[bottoms[i]]++;
		}
		int max = 0;
		int mostFrequent = 0;
		for (int i = 0; i < 7; i++) {
			if (counts[i] > max) { // RECORD the most frequent value
				max = counts[i];
				mostFrequent = i;
			}
		}
		return mostFrequent;
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
