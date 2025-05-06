package com.svetanis.algorithms.search.binary;

import java.util.Arrays;

// 2226. Maximum Candies Allocated to K Children

public final class MaxCandiesAllocatedToKChildren {
	// Time Complexity: O(n * log(max(candies))
	// Space Complexity: O(1)

	public static int maxCandies(int[] candies, long k) {
		int low = 0;
		int high = Arrays.stream(candies).max().getAsInt();
		while (low < high) {
			int mid = low + (high - low + 1) / 2;
			if (count(candies, mid) >= k) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private static long count(int[] candies, long mid) {
		long count = 0;
		for (int size : candies) {
			count += size / mid;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 8, 6 };
		System.out.println(maxCandies(a1, 3)); // 5
		int[] a2 = { 2, 5 };
		System.out.println(maxCandies(a2, 11)); // 0
	}
}
