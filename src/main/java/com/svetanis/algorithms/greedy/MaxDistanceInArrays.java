package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 624. Maximum Distance in Arrays

public final class MaxDistanceInArrays {
	// Time Complexity: O(n)

	public static int maxDist(List<List<Integer>> lists) {
		int maxDist = 0;
		List<Integer> first = lists.get(0);
		int min = first.get(0);
		int max = first.get(first.size() - 1);
		for (int i = 1; i < lists.size(); i++) {
			List<Integer> list = lists.get(i);
			int last = list.get(list.size() - 1);
			int dwMax = Math.abs(list.get(0) - max);
			int dwMin = Math.abs(last - min);
			maxDist = Math.max(maxDist, Math.max(dwMax, dwMin));
			min = Math.min(min, list.get(0));
			max = Math.max(max, last);
		}
		return maxDist;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = new ArrayList<>();
		lists.add(Arrays.asList(1, 2, 3));
		lists.add(Arrays.asList(4, 5));
		lists.add(Arrays.asList(1, 2, 3));
		System.out.println(maxDist(lists)); // 4

		List<List<Integer>> lists2 = new ArrayList<>();
		lists2.add(Arrays.asList(1));
		lists2.add(Arrays.asList(1));
		System.out.println(maxDist(lists2)); // 0
	}
}
