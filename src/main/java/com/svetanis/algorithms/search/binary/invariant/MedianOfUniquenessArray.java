package com.svetanis.algorithms.search.binary.invariant;

import java.util.HashMap;
import java.util.Map;

// 3134. Find the Median of the Uniqueness Array

public final class MedianOfUniquenessArray {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int median(int[] a) {
		int n = a.length;
		long m = (1L + n) * n / 2;
		int left = 0;
		int right = n;
		while (left < right) {
			int mid = (left + right) >> 1;
			if (check(a, m, mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static boolean check(int[] a, long m, int max) {
		int left = 0;
		long sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < a.length; right++) {
			int curr = a[right];
			map.merge(curr, 1, Integer::sum);
			while (map.size() > max) {
				int start = a[left++];
				if (map.merge(start, -1, Integer::sum) == 0) {
					map.remove(start);
				}
			}
			sum += right - left + 1;
			if (sum >= (m + 1) / 2) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(median(a1)); // 1
		int[] a2 = { 3, 4, 3, 4, 5 };
		System.out.println(median(a2)); // 2
		int[] a3 = { 4, 3, 5, 4 };
		System.out.println(median(a3)); // 2
	}
}
