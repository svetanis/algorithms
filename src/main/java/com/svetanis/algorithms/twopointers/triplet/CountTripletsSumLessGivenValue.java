package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// 259. 3Sum Smaller
//
// Input: an array of integers, repeats allowed, and a target.
// Return: how many triples of positions i < j < k have a[i] + a[j] + a[k] < target.
//
// The one idea: sort -- the count is over sets of positions, and sorting only relabels
// them -- then FIX the smallest number a[i] and converge on the numbers to its right for
// pairs below target - a[i]. When a[left] + a[right] is small enough, every partner of
// a[left] from left + 1 to right is too, because each is at most a[right]: count all
// right - left of them in one step and drop left. Otherwise a[right] is too big even with
// the smallest partner: drop right. No + 1: left cannot pair with itself.
//
// Siblings:
//   twopointers.triplet.AllTripletsSumLessGivenValue -- lists the triplets this counts
//   twopointers.triplet.CountTripletsSumInRange      -- the same count with <=, taken twice
//                                                      for a range
//   twopointers.ValidTriangleNumber611               -- the mirror: counts a block, then
//                                                      drops RIGHT
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(1) besides the sort, which works in place.

public final class CountTripletsSumLessGivenValue {

	public static int count(int[] a, int k) {
		Arrays.sort(a);                                  // SORT: the converging pass needs it

		int count = 0;
		for (int i = 0; i < a.length; i++) {             // FIX the smallest number a[i]
			int target = k - a[i];                       // the other two must sum below this
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
			if (sum >= target) {
				right--;                                 // DROP RIGHT: too big even with the smallest
			} else {
				count += right - left;                   // COUNT: every partner in (left, right] fits
				left++;                                  // DROP LEFT: all its pairs are counted
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 5, 1, 3, 4, 7 };
		System.out.println(count(a, 12)); // 4

		int[] a1 = { -2, 0, 1, 3 };
		System.out.println(count(a1, 2)); // 2

		int[] a2 = {};
		System.out.println(count(a2, 0)); // 0
	}
}
