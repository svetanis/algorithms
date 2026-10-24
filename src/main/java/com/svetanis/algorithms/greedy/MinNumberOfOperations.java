package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2870. Minimum Number of Operations to Make Array Empty
//
// Input: list -- an array of positive whole numbers. One operation deletes two equal elements, or
// three equal elements. Returns the fewest operations that empty the array, or -1 if it cannot be
// emptied. On LeetCode: 2 <= list.length <= 10^5, 1 <= list[i] <= 10^6.
//
// The one idea: an operation only ever deletes copies of one value, so each value is a separate
// small problem: f copies, removed 2 or 3 at a time. A value that appears once can never be
// removed, so the answer is -1. Otherwise each operation removes at most 3, so at least f / 3
// rounded up are needed, and that many always suffice: if f divides by 3, all threes; if 1 is left
// over (f is at least 4), trade one three for two twos, one more operation; if 2 are left over, add
// one two. Either way the count is f / 3 rounded up, which is what quotient and quotient + 1 give.
// The values do not affect each other, so the totals simply add.
//
// Time: O(n) -- one pass to count, one pass over the distinct values.
// Space: O(n) -- the map, one entry per distinct value.

public final class MinNumberOfOperations {

	public static int minNumOperations(List<Integer> list) {
		Map<Integer, Integer> map = frequency(list); // COUNT copies of each value
		int total = 0;
		for (int freq : map.values()) { // EACH value on its own
			if (freq < 2) { // STOP: a single copy can never be deleted
				return -1;
			}
			int remainder = freq % 3;
			int quotient = freq / 3;
			if (remainder == 0) {
				total += quotient; // ALL threes
			} else {
				total += quotient + 1; // ROUND UP: one more operation covers the 1 or 2 left over
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
