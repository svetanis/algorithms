package com.svetanis.algorithms.search.median;

// 4. Median of Two Sorted Arrays

// Given two sorted arrays nums1 and nums2 of size m and n, return the
// median of the two sorted arrays, in O(log (m + n)) time.
// Takes 0 <= m, n <= 1000, 1 <= m + n <= 2000 and values in
// -10^6 .. 10^6. Two empty arrays throw.

// Binary search the CUT: how many numbers the left half takes from the
// front of the shorter array, i in 0 .. m; the rest of the left half,
// j = half - i, comes from the front of the other. Both arrays are
// sorted, so the smallest half is always a front piece of each.
// A cut is right when nothing on its left is bigger than anything on
// its right. Within one array that is automatic; only the two crossing
// pairs can fail:
//   top check:    last taken from nums1 <= first left in nums2
//   bottom check: last taken from nums2 <= first left in nums1
// As i grows the bottom check only turns true, so it reads F F F T T T,
// and at its first T the top check holds too. half is (m + n + 1) / 2,
// so an odd middle sits on the left: the median is the larger last
// taken, or the average of that and the smaller first left.
// A cell past an edge stands in as an infinity: a missing last taken is
// Integer.MIN_VALUE, losing every max; a missing first left is
// Integer.MAX_VALUE, losing every min. They are compared, never added.
// Searching the shorter array keeps j inside 0 .. n for every i.

// Two methods, the same cut: median runs the first-T loop on the bottom
// check alone, and reads the four cells once the loop stops; median2
// reads all four cells at every step and tests both checks, stepping
// left or right until neither fails.

// Time: O(log min(m, n))
// Space: O(1)

// Siblings:
//   MedianTwoSortedArraysKthElement -- the k-th smallest by halving k, O(log (m + n)).
//   MedianTwoSortedEqualSizeBinary, ...Compare, ...Merge -- the same
//     question for two arrays of equal length.

public final class MedianTwoSortedArraysPartition {

	private static final String EMPTY = "median of two empty arrays is undefined";

	public static double median(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			return median(nums2, nums1); // SWAP: search the shorter array
		}
		int m = nums1.length;
		int n = nums2.length;
		if (m + n == 0) {
			throw new IllegalArgumentException(EMPTY);
		}
		int half = (m + n + 1) / 2; // the left half's size: an odd middle sits on the left
		int left = 0; // the boxes: i = how many taken from nums1, 0 .. m
		int right = m;
		while (left < right) { // SEARCH: the first i where the bottom check holds
			int mid = left + (right - left) / 2; // at most m - 1, so nums1[mid] exists
			int j = half - mid; // at least 1, since half >= m: nums2[j - 1] exists
			if (nums2[j - 1] <= nums1[mid]) { // TEST: last taken from nums2 <= first left in nums1
				right = mid; // T: keep mid
			} else {
				left = mid + 1; // F: drop mid
			}
		}
		int i = left; // REPORT: the cut; i = m is always T, so no check
		int j = half - left;
		int leftOf1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1]; // last taken from nums1
		int rightOf1 = (i == m) ? Integer.MAX_VALUE : nums1[i]; // first left in nums1
		int leftOf2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1]; // last taken from nums2
		int rightOf2 = (j == n) ? Integer.MAX_VALUE : nums2[j]; // first left in nums2
		if ((m + n) % 2 == 1) {
			return Math.max(leftOf1, leftOf2); // ODD: the largest on the left
		}
		int largest = Math.max(leftOf1, leftOf2);
		int smallest = Math.min(rightOf1, rightOf2);
		return (largest + smallest) / 2.0; // EVEN: the average of the two middles; 2.0, not 2
	}

	// -----------------------------------------------------------
	public static double median2(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			return median2(nums2, nums1); // SWAP: search the shorter array
		}
		int m = nums1.length;
		int n = nums2.length;
		if (m + n == 0) {
			throw new IllegalArgumentException(EMPTY);
		}
		int half = (m + n + 1) / 2;
		int left = 0;
		int right = m;
		while (left <= right) {
			int i = left + (right - left) / 2; // taken from nums1
			int j = half - i; // taken from nums2
			int leftOf1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
			int rightOf1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
			int leftOf2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
			int rightOf2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
			if (leftOf1 > rightOf2) { // the top check fails
				right = i - 1; // took too many from nums1
			} else if (leftOf2 > rightOf1) { // the bottom check fails
				left = i + 1; // took too few from nums1
			} else if ((m + n) % 2 == 1) {
				return Math.max(leftOf1, leftOf2); // ODD
			} else {
				return (Math.max(leftOf1, leftOf2) + Math.min(rightOf1, rightOf2)) / 2.0; // EVEN
			}
		}
		throw new IllegalStateException(EMPTY);
	}

	public static void main(String[] args) {
		int[] s1 = { 1, 3 };
		int[] p1 = { 2 };
		System.out.println(median(s1, p1)); // 2.0
		System.out.println(median2(s1, p1)); // 2.0

		int[] s2 = { 1, 2 };
		int[] p2 = { 3, 4 };
		System.out.println(median(s2, p2)); // 2.5
		System.out.println(median2(s2, p2)); // 2.5

		int[] s3 = { 1, 3, 7 };
		int[] p3 = { 0, 2, 5, 6, 8 };
		System.out.println(median(s3, p3)); // 4.0
		System.out.println(median2(s3, p3)); // 4.0

		int[] s4 = { 1, 3, 7, 9 };
		int[] p4 = { 0, 2, 5, 6, 8 };
		System.out.println(median(s4, p4)); // 5.0
		System.out.println(median2(s4, p4)); // 5.0

		int[] s5 = {};
		int[] p5 = { 1, 2, 3, 4 };
		System.out.println(median(s5, p5)); // 2.5 -- one array empty
		System.out.println(median2(s5, p5)); // 2.5
	}
}
