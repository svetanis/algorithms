package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 719. Find K-th Smallest Pair Distance

public final class KthSmallestPairDistance {
	// Time Complexity: O(n^2 log n)

	public static int smallestDistPair(int[] a, int k) {
		Arrays.sort(a);
		int left = 0;
		int right = a[a.length - 1] - a[0];
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (countPairs(a, mid) >= k) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int countPairs(int[] a, int dist) {
		int count = 0;
		int right = 0;
		for (int left = 0; left < a.length; left++) {
			while (right < a.length && a[right] - a[left] <= dist) {
				right++;
			}
			count += right - left - 1;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 1 };
		System.out.println(smallestDistPair(a1, 1)); // 0
		int[] a2 = { 1, 1, 1 };
		System.out.println(smallestDistPair(a2, 2)); // 0
		int[] a3 = { 1, 6, 1 };
		System.out.println(smallestDistPair(a3, 3)); // 5
	}
}
