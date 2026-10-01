package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// 912. Sort an Array

// Hoare partition: two pointers walk toward each other and swap the pair that is on the
// wrong side, so copies of the pivot value spread over both halves. The pivot comes from a
// random index, swapped to the left end: taking it from the right end can return the whole
// range as the left half, and the recursion would never shrink.

public final class QuickSortSubmit {
	// Time Complexity: O(n log n) average
	// Space Complexity: O(log n) average, the recursion

	private static final Random GENERATOR = new Random();

	public static int[] sortArray(int[] a) {
		quickSort(a, 0, a.length - 1);
		return a;
	}

	public static void quickSort(int[] a, int left, int right) {
		if (left >= right) {
			return;
		}
		int random = randomIndex(left, right);
		swap(a, random, left); // at the left end, the returned split is always below right
		int split = partition(a, left, right);
		quickSort(a, left, split);
		quickSort(a, split + 1, right);
	}

	// Hoare partition around the pivot a[left]: returns split, with a[left..split] <= pivot
	// and a[split + 1..right] >= pivot
	private static int partition(int[] a, int left, int right) {
		int pivot = a[left];
		int start = left - 1;
		int end = right + 1;
		while (start < end) {
			while (a[++start] < pivot) {
			}
			while (a[--end] > pivot) {
			}
			if (start < end) {
				swap(a, start, end);
			}
		}
		return end;
	}

	private static int randomIndex(int left, int right) {
		return left + GENERATOR.nextInt(right - left + 1); // right included
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { 1000, 80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
		System.out.println(Arrays.toString(sortArray(a))); // [-1000, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 1000]
		int[] a1 = { 5, 2, 3, 1 };
		System.out.println(Arrays.toString(sortArray(a1))); // [1, 2, 3, 5]
		int[] a2 = { 5, 1, 1, 2, 0, 0 };
		System.out.println(Arrays.toString(sortArray(a2))); // [0, 0, 1, 1, 2, 5]
		int[] a3 = { -2, 3, -5 };
		System.out.println(Arrays.toString(sortArray(a3))); // [-5, -2, 3]

		int n = 50_000;
		int[] organ = new int[n]; // rises to the middle, then falls
		for (int i = 0; i < n; i++) {
			organ[i] = i < n / 2 ? i : n - i;
		}
		sortArray(organ);
		System.out.println(organ[0] + " " + organ[n - 1]); // 0 25000
	}
}
