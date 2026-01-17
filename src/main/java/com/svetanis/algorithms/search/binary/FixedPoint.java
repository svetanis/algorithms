package com.svetanis.algorithms.search.binary;

// 1064. Fixed Point

public final class FixedPoint {
	// Time Complexity: O(log n)

	public static int fixedPoint(int[] a) {
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] >= mid) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return a[left] == left ? left : -1;
	}

	public static int fixedPointBoundary(int[] a) {
		int left = 0;
		int right = a.length - 1;
		int boundary = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a[mid] == mid) {
				boundary = mid;
				right = mid - 1;
			} else if (a[mid] > mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return boundary;
	}

	public static void main(String[] args) {
		int[] a = { -10, -5, 0, 3, 7 };
		System.out.println(fixedPoint(a)); // 3
		int[] a1 = { 0, 2, 5, 8, 17 };
		System.out.println(fixedPoint(a1)); // 0
		int[] a2 = { -10, -5, 3, 4, 7, 9 };
		System.out.println(fixedPoint(a2)); // -1
	}
}