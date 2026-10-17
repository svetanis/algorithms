package com.svetanis.algorithms.twopointers;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;

// 581. Shortest Unsorted Continuous Subarray
//
// Input: an array of integers, at least one.
// Return: {start, end}, the first and last index of the shortest subarray which, when
// sorted, sorts the whole array; {-1, -1} when the array is already sorted.
// LeetCode asks for the length: end - start + 1, or 0.
//
// The one idea: the stretch that is out of order seen from the two ends is only the
// core. Sorting it moves its smallest and largest numbers, so it must grow:
//   1. From the front, find where the order first breaks; from the back, the same.
//      Those two positions bound the core.
//   2. Find the smallest and largest numbers inside the core.
//   3. Grow the start leftward over every number bigger than the core's smallest --
//      the smallest has to land in front of it.
//   4. Grow the end rightward over every number smaller than the core's largest.
// The tests are strict: a number EQUAL to the core's smallest may stay in front of it,
// so duplicates do not widen the window.
//
// Sibling: twopointers.MinWindowSortSubmit -- one loop that walks from both ends at
//   once and returns only the length.
//
// Time: O(n) -- each of the four steps is one pass over part of the array.
// Space: O(1).

public final class MinWindowSort {

	public static int[] subArray(int[] a) {
		int n = a.length;
		int left = left(a);
		if (left == n - 1) {
			return new int[] { -1, -1 }; // SORTED: the front run reached the end
		}
		int right = right(a);
		int[] minMax = minMax(a, left, right);
		int start = extendLeft(a, left, minMax[0]);
		int end = extendRight(a, right, minMax[1]);
		return new int[] { start, end };
	}

	// the last index of the sorted run at the front
	private static int left(int[] a) {
		int n = a.length;
		int left = 0;
		while (left < n - 1 && a[left] <= a[left + 1]) { // STOP at the first drop
			left++;
		}
		return left;
	}

	// the first index of the sorted run at the back
	private static int right(int[] a) {
		int n = a.length;
		int right = n - 1;
		while (right > 0 && a[right] >= a[right - 1]) { // STOP at the first rise, read backwards
			right--;
		}
		return right;
	}

	// {smallest, largest} of a[left..right]
	private static int[] minMax(int[] a, int left, int right) {
		int min = a[left];
		int max = a[left];
		for (int i = left + 1; i <= right; ++i) {
			min = min(min, a[i]); // RECORD the core's smallest
			max = max(max, a[i]); // RECORD the core's largest
		}
		return new int[] { min, max };
	}

	private static int extendLeft(int[] a, int left, int min) {
		while (left > 0 && a[left - 1] > min) { // GROW: bigger than the core's smallest
			left--;
		}
		return left;
	}

	private static int extendRight(int[] a, int right, int max) {
		int n = a.length;
		while (right < n - 1 && a[right + 1] < max) { // GROW: smaller than the core's largest
			right++;
		}
		return right;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60 };
		System.out.println(Arrays.toString(subArray(a1))); // [3, 8]

		int[] a2 = { 1, 2, 3, 4, 5, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println(Arrays.toString(subArray(a2))); // [5, 9]

		int[] a3 = { 0, 1, 15, 25, 6, 7, 30, 40, 50 };
		System.out.println(Arrays.toString(subArray(a3))); // [2, 5]

		int[] a4 = { 2, 6, 4, 8, 10, 9, 15 };
		System.out.println(Arrays.toString(subArray(a4))); // [1, 5]

		int[] a5 = { 1, 2, 3, 4 };
		System.out.println(Arrays.toString(subArray(a5))); // [-1, -1]
	}
}
