package com.svetanis.algorithms.search.kthelement;

import java.util.Arrays;
import java.util.Random;

// 215. Kth Largest Element in an Array

// Given an integer array a and k (1 <= k <= a.length), return the kth largest
// element -- the one at position k if the array were sorted from biggest to
// smallest. Duplicates count separately. Rearranges a.

// A random pivot splits the stretch into three blocks: larger than the pivot on the
// left, equal to it in the middle, smaller on the right. k counts from the left end of
// the stretch, so the three block sizes say at once which block holds the answer. A k
// inside the equal block is found in one pass, so an array of equal numbers takes one
// pass.

// Time: O(n) average -- each call is linear in its stretch, and a random pivot
//   cuts the stretch by a constant fraction on average; O(n^2) worst, with
//   every pivot unlucky
// Space: O(log n) average, the recursion -- one call per stretch

// Siblings, LC 215 five ways:
//   KthLargestMinHeapPQ.kthLargest           -- min-heap of k, seeded with the first k, O(n log k)
//   KthLargestMinHeapPQ.kthLargestSingleLoop -- the same heap in one loop, no seeding
//   KthLargestMaxHeapPQ                      -- max-heap of all n, polled k - 1 times
//   KthLargestQuickSelectRandomized          -- this file: recursive, three groups
//   KthLargestQuickSelectIterative           -- the same search in a loop, two groups
// KthSmallestQuickSelectRandomized is the mirror: < and > swapped in the partition.

public final class KthLargestQuickSelectRandomized {

	private static final Random GENERATOR = new Random();

	public static int select(int[] a, int k) {
		int n = a.length;
		return select(a, 0, n - 1, k); // SEARCH the whole array for rank k
	}

	private static int select(int[] a, int left, int right, int k) {
		Block equal = partition(a, left, right, randomIndex(left, right)); // PARTITION around a random pivot
		int larger = equal.first() - left; // COUNT: numbers larger than the pivot
		int notSmaller = equal.last() - left + 1; // COUNT: the larger ones plus the pivot's copies
		if (k <= larger) {
			return select(a, left, equal.first() - 1, k); // KEEP the larger block: rank k is in it
		}
		if (k <= notSmaller) {
			return a[equal.first()]; // REPORT: rank k lands in the equal block
		}
		return select(a, equal.last() + 1, right, k - notSmaller); // KEEP the smaller block: k is a rank, so drop the notSmaller passed
	}

	// larger elements to the left, equal ones in the middle, smaller ones to the right
	private static Block partition(int[] a, int left, int right, int index) {
		int pivot = a[index];
		int larger = left; // SLOT: where the next larger number goes
		int current = left; // READ: the next number to place
		int smaller = right; // SLOT: where the next smaller number goes, from the right end
		while (current <= smaller) { // WALK until the unread middle is empty
			if (a[current] > pivot) {
				swap(a, larger, current); // MOVE it into the larger block, on the left
				larger++;
				current++;
			} else if (a[current] < pivot) {
				swap(a, current, smaller); // MOVE it into the smaller block, on the right
				smaller--; // the number swapped in is unread, so current stays
			} else {
				current++; // KEEP an equal number where it is
			}
		}
		return new Block(larger, smaller); // REPORT the equal block
	}

	private static int randomIndex(int left, int right) {
		return GENERATOR.nextInt(right - left + 1) + left; // PICK: right included
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i]; // SWAP through a temporary
		a[i] = a[j];
		a[j] = temp;
	}

	// the equal block, first and last index inclusive
	private record Block(int first, int last) {
	}

	public static void main(String[] args) {
		int[] a = { 6, 7, 3, 1, 5, 9, 11 };
		System.out.println(select(a, 4)); // 6

		int[] a1 = { 3, 2, 1, 5, 6, 4 };
		System.out.println(select(a1, 2)); // 5

		int[] a2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		System.out.println(select(a2, 4)); // 4

		int[] equal = new int[100_000];
		Arrays.fill(equal, 7);
		System.out.println(select(equal, 100_000)); // 7
	}
}
