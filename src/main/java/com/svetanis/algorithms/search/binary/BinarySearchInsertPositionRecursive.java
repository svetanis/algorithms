package com.svetanis.algorithms.search.binary;

// 35. Search Insert Position

// given a sorted array and a target value
// find the index if the target is found
// if not, return the index where it would
// be if it were inserted in order

public final class BinarySearchInsertPositionRecursive {
	// Time Complexity: O(log n)

	public static int binary(int[] a, int target) {
		return binary(a, 0, a.length - 1, target);
	}

	private static int binary(int[] a, int left, int right, int target) {
		if (left > right) {
			return left;
		}
		int mid = left + (right - left) / 2;
		if (target < a[mid]) {
			return binary(a, left, mid - 1, target);
		} else if (target > a[mid]) {
			return binary(a, mid + 1, right, target);
		} else {
			return mid;
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 6 };
		System.out.println(binary(a, 5)); // 2
		System.out.println(binary(a, 2)); // 1
		System.out.println(binary(a, 7)); // 4
		System.out.println(binary(a, 0)); // 0
	}
}