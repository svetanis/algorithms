package com.svetanis.algorithms.search.median;

// Median of an unsorted array, by repeated minimum selection.

// Given an unsorted array a of length n >= 1, return its median: the
// middle value when n is odd, the lower of the two middle values when n
// is even -- an int, so nothing is averaged. Rearranges a.

// The median is the k-th smallest for k = (n + 1) / 2. Run selection sort
// only that far: pass i finds the smallest of a[i .. n-1] and swaps it to
// index i, so after k passes a[0 .. k-1] holds the k smallest in order,
// and a[k - 1] is the k-th.

// Time: O(n * k) = O(n^2) -- k = (n + 1) / 2 passes, each over up to n values.
// Space: O(1) -- in place.

// Siblings:
//   MedianSortedArray -- the median when the array is already sorted, O(1).
//   KthSmallestQuickSelectRandomized (search/kthelement) -- the k-th
//     smallest in O(n) average, by partitioning around a random pivot.
//   ClosestToMedianHeap, SlidingWindowMedianHeapRemove -- call minSelect.

public final class MedianUnsortedMinSelect {

	public static int minSelect(int[] a) {
		int n = a.length;
		int k = (n + 1) / 2; // RANK: the median is the k-th smallest; the lower middle when n is even

		for (int i = 0; i < k; ++i) { // PASS: place the (i+1)-th smallest at index i
			int index = i;
			for (int j = i + 1; j < n; ++j) {
				if (a[j] < a[index]) { // MIN: the smallest of a[i .. j] so far
					index = j;
				}
			}
			swap(a, i, index); // SWAP: to the front of the unsorted part
		}
		return a[k - 1]; // REPORT: the k-th smallest
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
		System.out.println("median: " + minSelect(a)); // 10

		int[] a1 = { 0, 1, 2, 3, -2, 4, -4 };
		System.out.println("median: " + minSelect(a1)); // 1

		int[] a2 = { 6, 7, 8, 1, 2, 3, 4, 5, 9, 10 };
		System.out.println("median: " + minSelect(a2)); // 5 -- the lower of 5 and 6
	}
}
