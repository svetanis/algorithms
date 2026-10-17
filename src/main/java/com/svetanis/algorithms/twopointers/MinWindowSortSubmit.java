package com.svetanis.algorithms.twopointers;

// 581. Shortest Unsorted Continuous Subarray
//
// Input: an array of integers, at least one.
// Return: the length of the shortest subarray which, when sorted, sorts the whole
// array; 0 when the array is already sorted.
//
// The one idea: a number has to move exactly when something before it is bigger, or
// something after it is smaller. Walking left to right with the running maximum, the
// LAST index whose number is below that maximum is the window's end. Walking right to
// left with the running minimum, the last index whose number is above that minimum is
// the window's start. One loop does both walks at once: i from the front, n - 1 - i
// from the back. The tests are strict, so a number EQUAL to the maximum before it
// stays where it is.
//
// Sibling: twopointers.MinWindowSort -- finds the out-of-order core from the two ends,
//   then grows it in four separate passes, and returns the window's two indices.
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class MinWindowSortSubmit {

	public static int subArrayLen(int[] a) {
		int n = a.length;
		int left = -1;               // START: no number has had to move yet
		int right = -1;
		int min = Integer.MAX_VALUE; // the smallest number after n - 1 - i
		int max = Integer.MIN_VALUE; // the largest number before i
		for (int i = 0; i < n; i++) {
			if (max > a[i]) {
				right = i;           // RECORD: something before a[i] is bigger -- it must move
			} else {
				max = a[i];          // RAISE the running maximum
			}

			if (min < a[n - i - 1]) {
				left = n - i - 1;                  // RECORD: something after it is smaller -- it must move
			} else {
				min = a[n - i - 1];                // LOWER the running minimum
			}
		}
		return right == -1 ? 0 : right - left + 1; // SORTED when nothing had to move
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 6, 4, 8, 10, 9, 15 };
		System.out.println(subArrayLen(a1)); // 5

		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(subArrayLen(a2)); // 0

		int[] a3 = { 1 };
		System.out.println(subArrayLen(a3)); // 0

		int[] a4 = { 1, 3, 2, 2, 2 };
		System.out.println(subArrayLen(a4)); // 4 -- every 2 is below the 3 before it
	}
}
