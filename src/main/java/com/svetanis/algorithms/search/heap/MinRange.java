package com.svetanis.algorithms.search.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 632. Smallest Range Covering Elements from K Lists

public final class MinRange {
	// Time Complexity: O(n log n)

	public static int[] smallestRange(List<List<Integer>> lists) {
		List<int[]> sorted = sorted(lists);
		int left = 0;
		int min = Integer.MAX_VALUE;
		int[] range = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < sorted.size(); right++) {
			int[] node = sorted.get(right);
			int rval = node[0];
			int ridx = node[1];
			map.put(ridx, map.getOrDefault(ridx, 0) + 1);
			while (map.size() == lists.size()) {
				int lval = sorted.get(left)[0];
				int lidx = sorted.get(left)[1];
				if (rval - lval < min) {
					min = rval - lval;
					range[0] = lval;
					range[1] = rval;
				}
				// shrink from left
				map.put(lidx, map.get(lidx) - 1);
				if (map.get(lidx) == 0) {
					map.remove(lidx);
				}
				left++;
			}
		}
		return range;
	}

	private static List<int[]> sorted(List<List<Integer>> lists) {
		List<int[]> sorted = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {
			for (int num : lists.get(i)) {
				sorted.add(new int[] { num, i });
			}
		}
		Collections.sort(sorted, Comparator.comparingInt(a -> a[0]));
		return sorted;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists1 = new ArrayList<>();
		lists1.add(Arrays.asList(4, 10, 15, 24, 26));
		lists1.add(Arrays.asList(0, 9, 12, 20));
		lists1.add(Arrays.asList(5, 18, 22, 30));
		Print.print(smallestRange(lists1)); // [20, 24]

		List<List<Integer>> lists2 = new ArrayList<>();
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		Print.print(smallestRange(lists2)); // [1, 1]
	}
}