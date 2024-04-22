package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// given an array of size n and an integer k
// count the distinct numbers in all windows of size k

public final class MaxNumOfDistinctElementsInWindow {

	public static int maxDistinct(int[] a, int k) {
		// Time complexity: O(n)

		Map<Integer, Integer> map = newHashMap();
		// traverse the first k elements
		for (int i = 0; i < k; i++) {
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1);
		}

		int count = map.keySet().size();
		int max = count;

		// traverse through the remaining array
		for (int i = k; i < a.length; i++) {
			// remove the first element of prev window
			// if there was only one occurrence,
			// then remove it from the map
			// otherwise reduce distinct count
			int f = map.get(a[i - k]);
			if (f == 1) {
				map.remove(a[i - k]);
				count--;
			} else {
				map.put(a[i - k], f - 1);
			}

			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1);
			count = map.keySet().size();
			max = max(max, count);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 3, 5, 2, 3, 2 };
		System.out.println(maxDistinct(a1, 3));

		int[] a2 = { 5, 5, 5, 5, 5, 5 };
		System.out.println(maxDistinct(a2, 3));

	}
}
