package com.svetanis.algorithms.search.binary;

import static java.util.Arrays.asList;

import java.util.List;

// given an array of integers sorted in
// increasing order and a target, find
// the index of the first element in the
// array that is larger than or equal to
// the target. assume that it is guaranteed
// to find a satisfying number

public final class FirstLargerThanTarget {
	// Time Complexity: O(log n)

	public static int vanilla(List<Integer> arr, int target) {
		int n = arr.size();
		int left = 0;
		int right = n - 1;
		int boundary = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid) >= target) {
				boundary = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return boundary;
	}

	public static int binary(List<Integer> arr, int target) {
		int n = arr.size();
		int left = 0;
		int right = n - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if ((mid == 0 || arr.get(mid - 1) < target) && arr.get(mid) >= target) {
				return mid;
			} else if (arr.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(vanilla(asList(1, 3, 3, 5, 8, 8, 10), 2)); // 1
		System.out.println(vanilla(asList(2, 3, 5, 7, 11, 13, 17, 19), 6)); // 3
		System.out.println();
		System.out.println(binary(asList(1, 3, 3, 5, 8, 8, 10), 2)); // 1
		System.out.println(binary(asList(2, 3, 5, 7, 11, 13, 17, 19), 6)); // 3
	}
}
