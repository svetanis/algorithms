package com.svetanis.algorithms.twopointers;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.svetanis.java.base.Pair;

// // 581. Shortest Unsorted Continuous Subarray

// Given an array, find the length of the smallest subarray in it 
// which when sorted will sort the whole array.

// 1. From the beginning and end of the array, find  
//    the first elements that are out of the sorting order. 
//		The two elements will be our candidate subarray.
// 2. Find the maximum and minimum of this subarray.
// 3. Extend the subarray from beginning to include any number 
//    which is bigger than the minimum of the subarray.
// 4. Similarly, extend the subarray from the end to include any number 
//    which is smaller than the maximum of the subarray.

public final class MinWindowSort {
	// Time complexity: O(n)

	public static Pair<Integer, Integer> subArray(int[] a) {
		int n = a.length;
		int left = left(a);
		// if a[] is already sorted
		if (left == n - 1) {
			return Pair.build(-1, -1);
		}
		int right = right(a);
		Pair<Integer, Integer> pair = minMax(a, left, right);
		int start = extendLeft(a, left, pair.getLeft());
		int end = extendRight(a, right, pair.getRight());
		return Pair.build(start, end);
	}

	private static int left(int[] a) {
		int n = a.length;
		int left = 0;
		while (left < n - 1 && a[left] <= a[left + 1]) {
			left++;
		}
		return left;
	}

	private static int right(int[] a) {
		int n = a.length;
		int right = n - 1;
		while (right > 0 && a[right] >= a[right - 1]) {
			right--;
		}
		return right;
	}

	private static Pair<Integer, Integer> minMax(int[] a, int left, int right) {
		int min = a[left];
		int max = a[left];
		for (int i = left + 1; i <= right; ++i) {
			min = min(min, a[i]);
			max = max(max, a[i]);
		}
		return Pair.build(min, max);
	}

	private static int extendLeft(int[] a, int left, int min) {
		while (left > 0 && a[left - 1] > min) {
			left--;
		}
		return left;
	}

	private static int extendRight(int[] a, int right, int max) {
		int n = a.length;
		while (right < n - 1 && a[right + 1] < max) {
			right++;
		}
		return right;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60 };
		System.out.println(subArray(a1));

		int[] a2 = { 1, 2, 3, 4, 5, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println(subArray(a2));

		int[] a3 = { 0, 1, 15, 25, 6, 7, 30, 40, 50 };
		System.out.println(subArray(a3));
	}
}
