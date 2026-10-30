package com.svetanis.algorithms.search.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 632. Smallest Range Covering Elements from K Lists

// Given k lists of sorted integers, return the smallest range [a, b]
// that includes at least one number from each list. A range is smaller
// when b - a is smaller, or when the widths tie and a is smaller.

// Pour every number into one list, tagged with the list it came from,
// and sort it. Now the question is the shortest window of that sorted
// list holding at least one tag of every list -- a sliding window: grow
// on the right; while every list is covered, record the width and
// shrink from the left. The first window found at a width wins a tie,
// because its left end is the smallest.

// Two methods, the same window; only the "how many lists are covered"
// count differs:
//   smallestRange  -- an array of counts per list plus a covered counter.
//   smallestRange2 -- a map from list to count; covered is the map's size.

// Time: O(n log n), n the total count of numbers -- the sort; the window
//   moves each end at most n times
// Space: O(n) for the tagged list

// Siblings:
//   MinRangeKWayMerge -- a heap holding one number per list, O(n log k).
//   sorting/kwaymerge/SmallestNumberRange -- the same k-way merge on Node.

public final class MinRangeSlidingWindow {

	public static int[] smallestRange(List<List<Integer>> lists) {
		List<int[]> sorted = sorted(lists); // MERGE: every number, tagged, in order
		int left = 0;
		int covered = 0; // how many lists have a number in the window
		int[] count = new int[lists.size()]; // numbers in the window, per list
		long min = Long.MAX_VALUE; // a width can pass Integer.MAX_VALUE
		int[] range = new int[2];
		for (int right = 0; right < sorted.size(); right++) {
			int rval = sorted.get(right)[0];
			int ridx = sorted.get(right)[1];
			count[ridx]++; // GROW: the number at right joins
			if (count[ridx] == 1) {
				covered++; // COVER: its list's first number in the window
			}
			while (covered == lists.size()) { // VALID: every list is covered
				int lval = sorted.get(left)[0];
				int lidx = sorted.get(left)[1];
				long width = (long) rval - lval; // widened before the subtraction
				if (width < min) { // RECORD: strictly narrower; a tie keeps the earlier, smaller start
					min = width;
					range[0] = lval;
					range[1] = rval;
				}
				count[lidx]--; // SHRINK: the number at left leaves
				if (count[lidx] == 0) {
					covered--; // UNCOVER: its list has no number left in the window
				}
				left++;
			}
		}
		return range; // REPORT
	}

	public static int[] smallestRange2(List<List<Integer>> lists) {
		List<int[]> sorted = sorted(lists); // MERGE: every number, tagged, in order
		int left = 0;
		Map<Integer, Integer> count = new HashMap<>(); // list -> numbers in the window; its size is lists covered
		long min = Long.MAX_VALUE; // a width can pass Integer.MAX_VALUE
		int[] range = new int[2];
		for (int right = 0; right < sorted.size(); right++) {
			int rval = sorted.get(right)[0];
			int ridx = sorted.get(right)[1];
			count.put(ridx, count.getOrDefault(ridx, 0) + 1); // GROW: the number at right joins
			while (count.size() == lists.size()) { // VALID: every list is covered
				int lval = sorted.get(left)[0];
				int lidx = sorted.get(left)[1];
				long width = (long) rval - lval; // widened before the subtraction
				if (width < min) { // RECORD: strictly narrower; a tie keeps the earlier, smaller start
					min = width;
					range[0] = lval;
					range[1] = rval;
				}
				count.put(lidx, count.get(lidx) - 1); // SHRINK: the number at left leaves
				if (count.get(lidx) == 0) {
					count.remove(lidx); // UNCOVER: removing the key is what lowers the size
				}
				left++;
			}
		}
		return range; // REPORT
	}

	private static List<int[]> sorted(List<List<Integer>> lists) {
		List<int[]> sorted = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {
			for (int num : lists.get(i)) {
				sorted.add(new int[] { num, i }); // TAG: the number and its list
			}
		}
		sorted.sort(Comparator.comparingInt(a -> a[0])); // ORDER: by value; equal values in any order
		return sorted;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists1 = new ArrayList<>();
		lists1.add(Arrays.asList(4, 10, 15, 24, 26));
		lists1.add(Arrays.asList(0, 9, 12, 20));
		lists1.add(Arrays.asList(5, 18, 22, 30));
		System.out.println(Arrays.toString(smallestRange(lists1))); // [20, 24]
		System.out.println(Arrays.toString(smallestRange2(lists1))); // [20, 24]

		List<List<Integer>> lists2 = new ArrayList<>();
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		System.out.println(Arrays.toString(smallestRange(lists2))); // [1, 1]
		System.out.println(Arrays.toString(smallestRange2(lists2))); // [1, 1]

		// values far apart: the widths pass Integer.MAX_VALUE
		List<List<Integer>> lists3 = new ArrayList<>();
		lists3.add(Arrays.asList(-2_000_000_000, 0));
		lists3.add(Arrays.asList(2_000_000_000));
		System.out.println(Arrays.toString(smallestRange(lists3))); // [0, 2000000000]
		System.out.println(Arrays.toString(smallestRange2(lists3))); // [0, 2000000000]
	}
}
