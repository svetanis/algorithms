package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 904. Fruit Into Baskets

public final class MaxFruitIntoBaskets {
	// Time complexity: O(n)

	public static int maxLen(int[] a) {
		int n = a.length;
		int left = 0; // current start
		int max = 0; // max window size
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			int next = a[right];
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq);
			// shrink the sliding window, until k
			// distinct chars left in frequency map
			while (map.size() > 2) {
				int front = a[left];
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front);
				}
				left++; // shrink the window
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1 };
		System.out.println(maxLen(a1)); // 3

		int[] a2 = { 0, 1, 2, 2 };
		System.out.println(maxLen(a2)); // 3

		int[] a3 = { 1, 2, 3, 2, 2 };
		System.out.println(maxLen(a3)); // 4
	}
}
