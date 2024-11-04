package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2870. Minimum Number of Operations to Make Array Empty

public final class MinNumberOfOperations {
	// Time Complexity: O(n)

	public static int minNumOperations(List<Integer> list) {
		Map<Integer, Integer> map = frequency(list);
		int total = 0;
		for (int freq : map.values()) {
			if (freq < 2) {
				return -1;
			}
			int remainder = freq % 3;
			int quotient = freq / 3;
			if (remainder == 0) {
				total += quotient;
			} else {
				total += quotient + 1;
			}
		}
		return total;
	}

	private static Map<Integer, Integer> frequency(List<Integer> list) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int element : list) {
			map.put(element, map.getOrDefault(element, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(14, 12, 14, 14, 12, 14, 14, 12, 12, 12, 12, 14, 14, 12, 14, 14, 14, 12, 12);
		System.out.println(minNumOperations(list)); // 7
		System.out.println(minNumOperations(asList(2, 3, 3, 2, 2, 4, 2, 3, 4))); // 4
		System.out.println(minNumOperations(asList(2, 1, 2, 2, 3, 3))); // -1
	}
}
