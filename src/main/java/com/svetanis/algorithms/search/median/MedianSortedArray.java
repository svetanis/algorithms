package com.svetanis.algorithms.search.median;

// Median of a sorted array, or of one stretch of it.

// Given a sorted array a, return the median of a[start .. end]: the middle
// value when the stretch has odd length, the average of the two middle
// values when it is even. median(a) is the whole array. Takes a non-empty
// stretch and values whose pairwise sum fits in an int, as the code assumes.

// The array is sorted, so the middle is found by index alone. The stretch
// holds n = end - start + 1 values and begins at start, so its upper
// middle is at start + n / 2; for even n the lower middle is the cell
// just before it.

// Time: O(1) -- two index reads.
// Space: O(1)

// Siblings:
//   MedianTwoSortedEqualSizeCompare -- calls median(a, start, end) on
//     stretches that do not start at 0.
//   MedianUnsortedMinSelect -- the median of an UNSORTED array, by
//     selection, O(n^2).

public final class MedianSortedArray {

	public static double median(int[] a) {
		int n = a.length;
		return median(a, 0, n - 1);
	}

	public static double median(int[] a, int start, int end) {
		int n = end - start + 1;
		// start + n/2, not n/2: without the offset every sub-range
		// is measured from index 0 and only start == 0 is right
		int index = start + n / 2; // MIDDLE: the upper middle when n is even
		if (n % 2 == 0) {
			// 2.0, not 2: int division truncates 3.5 to 3 before
			// the widening to double, so the return type lies
			return (a[index] + a[index - 1]) / 2.0; // EVEN: the average of the two middles
		}
		return a[index]; // ODD: the one middle
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 12, 15, 26, 38 };
		System.out.println(median(a1)); // 15.0

		int[] a2 = { 2, 13, 17, 30, 45 };
		System.out.println(median(a2)); // 17.0

		int[] a3 = { 1, 2, 3, 3, 4, 6, 6, 7 };
		System.out.println(median(a3)); // 3.5
	}
}
