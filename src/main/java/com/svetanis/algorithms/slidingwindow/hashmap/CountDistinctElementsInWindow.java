package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;

// given an array of size n and an integer k
// count the distinct numbers in all windows of size k

public final class CountDistinctElementsInWindow {

	public static ImmutableList<Integer> count(int[] a, int k) {
		// Time complexity: O(n)

		List<Integer> list = newArrayList();
		Map<Integer, Integer> map = newHashMap();
		// traverse the first k elements
		for (int i = 0; i < k; i++) {
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1);
		}
		list.add(map.keySet().size());

		// traverse through the remaining array
		for (int i = k; i < a.length; i++) {
			// remove the first element of prev window
			// if there was only one occurrence,
			// then remove it from the map
			// otherwise reduce distinct count
			int f = map.get(a[i - k]);
			if (f == 1) {
				map.remove(a[i - k]);
			} else {
				map.put(a[i - k], f - 1);
			}

			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1);

			list.add(map.keySet().size());
		}
		return newList(list);
	}

	public static void main(String[] args) {
		int k = 4;
		int[] a = { 1, 2, 1, 3, 4, 2, 3 };
		System.out.println(count(a, k));
	}
}
