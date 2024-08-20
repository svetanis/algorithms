package com.svetanis.algorithms.search.binary.diff;

// given an array of distinct integers
// find a local minima in it

// an element is a local minimum if
// it is less than or equal to both its neighbors

// if middle element is not minima and 
// its left neighbor is smaller than it
// then left half must have a local minima

// if middle element is not minima and
// its right neighbor is smaller that it
// then right half must have a local minima

public final class LocalMinIterative {

	public static int localMin(int[] a) {
		// Time Complexity: O(log n)

		int n = a.length;
		int left = 0;
		int right = n - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			// compare middle element with its neighbors if exist
			boolean one = mid == 0 || a[mid - 1] > a[mid];
			boolean two = mid == n - 1 || a[mid + 1] > a[mid];
			if (one && two) {
				return mid;
			} else if (mid > 0 && a[mid - 1] < a[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 6, 3, 14, 5, 7, 4 }; // 3
		int[] a2 = { 23, 8, 15, 2, 3 }; // 8
		int[] a3 = { 1, 2, 3 }; // 1
		int[] a4 = { 3, 2, 1 }; // 1
		int[] a5 = { 4, 3, 1, 14, 16, 40 }; // 1

		System.out.println(localMin(a1)); // 2
		System.out.println(localMin(a2)); // 1
		System.out.println(localMin(a3)); // 0
		System.out.println(localMin(a4)); // 2
		System.out.println(localMin(a5)); // 2
	}
}
