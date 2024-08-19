package com.svetanis.algorithms.search.binary.rotated;

// given a sorted array of distinct integer in ascending order
// and is rotated k times around a pivot, find the number of rotations

// number of rotations is equal to index of minimum element

public final class RotationCountNoDuplicatesIterative {

	public static int count(int[] a) {
		// time complexity: O(log n)

		int left = 0, right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;

			if (mid < right && a[mid] > a[mid + 1]) {
				return mid + 1;
			}

			if (mid > left && a[mid - 1] > a[mid]) {
				return mid;
			}
			
			// left side is sorted,
			// pivot is on the right side
			if (a[left] < a[mid]) {
				left = mid + 1;
			} else {
			// right side is sorted,
			// pivot is on the left side
				right = mid - 1;
			}
		}
		// the array is not rotated
		return 0;
	}

	public static void main(String[] args) {
		int[] a = { 15, 18, 2, 3, 6, 12 };
		System.out.println(count(a));
	}
}