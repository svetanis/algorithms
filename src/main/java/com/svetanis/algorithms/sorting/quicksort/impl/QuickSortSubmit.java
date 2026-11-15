package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// 912. Sort an Array
//
// Input: a, an array of ints. Returns a, sorted ascending (sorted in place). On LeetCode:
// 1 <= a.length <= 5 * 10^4, -5 * 10^4 <= a[i] <= 5 * 10^4.
//
// The one idea: pick a pivot value, then move numbers so that everything in a[left..split] is at
// most the pivot and everything in a[split + 1..right] is at least the pivot; then each side is
// sorted by the same method, and nothing has to cross between them again. The split is a Hoare
// partition: two pointers walk toward each other, the left one stops on a number that is not
// smaller than the pivot, the right one on a number that is not larger, and the pair is swapped.
// Copies of the pivot value therefore spread over both halves, so an array of equal numbers splits
// in the middle. The pivot comes from a random index, swapped to the left end: with the pivot at
// the left end the returned split is always below right, so both halves are smaller than the range
// and the recursion shrinks; taking it from the right end can return the whole range as the left
// half, and the recursion would never end.
//
// Time: O(n log n) on average -- a random pivot gives balanced halves on average; the worst case
// is O(n^2), with vanishing probability.
// Space: O(log n) on average -- the recursion.
//
// Siblings: QuickSortRandomized and ThreeWayQuickSortNationalFlag split in three blocks, so the
// equal numbers drop out of the recursion. Partition holds the two-group and three-group splits as
// a reference.

public final class QuickSortSubmit {

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
		quickSort(a, left, split); // LEFT: at most the pivot
		quickSort(a, split + 1, right); // RIGHT: at least the pivot
	}

	// Hoare partition around the pivot a[left]: returns split, with a[left..split] <= pivot
	// and a[split + 1..right] >= pivot
	private static int partition(int[] a, int left, int right) {
		int pivot = a[left];
		int start = left - 1;
		int end = right + 1;
		while (start < end) {
			while (a[++start] < pivot) { // WALK: right until a number that is not smaller
			}
			while (a[--end] > pivot) { // WALK: left until a number that is not larger
			}
			if (start < end) {
				swap(a, start, end); // SWAP: the pair that is on the wrong side
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
