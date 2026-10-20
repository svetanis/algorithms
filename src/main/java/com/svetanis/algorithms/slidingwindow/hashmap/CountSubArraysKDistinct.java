package com.svetanis.algorithms.slidingwindow.hashmap;

// 992. Subarrays with K Different Integers
//
// Input: an array a of n integers, each in 0..n, and a number k.
// Return: how many contiguous subarrays hold exactly k different values; 0 when k <= 0.
//
// The one idea: "exactly k" is not a window on its own. Dropping the leftmost element of a
// subarray with k different values can leave k - 1, so there is no rule for when to shrink.
// "At most k" has one: shrink while the window holds more than k. Every subarray with at most
// k different values has either exactly k or at most k - 1, and no subarray is in both
// groups -- so exactly(k) = atMost(k) - atMost(k - 1).
// atMost counts each subarray at its last index. Once the window a[left..right] holds at most
// k values, every subarray ending at right and starting at left .. right holds at most k too
// (it is a piece of the window), and one starting before left holds more. That is
// right - left + 1 subarrays for this right, and none is counted at any other right.
//
// The value range 0..n is what the counts array allows: it is indexed by value, so a value
// above n or below 0 throws ArrayIndexOutOfBoundsException.
//
// Time: O(n) -- atMost runs twice; in each run right and left only move forward, so the inner
// while loop runs at most n times in total, not n times for every right.
// Space: O(n) -- the counts array, n + 1 slots.
//
// Siblings, other "exactly" counts:
//   array.BinarySubArrWithSum (930) -- exactly a sum, on a 0/1 array; the two atMost windows
//     run in one loop with two left indices instead of a helper called twice.
//   array.CountFixedBountSubArrs (2444) -- the minimum and maximum fixed exactly; that is a
//     "contains at least one" condition, not a threshold, so there is nothing to subtract: it
//     tracks the last position of each.
//   prefixsum.CountNiceSubArrs (1248) -- exactly k odd numbers, counted with prefix counts
//     instead of a window.
//   string.LongestSubStrLenAtMostKUnique (340) -- the same at-most-k window, measuring the
//     longest one instead of counting.

public final class CountSubArraysKDistinct {

	public static int count(int[] a, int k) {
		// k = 0 would call atMost(a, -1), whose shrink loop can never be
		// satisfied and walks left off the end of the array. No non-empty
		// subarray holds 0 distinct values, so the answer is 0.
		if (k <= 0) { // EARLY: nothing holds 0 different values
			return 0;
		}
		return atMost(a, k) - atMost(a, k - 1); // RETURN: at most k, minus at most k - 1
	}

	// the number of subarrays containing AT MOST k distinct values
	private static int atMost(int[] a, int k) {
		int n = a.length;
		int left = 0;
		int count = 0;
		int distinct = 0;
		int[] counts = new int[n + 1]; // copies of each value in the window, indexed by value
		for (int right = 0; right < n; right++) {
			counts[a[right]]++; // ADD a[right]
			if (counts[a[right]] == 1) { // NEW: its first copy in the window
				distinct++;
			}
			while (distinct > k) { // SHRINK while the window holds more than k values
				counts[a[left]]--; // DROP a[left]
				if (counts[a[left]] == 0) { // GONE: that was its last copy
					distinct--;
				}
				left++;
			}
			// every subarray ENDING at right is valid once the window is:
			// its start may be any index in [left, right], which is
			// right - left + 1 choices. Dropping the +1 under-counts by
			// one per position -- it cancels in count() above, but makes
			// this method wrong by n on its own.
			count += right - left + 1; // COUNT: starts left .. right, all ending at right
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1, 2, 3 };
		System.out.println(count(a1, 2)); // 7
		System.out.println(count(a1, 1)); // 5
		System.out.println(count(a1, 0)); // 0

		int[] a2 = { 1, 2, 1, 3, 4 };
		System.out.println(count(a2, 3)); // 3
		System.out.println(count(a2, 5)); // 0
	}
}
