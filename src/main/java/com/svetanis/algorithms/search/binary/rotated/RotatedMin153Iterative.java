package com.svetanis.algorithms.search.binary.rotated;

import static java.util.Arrays.asList;

import java.util.List;

// 153. Find Minimum in Rotated Sorted Array

// a sorted array of unique integers
// was rotated at an unknown pivot.
// find the index of the minimum 
// element in this array

public final class RotatedMin153Iterative {
	// Time Complexity: O(log n)

	public static int min(int[] a) {
		int left = 0;
		int last = a.length - 1;
		if (a[left] <= a[last]) {
			return a[0];
		}
		int right = last;
		int pivot = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a[mid] <= a[last]) {
				pivot = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return a[pivot];
	}

	public static int min(List<Integer> a) {
		int left = 0;
		int last = a.size() - 1;
		if (a.get(left) <= a.get(last)) {
			return 0;
		}
		int right = last;
		int pivot = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a.get(mid) <= a.get(last)) {
				pivot = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return pivot;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 4, 5, 1, 2 };
		System.out.println(min(a1)); // 1
		int[] a2 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(min(a2)); // 0
		int[] a3 = { 11, 13, 15, 17 };
		System.out.println(min(a3)); // 11

		System.out.println(min(asList(30, 40, 50, 10, 20))); // 3 -> 10
		System.out.println(min(asList(3, 5, 7, 11, 13, 17, 19, 2))); // 7 -> 2
		System.out.println(min(asList(0, 1, 2, 3, 4, 5))); // 0 -> 0
	}
}