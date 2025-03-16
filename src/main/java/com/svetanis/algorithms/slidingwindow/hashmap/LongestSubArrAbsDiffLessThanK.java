package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeMap;

// 1438. Longest Continuous Subarray With 
// Absolute Diff Less Than or Equal to Limit

public final class LongestSubArrAbsDiffLessThanK {
	// Time complexity: O(n)

	public static int longestSubArr(int[] a, int limit) {
		int max = 0;
		int left = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int right = 0; right < a.length; right++) {
			map.put(a[right], map.getOrDefault(a[right], 0) + 1);
			while (map.lastKey() - map.firstKey() > limit) {
				map.put(a[left], map.get(a[left]) - 1);
				if (map.get(a[left]) == 0) {
					map.remove(a[left]);
				}
				left++;
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 8, 2, 4, 7 };
		System.out.println(longestSubArr(a1, 4)); // 2

		int[] a2 = { 10, 1, 2, 4, 7, 2 };
		System.out.println(longestSubArr(a2, 5)); // 4

		int[] a3 = { 4, 2, 2, 2, 4, 4, 2, 2 };
		System.out.println(longestSubArr(a3, 0)); // 3
	}
}
