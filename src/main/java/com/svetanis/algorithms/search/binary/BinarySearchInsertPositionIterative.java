package com.svetanis.algorithms.search.binary;

// 35. Search Insert Position

// given a sorted array and a target value
// find the index if the target is found
// if not, return the index where it would
// be if it were inserted in order

public final class BinarySearchInsertPositionIterative {
	// Time Complexity: O(log n)

	public static int binary(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target > a[mid]) {
				left = mid + 1;
			} else if (target < a[mid]) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		return left;
	}

	public static int binary2(int[] a, int target) {
		int left = 0;
		int right = a.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 6 };
		System.out.println(binary(a, 5)); // 2
		System.out.println(binary(a, 2)); // 1
		System.out.println(binary(a, 7)); // 4
		System.out.println(binary(a, 0)); // 0
	}
}