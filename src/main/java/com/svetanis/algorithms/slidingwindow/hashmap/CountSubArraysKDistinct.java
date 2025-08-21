package com.svetanis.algorithms.slidingwindow.hashmap;

// 992. Subarrays with K Different Integers

public final class CountSubArraysKDistinct {
	// Time complexity: O(n)

	public static int count(int[] a, int k) {
		return counts(a, k) - counts(a, k - 1);
	}

	private static int counts(int[] a, int k) {
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
			count += right - left;
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
