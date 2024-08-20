package com.svetanis.algorithms.sorting;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.filterValues;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

// sort a1[0..n-1] according to the order
// defined by a2[0..m-1]

public final class SortAccording {

	public static ImmutableList<Integer> sortAccording(List<Integer> list1, List<Integer> list2) {
		// Time complexity: O(n log n + m)
		// Space complexity: O(n)
		
		Map<Integer, Integer> map = freqMap(list1);
		List<Integer> list = newArrayList();
		for (int element : list2) {
			int freq = map.getOrDefault(element, 0);
			list.addAll(nCopies(freq, element));
			map.put(element, 0);
		}
		Map<Integer, Integer> filtered = filterValues(map, v -> v > 0);
		for (int key : sort(filtered.keySet())) {
			list.addAll(nCopies(map.get(key), key));
		}
		return newList(list);
	}

	private static Map<Integer, Integer> freqMap(List<Integer> list) {
		Map<Integer, Integer> map = newHashMap();
		for (int element : list) {
			int freq = map.getOrDefault(element, 0);
			map.put(element, freq + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8);
		List<Integer> list2 = asList(2, 1, 8, 3, 4);
		// 2,2,1,1,8,8,3,5,6,7,9
		List<Integer> sorted = sortAccording(list1, list2);
		print(sorted);
	}
}
