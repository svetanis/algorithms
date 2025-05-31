package com.svetanis.algorithms.sorting;

import com.svetanis.java.base.utils.Print;

// 360. Sort Transformed Array

public final class SortTransformedArray {
	// Time Complexity: O(n)

	public static int[] sort(int[] nums, int a, int b, int c) {
		int n = nums.length;
		int start = 0, end = n - 1;
		int index = a < 0 ? 0 : n - 1;
		int[] sorted = new int[n];
		while (start <= end) {
			int left = quadratic(a, b, c, nums[start]);
			int right = quadratic(a, b, c, nums[end]);
			if (a < 0) {
				if (left <= right) {
					sorted[index] = left;
					start++;
				} else {
					sorted[index] = right;
					end--;
				}
				index++;
			} else {
				if (left >= right) {
					sorted[index] = left;
					start++;
				} else {
					sorted[index] = right;
					end--;
				}
				index--;
			}
		}
		return sorted;
	}

	private static int quadratic(int a, int b, int c, int x) {
		return a * x * x + b * x + c;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		Print.print(sort(a, -1, 0, 0));
	}
}
