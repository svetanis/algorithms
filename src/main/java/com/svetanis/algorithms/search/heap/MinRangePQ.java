package com.svetanis.algorithms.search.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.java.base.utils.Print;

// 632. Smallest Range Covering Elements from K Lists

public final class MinRangePQ {
	// Time Complexity: O(n log k)

	public static int[] smallestRange(List<List<Integer>> lists) {
		int max = Integer.MIN_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (int i = 0; i < lists.size(); i++) {
			int first = lists.get(i).get(0);
			pq.offer(new int[] { first, i, 0 });
			max = Math.max(max, first);
		}
		int left = 0;
		int right = Integer.MAX_VALUE;
		while (pq.size() == lists.size()) {
			int[] node = pq.poll();
			int val = node[0];
			int index = node[1];
			int iter = node[2];
			if (max - val < right - left) {
				left = val;
				right = max;
			}
			if (iter + 1 < lists.get(index).size()) {
				int next = lists.get(index).get(iter + 1);
				pq.offer(new int[] { next, index, iter + 1 });
				max = Math.max(max, next);
			}
		}
		return new int[] { left, right };
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