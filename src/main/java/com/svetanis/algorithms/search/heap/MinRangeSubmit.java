package com.svetanis.algorithms.search.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 632. Smallest Range Covering Elements from K Lists

public final class MinRangeSubmit {
	// Time Complexity: O(n log n)

	public static int[] smallestRange(List<List<Integer>> lists) {
		List<int[]> sorted = sorted(lists);
		int left = 0;
		int count = 0;
		int min = Integer.MAX_VALUE;
		int[] range = new int[2];
		int[] frequency = new int[lists.size()];
		for (int right = 0; right < sorted.size(); right++) {
			int[] element = sorted.get(right);
			frequency[element[1]]++;
			// increase count when current list first element included
			if (frequency[element[1]] == 1) {
				count++;
			}
			while (count == lists.size()) {
				int lval = sorted.get(left)[0];
				int rval = sorted.get(right)[0];
				if (rval - lval < min) {
					min = rval - lval;
					range[0] = lval;
					range[1] = rval;
				}
				// shrink from left
				int index = sorted.get(left)[1];
				frequency[index]--;
				if (frequency[index] == 0) {
					count--;
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