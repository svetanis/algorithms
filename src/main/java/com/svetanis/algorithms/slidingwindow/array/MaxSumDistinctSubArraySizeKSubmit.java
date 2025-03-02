package com.svetanis.algorithms.slidingwindow.array;

import java.util.HashMap;
import java.util.Map;

// 2461. Maximum Sum of Distinct Subarrays With Length K

public final class MaxSumDistinctSubArraySizeKSubmit {
	// Time Complexity: O(n)
	// Aux Space: O(k)

	public static long maxSum(int k, int[] a) {
		int n = a.length;
		if (n < k) {
			return -1;
		}
		Map<Integer, Integer> map = new HashMap<>();
		long sum = sum(a, k, map);
		long max = map.size() == k ? sum : 0;
		for (int i = k; i < n; i++) {
			int left = a[i - k];
			int right = a[i];
			sum += right - left;
			// add current to map and update its frequency
			map.merge(right, 1, Integer::sum);
			// decrement the count of the element that is k
			// positions behind the current and remove it
			// from the map if its count becomes 0
			int count = map.merge(left, -1, Integer::sum);
			if (count == 0) {
				map.remove(left);
			}
			// update the max sum when there are k distinct
			// elements in the map
			if (map.size() == k) {
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	private static long sum(int[] a, int k, Map<Integer, Integer> map) {
		long sum = 0;
		for (int i = 0; i < k; i++) {
			int curr = a[i];
			map.merge(curr, 1, Integer::sum);
			sum += curr;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 4, 2, 9, 9, 9 };
		System.out.println(maxSum(3, a1)); // 15
		int[] a2 = { 4, 4, 4 };
		System.out.println(maxSum(3, a2)); // 0
	}
}
