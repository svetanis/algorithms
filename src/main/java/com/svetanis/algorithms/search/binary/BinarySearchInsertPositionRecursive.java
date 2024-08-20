package com.svetanis.algorithms.search.binary;

// given a sorted array and a target value
// find the index if the target is found
// if not, return the index where it would
// be if it were inserted in order

public final class BinarySearchInsertPositionRecursive {

	public static int binary(int[] a, int x) {
		// O(log n)
		return binary(a, 0, a.length - 1, x);
	}

	private static int binary(int[] a, int left, int right, int x) {
		if (left > right) {
			return left;
		}
		int mid = left + (right - left) / 2;
		if (x < a[mid]) {
			return binary(a, left, mid - 1, x);
		} else if (x > a[mid]) {
			return binary(a, mid + 1, right, x);
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