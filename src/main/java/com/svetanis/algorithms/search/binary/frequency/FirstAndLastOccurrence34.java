package com.svetanis.algorithms.search.binary.frequency;

import static com.svetanis.java.base.utils.Print.print;

// 34. Find First and Last Position of Element in Sorted Array

public final class FirstAndLastOccurrence34 {
	// Time Complexity: O(log n)

	public static int[] firstAndLast(int[] a, int target) {
		int left = firstOccurrence(a, target);
		int right = firstOccurrence(a, target + 1);
		if (left == right) {
			return new int[] { -1, -1 };
		}
		return new int[] { left, right - 1 };
	}

	private static int firstOccurrence(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target < a[mid]) {
				right = mid - 1;
			} else if (target > a[mid]) {
				left = mid + 1;
			} else { // a[mid] == k) {
				right = mid - 1; // search left to find the first index
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a = { 5, 7, 7, 8, 8, 10 };
		print(firstAndLast(a, 8)); // [3 4]
		print(firstAndLast(a, 6)); // [-1 -1]
	}
}