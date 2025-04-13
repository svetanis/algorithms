package com.svetanis.algorithms.prefixsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

// 1943. Describe the Painting

public final class DescribeThePainting {
	// Time complexity: O(n log n)

	public static List<List<Long>> splitPainting(int[][] segments) {
		TreeMap<Integer, Long> map = painting(segments);
		long sum = 0L;
		long prev = map.firstKey();
		List<List<Long>> list = new ArrayList<>();
		for (long position : map.keySet()) {
			if(sum > 0) {
				list.add(Arrays.asList(prev, position, sum));
			}
			prev = position;
			sum += map.get((int)position);
		}
		return list;
	}

	private static TreeMap<Integer, Long> painting(int[][] segments) {
		TreeMap<Integer, Long> map = new TreeMap<>();
		for (int[] segment : segments) {
			int start = segment[0];
			int end = segment[1];
			long color = segment[2];
			map.put(start, map.getOrDefault(start, 0L) + color);
			map.put(end, map.getOrDefault(end, 0L) - color);
		}
		return map;
	}

	public static void main(String[] args) {
		int[][] segments1 = { { 1, 4, 5 }, { 4, 7, 7 }, { 1, 7, 9 } };
		System.out.println(splitPainting(segments1));

		int[][] segments2 = { { 1, 7, 9 }, { 6, 8, 15 }, { 8, 10, 7 } };
		System.out.println(splitPainting(segments2));

		int[][] segments3 = { { 1, 4, 5 }, { 1, 4, 7 }, { 4, 7, 1 }, { 4, 7, 11 } };
		System.out.println(splitPainting(segments3));
	}
}
