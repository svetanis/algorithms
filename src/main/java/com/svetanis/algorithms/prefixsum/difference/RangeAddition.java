package com.svetanis.algorithms.prefixsum.difference;

import java.util.Arrays;

// 370. Range Addition
//
// Input: n -- an array of n zeros; updates[i] = [start, end, inc] -- add inc to every index from
// start to end, both ends included. Returns the array after all the updates. On LeetCode:
// 1 <= n <= 10^5, 0 <= updates.length <= 10^4, 0 <= start <= end < n, -1000 <= inc <= 1000, so
// a value is at most 10^7 in size and fits in an int.
//
// The one idea: one cell a[i] first holds the change in value from index i - 1 to index i: +inc
// where an update starts, -inc at end + 1, because index end itself still gets inc. An update
// that ends at the last index has no end + 1 cell and needs none. A running sum, written back
// into the same array, turns each cell's change into that index's final value.
//
// Time: O(n + k) -- two marks per update (k updates), then one pass over the array.
// Space: O(n) -- the returned array; the running sum is written into it in place.

public final class RangeAddition {

	public static int[] rangeAddition(int n, int[][] updates) {
		int[] a = new int[n]; // MARKS
		for (int[] update : updates) {
			int srt = update[0];
			int end = update[1];
			int inc = update[2];
			a[srt] += inc; // START
			if (end + 1 < n) { // STOP: end is included, so the change undoes one index later
				a[end + 1] -= inc;
			}
		}
		for (int i = 1; i < n; i++) { // WALK: running sum, in place
			a[i] += a[i - 1]; // now: the final value at index i
		}
		return a; // RETURN
	}

	public static void main(String[] args) {
		int[][] updates = { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } };
		System.out.println(Arrays.toString(rangeAddition(5, updates))); // [-2, 0, 3, 5, 3]

		int[][] updates2 = { { 2, 4, 6 }, { 5, 6, 8 }, { 1, 9, -4 } };
		System.out.println(Arrays.toString(rangeAddition(10, updates2))); // [0, -4, 2, 2, 2, 4, 4, -4, -4, -4]
	}
}
