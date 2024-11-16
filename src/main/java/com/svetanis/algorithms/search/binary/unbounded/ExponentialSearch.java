package com.svetanis.algorithms.search.binary.unbounded;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;
import static java.lang.Math.min;

import com.svetanis.algorithms.sorting.interval.Interval;

// Exponential Search involves two steps:
// 1. find range where element is present
// 2. do binary search in above found range

public final class ExponentialSearch {

	public static int exponential(int[] a, int x) {
		// Time Complexity: O(log n)

		if (a[0] == x) {
			return 0;
		}
		Interval range = range(a, x);
		return binarySearch(a, range.start, range.end, x);
	}

	private static Interval range(int[] a, int x) {
		int n = a.length;

		int i = 1;
		while (i < n && a[i] <= x) {
			i = i * 2;
		}

		int left = i / 2;
		int right = min(i, n);
		return new Interval(left, right);
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 4, 10, 40 };
		System.out.println(exponential(a, 10));
	}
}
