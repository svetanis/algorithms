package com.svetanis.algorithms.search.median;

// 4. Median of Two Sorted Arrays

// Given two sorted arrays a1 and a2 of size n and m, return the median of
// the two together, in O(log (n + m)) time.
// Takes 0 <= n, m <= 1000, 1 <= n + m <= 2000 and values in
// -10^6 .. 10^6. Two empty arrays throw.

// Reduce the median to the k-th smallest of both arrays together. The
// lower middle has rank (n + m + 1) / 2 and the upper (n + m + 2) / 2;
// they are the same rank when n + m is odd, so averaging the two answers
// needs no odd/even branch.
// To find the k-th smallest, read the k/2-th value of each array. The one
// that is smaller ends a block of k/2 values that all sit below the k-th
// smallest -- at most k - 1 values can -- so drop that block and look for
// the (k - k/2)-th in what is left. An array too short to have a k/2-th
// value stands in as Integer.MAX_VALUE, so the other array's block is
// the one dropped. k halves every call; the arrays are never copied, only
// start1 and start2 move.

// Time: O(log (n + m)) -- k starts near (n + m) / 2 and halves every call; two searches.
// Space: O(log (n + m)) -- the recursion depth.

// Siblings:
//   MedianTwoSortedArraysPartition -- binary search on the cut: how many the
//     left half takes from the shorter array, O(log min(n, m)), no recursion.
//   MedianTwoSortedEqualSizeBinary, ...Compare, ...Merge -- the same
//     question for two arrays of equal length.

public final class MedianTwoSortedArraysKthElement {

	public static double median(int[] a1, int[] a2) {
		// no sort: both inputs arrive sorted, and sorting one of them
		// mutates the caller's array and costs more than the search
		int n = a1.length;
		int m = a2.length;
		int target1 = (n + m + 1) / 2; // LOWER: the rank of the lower middle
		int target2 = (n + m + 2) / 2; // UPPER: the rank of the upper middle, the same when n + m is odd
		int left = kthElement(a1, 0, a2, 0, target1);
		int right = kthElement(a1, 0, a2, 0, target2);
		return (left + right) / 2.0; // AVERAGE: ODD averages one value with itself; 2.0, not 2
	}

	private static int kthElement(int[] a1, int start1, int[] a2, int start2, int k) {
		int n = a1.length;
		int m = a2.length;
		if (start1 >= n) { // EMPTY: a1 is used up, the k-th is in a2
			return a2[start2 + k - 1];
		}
		if (start2 >= m) { // EMPTY: a2 is used up, the k-th is in a1
			return a1[start1 + k - 1];
		}
		if (k == 1) { // FIRST: the smaller of the two fronts
			return Math.min(a1[start1], a2[start2]);
		}
		int mid = k / 2; // HALVE: compare the k/2-th value of each array
		boolean one = start1 + mid - 1 < n;
		boolean two = start2 + mid - 1 < m;
		int mid1 = one ? a1[start1 + mid - 1] : Integer.MAX_VALUE; // SENTINEL: too short reads as infinite
		int mid2 = two ? a2[start2 + mid - 1] : Integer.MAX_VALUE;

		if (mid1 < mid2) { // TEST: which block of k/2 ends lower
			return kthElement(a1, start1 + mid, a2, start2, k - mid); // DROP: a1's block, all below the k-th
		} else {
			return kthElement(a1, start1, a2, start2 + mid, k - mid); // DROP: a2's block, all below the k-th
		}
	}

	public static void main(String[] args) {
		int[] s1 = { 1, 3 };
		int[] p1 = { 2 };
		System.out.println(median(s1, p1)); // 2.0

		int[] s2 = { 1, 2 };
		int[] p2 = { 3, 4 };
		System.out.println(median(s2, p2)); // 2.5

		int[] s3 = { 0, 2, 5, 6, 8 };
		int[] p3 = { 1, 3, 7 };
		System.out.println(median(s3, p3)); // 4.0

		int[] s4 = { 0, 2, 5, 6, 8 };
		int[] p4 = { 1, 3, 7, 9 };
		System.out.println(median(s4, p4)); // 5.0

	}
}