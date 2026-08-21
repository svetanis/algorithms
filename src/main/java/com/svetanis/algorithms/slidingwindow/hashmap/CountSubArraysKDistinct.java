package com.svetanis.algorithms.slidingwindow.hashmap;

// 992. Subarrays with K Different Integers

public final class CountSubArraysKDistinct {

	// EXACTLY k is not a window: shrinking a valid window drops you to
	// k - 1, so there is no shrink rule. Every subarray with at most k
	// distinct has either exactly k or at most k - 1, and those two
	// groups do not overlap -- so subtract the second from the whole.

	// Time complexity: O(n) -- two passes of the same window
	public static int count(int[] a, int k) {
		// k = 0 would call atMost(a, -1), whose shrink loop can never be
		// satisfied and walks left off the end of the array. No non-empty
		// subarray holds 0 distinct values, so the answer is 0.
		if (k <= 0) {
			return 0;
		}
		return atMost(a, k) - atMost(a, k - 1);
	}

	// the number of subarrays containing AT MOST k distinct values
	// Time complexity: O(n) -- each index enters and leaves once
	private static int atMost(int[] a, int k) {
		int n = a.length;
		int left = 0;
		int count = 0;
		int distinct = 0;
		int[] counts = new int[n + 1];
		for (int right = 0; right < n; right++) {
			counts[a[right]]++;
			if (counts[a[right]] == 1) {
				distinct++;
			}
			while (distinct > k) {
				counts[a[left]]--;
				if (counts[a[left]] == 0) {
					distinct--;
				}
				left++;
			}
			// every subarray ENDING at right is valid once the window is:
			// its start may be any index in [left, right], which is
			// right - left + 1 choices. Dropping the +1 under-counts by
			// one per position -- it cancels in count() above, but makes
			// this method wrong by n on its own.
			count += right - left + 1;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1, 2, 3 };
		System.out.println(count(a1, 2)); // 7

		int[] a2 = { 1, 2, 1, 3, 4 };
		System.out.println(count(a2, 3)); // 3
	}
}
