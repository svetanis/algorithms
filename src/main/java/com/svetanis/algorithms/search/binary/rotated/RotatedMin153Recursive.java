package com.svetanis.algorithms.search.binary.rotated;

// 153. Find Minimum in Rotated Sorted Array

public final class RotatedMin153Recursive {
	// Time complexity: O(log n)

	public static int min(int[] a) {
		int n = a.length;
		return min(a, 0, n - 1);
	}

	private static int min(int[] a, int left, int right) {
		if (right < left) {
			return a[0];
		}
		if (left == right) {
			return a[left];
		}
		int mid = left + (right - left) / 2;
		// check if element (mid + 1) is min element.
		// consider the cases like {3, 4, 5, 1, 2}
		if (mid < right && a[mid + 1] < a[mid]) {
			return a[mid + 1];
		}
		// check if mid itself is min element
		if (mid > left && a[mid] < a[mid - 1]) {
			return a[mid];
		}
		if (a[right] > a[mid]) {
			return min(a, left, mid - 1);
		}
		return min(a, mid + 1, right);
	}

	public static void main(String[] args) {

		int[] a1 = { 5, 6, 1, 2, 3, 4 };
		System.out.println(min(a1));

		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(min(a2));

		int a3[] = { 1 };
		System.out.println(min(a3));

		int a4[] = { 1, 2 };
		System.out.println(min(a4));

		int a5[] = { 2, 1 };
		System.out.println(min(a5));

		int a6[] = { 5, 6, 7, 1, 2, 3, 4 };
		System.out.println(min(a6));

		int a7[] = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(min(a7));

		int a8[] = { 2, 3, 4, 5, 6, 7, 8, 1 };
		System.out.println(min(a8));

		int a9[] = { 3, 4, 5, 1, 2 };
		System.out.println(min(a9));
	}
}