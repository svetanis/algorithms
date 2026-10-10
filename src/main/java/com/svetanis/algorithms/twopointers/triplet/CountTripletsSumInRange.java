package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// Count triplets with sum in a range
//
// Input: an array of integers, repeats allowed, and a range [left, right].
// Return: how many triples of positions have left <= a[i] + a[j] + a[l] <= right.
//
// The one idea: a range count is the difference of two "at most" counts --
// count(sum <= right) - count(sum <= left - 1). Each "at most" count is 3Sum Smaller
// (LC 259) with <= in place of <: sort, FIX a[i], converge on the numbers to its right, and
// when a[l] + a[r] fits, all r - l partners of a[l] from l + 1 to r fit too.
//
// Sibling: twopointers.triplet.CountTripletsSumLessGivenValue -- LC 259, the single "less
//   than" count.
//
// Time: O(n^2) -- two counts, each n fixed numbers with an O(n) converging pass.
// Space: O(1) besides the sort.

public final class CountTripletsSumInRange {

	public static int countInRange(int[] a, int left, int right) {
		Arrays.sort(a);                                  // SORT once, for both counts

		int above = count(a, right);                     // COUNT sums <= right
		int below = count(a, left - 1);                  // COUNT sums < left
		return above - below;                            // RETURN the sums in between
	}

	private static int count(int[] a, int k) {           // a must be sorted
		int count = 0;
		for (int i = 0; i < a.length; i++) {             // FIX the smallest number a[i]
			int target = k - a[i];                       // the other two must sum to at most this
			count += count(a, target, i);
		}
		return count;
	}

	private static int count(int[] a, int target, int first) {
		int count = 0;
		int left = first + 1;                            // START: the two ends right of first
		int right = a.length - 1;
		while (left < right) {                           // STOP: a pair needs two positions
			int sum = a[left] + a[right];                // COMPARE
			if (sum > target) {
				right--;                                 // DROP RIGHT: too big even with the smallest
			} else {
				count += right - left;                   // COUNT: every partner in (left, right] fits
				left++;                                  // DROP LEFT: all its pairs are counted
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 2, 7, 5, 3, 8, 4, 1, 9 };
		System.out.println(countInRange(a, 8, 16)); // 36
	}
}
