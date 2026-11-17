package com.svetanis.algorithms.sorting.mergesort.inversions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Surpasser count of each element (GeeksforGeeks; no LeetCode number)
//
// Takes an array a of distinct integers. Returns, for each position i, its surpasser count: how many
// elements after position i are larger than a[i]. The answer is in a's order; a is not changed.
//
// Count the opposite first: how many elements after a[i] are smaller. That is what a merge sees.
// While merging two sorted halves, count is how many right-half elements have been taken so far,
// and every one of them is smaller than the left-half element being taken now and came after it in
// the array. Adding count to that element at each merge it takes part in totals all the smaller
// elements after it. Then, of the n - 1 - i elements after position i, the rest are larger:
//   surpasser = (n - 1 - i) - smaller
// The totals are kept by value, which is why the values must be distinct.
//
// The same merge as InversionsCountNewBuffer, which adds the count up for the whole array instead
// of keeping it per element. LC 315, Count of Smaller Numbers After Self, asks for the smaller
// counts themselves; its solutions are in com.svetanis.algorithms.todo.bit.

public final class SurpasserCountMergeSort {
	// Time: O(n log n) -- log n levels of merging, each level touching every element once
	// Space: O(n) -- the copy, the map and the largest merge buffer

	public static int[] surpasser(int[] a) {
		int n = a.length;
		Map<Integer, Integer> smaller = new HashMap<>(); // COUNT: value -> smaller elements after it
		int[] copy = Arrays.copyOf(a, n); // COPY: the merge sorts this one; a keeps its order
		mergeSort(copy, 0, n - 1, smaller); // SORT: fills smaller as a side effect
		int[] surpasser = new int[n];
		for (int i = 0; i < n; i++) { // WALK every position, the last one too: its answer is 0
			// DEFAULT: an element never in a left half (the last one) collected nothing
			surpasser[i] = n - 1 - i - smaller.getOrDefault(a[i], 0);
		}
		return surpasser;
	}

	private static void mergeSort(int[] a, int left, int right, Map<Integer, Integer> smaller) {
		if (right > left) {
			int mid = left + (right - left) / 2;
			mergeSort(a, left, mid, smaller);
			mergeSort(a, mid + 1, right, smaller);
			merge(a, left, mid + 1, right, smaller);
		}
	}

	// mid here is the first index of the right half: the left half is left..mid - 1
	private static void merge(int[] a, int left, int mid, int right, Map<Integer, Integer> smaller) {
		int count = 0; // COUNT: right-half elements taken so far, each smaller than what is left on the left
		int i = left; // i is index for left subarray
		int j = mid; // j is index for right subarray
		int k = 0; // k is index for merged array

		int[] temp = new int[right - left + 1]; // BUFFER: the size of this range, not of the whole array
		while (i <= mid - 1 && j <= right) {
			if (a[i] <= a[j]) {
				smaller.merge(a[i], count, Integer::sum); // RECORD: a[i] goes now, count smaller ones came after it
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++]; // TAKE from the right half
				count++;
			}
		}

		while (i <= mid - 1) { // DRAIN left: every right-half element was smaller than these
			smaller.merge(a[i], count, Integer::sum);
			temp[k++] = a[i++];
		}

		while (j <= right) { // DRAIN right
			temp[k++] = a[j++];
		}

		for (i = left; i <= right; i++) { // COPY BACK the merged range
			a[i] = temp[i - left];
		}
	}

	public static void main(String[] args) {
		int[] a = { 2, 7, 5, 3, 0, 8, 1 };
		System.out.println(Arrays.toString(surpasser(a))); // [4, 1, 1, 1, 2, 0, 0]
	}
}
