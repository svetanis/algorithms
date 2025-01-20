package com.svetanis.algorithms.search.binary.rotated;

// 154. Find Minimum in Rotated Sorted Array II

public final class RotatedMin154DuplicatesIterative {
	// Time complexity: O(n)

	public static int binary(int[] a) {
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] > a[right]) {
				left = mid + 1;
			} else if (a[mid] < a[right]) {
				right = mid;
			} else {
				right--;
			}
		}
		return a[left];
	}

	public static void main(String[] args) {
		int a1[] = { 1, 3, 5 };
		System.out.println(binary(a1)); // 1

		int a2[] = { 2, 2, 2, 0, 1 };
		System.out.println(binary(a2)); // 0

		int a3[] = { 1, 3, 3 };
		System.out.println(binary(a3)); // 1

	}
}