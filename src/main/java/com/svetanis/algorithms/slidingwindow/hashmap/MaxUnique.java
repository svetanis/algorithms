package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

public final class MaxUnique {

	public static int count(int[] a, int k) {
		// Time complexity: O(n)

		Map<Integer, Integer> map = newHashMap();
		// traverse the first k elements
		for (int i = 0; i < k; i++) {
			map.put(a[i], map.getOrDefault(a[i], 0) + 1);
		}

		int count = map.size();
		int max = count;
		// traverse through the remaining array
		for (int i = k; i < a.length; i++) {
			// remove the first element of prev window
			// if there was only one occurrence,
			// then reduce distinct count
			if (map.get(a[i - k]) == 1) {
				map.remove(a[i - k]);
				count--;
			} else {
				int frequency = map.get(a[i - k]);
				map.put(a[i - k], frequency - 1);
			}
			map.put(a[i], map.getOrDefault(a[i], 0) + 1);
			count = map.size();
			max = max(max, count);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 1, 3, 4, 2, 3 };
		System.out.println(count(a, 4)); // 4

		int[] a1 = { 5, 3, 5, 2, 3, 2 };
		System.out.println(count(a1, 3)); // 3

		int[] a2 = { 5, 5, 5, 5, 5, 5 };
		System.out.println(count(a2, 3)); // 1
	}
}
