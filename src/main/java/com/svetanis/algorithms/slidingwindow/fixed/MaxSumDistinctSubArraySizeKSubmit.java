package com.svetanis.algorithms.slidingwindow.fixed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2461. Maximum Sum of Distinct Subarrays With Length K

public final class MaxSumDistinctSubArraySizeKSubmit {
	// Time Complexity: O(n)
	// Aux Space: O(k)

	public static long maxSum(int[] a, int k) {
		int n = a.length;
		if (n < k) {
			return -1;
		}
		long max = 0;
		long sum = 0;
		int left = 0;
		Set<Integer> set = new HashSet<>();
		for (int right = 0; right < n; right++) {
			int curr = a[right];
			sum += curr;
			while (left < right && set.contains(curr) || right - left + 1 > k) {
				sum -= a[left];
				set.remove(a[left]);
				left++;
			}
			if (right - left + 1 == k) {
				max = Math.max(max, sum);
			}
			set.add(curr);
		}
		return max;
	}

	public static long maxSumMap(int[] a, int k) {
		int n = a.length;
		if (n < k) {
			return -1;
		}
		long max = 0;
		long sum = 0;
		int left = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			int curr = a[right];
			sum += curr;
			map.merge(curr, 1, Integer::sum);
			if (right - left + 1 == k) {
				if (map.size() == k) {
					max = Math.max(max, sum);
				}
				int first = a[left];
				sum -= first;
				map.merge(first, -1, Integer::sum);
				if (map.get(first) == 0) {
					map.remove(first);
				}
				left++;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 4, 2, 9, 9, 9 };
		System.out.println(maxSum(a1, 3)); // 15
		int[] a2 = { 4, 4, 4 };
		System.out.println(maxSum(a2, 3)); // 0
	}
}
