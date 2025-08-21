package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeMap;

// 2762. Continuous Subarrays

public final class ContinuousSubarrays {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static long countContinuousSubarrays(int[] a) {
		int left = 0;
		long count = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int right = 0; right < a.length; right++) {
			int curr = a[right];
			map.merge(curr, 1, Integer::sum);
			while (map.lastKey() - map.firstKey() > 2) {
				int min = a[left];
				map.merge(min, -1, Integer::sum);
				if (map.get(min) == 0) {
					map.remove(min);
				}
				left++;
			}
			count += right - left + 1;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 2, 4 };
		System.out.println(countContinuousSubarrays(a1)); // 8
		int[] a2 = { 1, 2, 3 };
		System.out.println(countContinuousSubarrays(a2)); // 6
	}
}
