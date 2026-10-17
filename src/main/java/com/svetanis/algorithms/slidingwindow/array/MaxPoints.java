package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// Maximum points from k moves around index i -- no LeetCode number; source unknown

// collect maximum points in array by moving either in single or
// both directions from given index i and making k moves.

// What the method computes: the largest sum of k + 1 consecutive
// numbers (the whole array if it is shorter) among the blocks that
// contain index i. That assumes the k moves cover k + 1 cells in one
// unbroken block around i; the statement above does not say whether
// turning back costs extra moves. Takes 0 <= i < a.length and k >= 0;
// numbers may be negative.

// Every block that contains i starts somewhere in [i - k, i], so the
// window starts at the leftmost such block and slides right until its
// right end passes i + k (or the array ends).

// Time: O(min(n, k)) -- at most k + 1 numbers to build, then at most k slides
// Space: O(1) -- the running sum and the best sum

// Siblings:
//   fixed.MaxScore (1423) -- k cards taken from the two ENDS of the row,
//     not around an index: a different problem despite the name.
//   fixed.MaxSumSubArraySizeK -- the same fixed window over every block
//     of size k, with no index the block must contain.

public final class MaxPoints {

	public static int maxPoints(int[] a, int k, int i) {
		// Time Complexity: O(min(n, k))
		// Space Complexity: O(1)
		int n = a.length;
		int sum = 0;
		int left = (k > i) ? 0 : i - k;

		for (int j = left; j <= left + k && j < n; j++) {
			sum += a[j]; // BUILD: the leftmost block that contains i
		}

		int max = sum;
		for (int j = left + k + 1; j < n && j <= i + k; j++) { // STOP: past i + k the block no longer holds i
			sum += a[j] - a[j - k - 1]; // IN a[j], OUT a[j - k - 1]
			max = max(max, sum); // RECORD
		}
		return max;
	}

	public static void main(String[] args) {

		int[] a1 = { 5, 6, 4, 2, 8, 3, 1 };
		System.out.println(maxPoints(a1, 3, 3)); // 20 : 6,4,2,8

		int[] a2 = { 5, 6, 4, 2, 8, 3, 1 };
		System.out.println(maxPoints(a2, 2, 5)); // 13 : 2,8,3

	}
}
