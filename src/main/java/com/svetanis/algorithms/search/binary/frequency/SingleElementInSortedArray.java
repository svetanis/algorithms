package com.svetanis.algorithms.search.binary.frequency;

// 540. Single Element in a Sorted Array

public final class SingleElementInSortedArray {
	// Time Complexity: O(log n)

	public static int single(int[] a) {
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left)/2;
			if (a[mid] != a[mid ^ 1]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return a[left];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 3, 3, 4, 4, 8, 8 };
		System.out.println(single(a1)); // 2

		int[] a2 = { 3, 3, 7, 10, 11, 11 };
		System.out.println(single(a2)); // 10
	}
}