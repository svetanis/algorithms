package com.svetanis.algorithms.search.binary.rotated;

// 33. Search in Rotated Sorted Array

public final class RotatedGivenElementNoDuplicatesRecursive {
	// Time Complexity: O(log n)

	public static int search(int[] a, int target) {
		int n = a.length;
		return search(a, 0, n - 1, target);
	}

	public static int search(int[] a, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		int mid = left + (right - left) / 2;
		if (a[mid] == target) {
			return mid;
		}
		if (a[left] <= a[mid]) {
			// a[left ... mid] sorted
			if (target >= a[left] && target < a[mid]) {
				return search(a, left, mid - 1, target);
			}
			return search(a, mid + 1, right, target);
		} else {
			// a[mid ... right] sorted
			if (target >= a[mid] && target <= a[right]) {
				return search(a, mid + 1, right, target);
			}
			return search(a, left, mid - 1, target);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
		System.out.println(search(a1, 3));

		int[] a2 = { 3, 4, 5, 1, 2 };
		System.out.println(search(a2, 3));
		System.out.println(search(a2, 4));

		int[] a3 = { 1, 2, 3, 4 };
		System.out.println(search(a3, 3));
	}
}