package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 624. Maximum Distance in Arrays
//
// Input: m lists, each sorted from small to large. Pick one number from one list and one number
// from a different list; their distance is the absolute value of their difference. Returns the
// largest distance. On LeetCode: 2 <= m <= 10^5, 1 <= each list's length <= 500, -10^4 <= every
// number <= 10^4, at most 10^5 numbers in all.
//
// The one idea: a list is sorted, so its smallest number is its first and its largest is its last.
// The biggest distance always uses the smallest number of one list and the largest of another, so
// only the two ends of each list matter -- a number in the middle can never beat its own list's
// ends. The walk goes through the lists in order and keeps two numbers: the smallest first number
// and the largest last number among the lists BEFORE the current one. The current list pairs its
// first number with that earlier largest, and its last number with that earlier smallest, so both
// partners come from a different list. Only after the pairing does the current list update the two
// numbers, which is what keeps a list from being paired with itself. Every two lists are compared
// when the later of the two is reached, so no pair is missed and no step is ever undone.
//
// Time: O(m) -- one visit per list, and each visit reads only the list's first and last number.
// Space: O(1) -- the two running numbers and the best distance.

public final class MaxDistanceInArrays {

	public static int maxDist(List<List<Integer>> lists) {
		int maxDist = 0;
		List<Integer> first = lists.get(0);
		int min = first.get(0); // SMALLEST first number so far
		int max = first.get(first.size() - 1); // LARGEST last number so far
		for (int i = 1; i < lists.size(); i++) { // WALK: from the second list on
			List<Integer> list = lists.get(i);
			int last = list.get(list.size() - 1);
			int dwMax = Math.abs(list.get(0) - max); // PAIR this list's first with an earlier largest
			int dwMin = Math.abs(last - min); // PAIR this list's last with an earlier smallest
			maxDist = Math.max(maxDist, Math.max(dwMax, dwMin)); // RECORD the better of the two
			min = Math.min(min, list.get(0)); // UPDATE only after the pairing -- never with itself
			max = Math.max(max, last);
		}
		return maxDist; // RETURN: the largest distance
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
