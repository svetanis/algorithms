package com.svetanis.algorithms.sorting.interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// 1353. Maximum Number of Events That Can Be Attended

public final class MaxNumberOfEvents {
	// Time Complexity: O(n*log n)
	// Space Complexity: O(n)

	public static int maxEvents(int[][] events) {
		int first = Integer.MAX_VALUE;
		int last = 0;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int[] event : events) {
			int start = event[0];
			int end = event[1];
			map.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
			first = Math.min(first, start);
			last = Math.max(last, end);
		}
		int count = 0;
		Queue<Integer> pq = new PriorityQueue<>();
		for (int day = first; day <= last; day++) {
			while (!pq.isEmpty() && pq.peek() < day) {
				pq.poll();
			}
			List<Integer> list = map.getOrDefault(day, new ArrayList<>());
			pq.addAll(list);
			if (!pq.isEmpty()) {
				pq.poll();
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
		System.out.println(maxEvents(g1)); // 3
		int[][] g2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 2 } };
		System.out.println(maxEvents(g2)); // 4
		int[][] g3 = { { 1, 4 }, { 4, 4 }, { 2, 2 }, { 3, 4 }, { 1, 1 } };
		System.out.println(maxEvents(g3)); // 4
		int[][] g4 = { { 1, 2 }, { 1, 2 }, { 3, 3 }, { 1, 5 }, { 1, 5 } };
		System.out.println(maxEvents(g4)); // 5
	}
}
