package com.svetanis.algorithms.search.kthelement;

import java.util.Random;

// 215. Kth Largest Element in an Array

// Given an integer array nums and k (1 <= k <= nums.length), return the kth
// largest element -- the one at position k if the array were sorted from
// biggest to smallest. Duplicates count separately. Rearranges nums.

// The kth largest sits at index n - k of the ascending sorted array. One partition puts the
// pivot exactly where it belongs in sorted order: its index is how many numbers are smaller.
// If that index is the target, done; otherwise keep only the side that holds the target.
// A random pivot each pass defeats sorted input.
//
// The partition splits two ways, so an array of equal numbers still costs one pass per
// number: O(n^2) comparisons, though the loop never grows the stack. Splitting three ways
// (larger, equal, smaller), as KthLargestQuickSelectRandomized does, finds a k inside the
// equal block in one pass.

// Time: O(n) average -- each pass is linear in its stretch, and a random pivot
//   cuts the stretch by a constant fraction on average; O(n^2) worst, e.g.
//   every number equal, where each pass places one number
// Space: O(1) -- a loop over two indexes, working inside the caller's array

// Siblings, LC 215 five ways:
//   KthLargestMinHeapPQ.kthLargest           -- min-heap of k, seeded with the first k, O(n log k)
//   KthLargestMinHeapPQ.kthLargestSingleLoop -- the same heap in one loop, no seeding
//   KthLargestMaxHeapPQ                      -- max-heap of all n, polled k - 1 times
//   KthLargestQuickSelectRandomized          -- the same search as a recursion, three groups
//   KthLargestQuickSelectIterative           -- this file: a loop, two groups
// KthSmallestQuickSelectRandomized is the mirror, for the kth smallest.

public final class KthLargestQuickSelectIterative {

	private static final Random GENERATOR = new Random();

	public static int select(int[] nums, int k) {
		int n = nums.length;
		int low = 0; // STRETCH: the target index n - k is always inside low .. high
		int high = n - 1;
		while (low < high) { // NARROW until one index is left
			swap(nums, randomIndex(low, high), high); // PIVOT: random every pass -- each pass is a new stretch
			int pivot = partition(nums, low, high); // PARTITION: the index where the pivot landed
			if (pivot == n - k) {
				return nums[pivot]; // REPORT: the pivot landed on the target
			}
			if (pivot < n - k) {
				low = pivot + 1; // KEEP the right side: the pivot is placed, and it is not the answer
			} else {
				high = pivot - 1; // KEEP the left side
			}
		}
		return nums[low]; // REPORT: one index left, it is the target
	}

	// smaller elements to the left
	private static int partition(int[] nums, int lo, int hi) {
		int i = lo; // SLOT: where the next smaller number goes -- lo, not 0
		int pivot = nums[hi]; // PIVOT: parked at the right end
		for (int j = lo; j < hi; j++) {
			if (nums[j] < pivot) { // TEST: strictly smaller goes left; an equal number stays right
				swap(nums, i, j); // MOVE it into the smaller block
				i++;
			}
		}
		swap(nums, i, hi); // PLACE the pivot: nums[i] is never smaller than it here
		return i; // REPORT the pivot's index -- how many numbers are smaller
	}

	private static int randomIndex(int low, int high) {
		return GENERATOR.nextInt(high - low + 1) + low; // PICK: high included
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i]; // SWAP through a temporary
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 1, 5, 6, 4 };
		System.out.println(select(a1, 2)); // 5

		int[] a2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		System.out.println(select(a2, 4)); // 4

		int[] a3 = { 1 };
		System.out.println(select(a3, 1)); // 1

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(select(a4, 10)); // 1
	}
}
