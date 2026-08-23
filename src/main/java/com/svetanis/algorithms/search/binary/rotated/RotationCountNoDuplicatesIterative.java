package com.svetanis.algorithms.search.binary.rotated;

// given a sorted array of distinct integers in ascending order
// and rotated k times around a pivot, find the number of rotations

// the number of rotations is the index of the minimum element,
// so this is 153 returning the index instead of the value:
// a[i] <= a[last] reads F F F F T T T, and the first T is the minimum

public final class RotationCountNoDuplicatesIterative {

	public static int count(int[] a) {
		// time complexity: O(log n)

		int last = a.length - 1;
		int left = 0;
		int right = last;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] <= a[last]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		// an unrotated array is all true, so the first true is index 0
		return left;
	}

	public static void main(String[] args) {
		int[] a1 = { 15, 18, 2, 3, 6, 12 };
		System.out.println(count(a1)); // 2

		int[] a2 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(count(a2)); // 4

		int[] a3 = { 1, 2, 3, 4 };
		System.out.println(count(a3)); // 0
	}
}
