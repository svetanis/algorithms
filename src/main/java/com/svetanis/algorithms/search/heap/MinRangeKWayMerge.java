package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 632. Smallest Range Covering Elements from K Lists
//
// Given k lists of sorted integers, return the smallest range [a, b] that includes at least one
// number from each list. A range is smaller when b - a is smaller, or when the widths tie and a is
// smaller.
//
// Hold one number from every list: a min-heap of them, plus the largest as one int. Those k numbers
// make the range [heap top, max]. Only raising the LOWEST can shrink it, so poll the top, record
// the range, and push the next number from the same list -- what leaves is always the lowest, so
// only the arrival can raise max. The moment a list has no next number, no choice that covers
// every list remains: the loop sees the heap one short and stops.
//
// Time: O(n log k), n the total count of numbers -- each is pushed and polled once, on a heap of k
// Space: O(k) for the heap
//
// Siblings, same problem:
//   MinRangeSlidingWindow -- sort every number tagged with its list, then a sliding window, O(n log n).
//   sorting/kwaymerge/SmallestNumberRange -- the same k-way merge on Node, returning an Interval.

public final class MinRangeKWayMerge {

	public static int[] smallestRange(List<List<Integer>> lists) {
		int max = Integer.MIN_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(a -> a[0])); // MIN-HEAP: (value, list, position), the lowest value on top
		for (int i = 0; i < lists.size(); i++) {
			int first = lists.get(i).get(0);
			pq.offer(new int[] { first, i, 0 });   // PUSH: the first number of every list
			max = Math.max(max, first);            // MAX: the highest number held
		}
		int left = 0;
		int right = Integer.MAX_VALUE;
		long best = Long.MAX_VALUE; // a width can pass Integer.MAX_VALUE
		while (pq.size() == lists.size()) {        // VALID: one number from every list is held
			int[] node = pq.poll();                // POLL: the lowest number held
			int val = node[0];
			int index = node[1];
			int iter = node[2];
			long width = (long) max - val; // widened before the subtraction
			if (width < best) {                    // RECORD: strictly narrower; a tie keeps the earlier, smaller start
				best = width;
				left = val;
				right = max;
			}
			if (iter + 1 < lists.get(index).size()) {
				int next = lists.get(index).get(iter + 1);
				pq.offer(new int[] { next, index, iter + 1 }); // PUSH: the next number from the same list
				max = Math.max(max, next);         // MAX: only the arrival can raise it
			}
		}
		return new int[] { left, right };          // REPORT
	}

	public static void main(String[] args) {
		List<List<Integer>> lists1 = new ArrayList<>();
		lists1.add(Arrays.asList(4, 10, 15, 24, 26));
		lists1.add(Arrays.asList(0, 9, 12, 20));
		lists1.add(Arrays.asList(5, 18, 22, 30));
		System.out.println(Arrays.toString(smallestRange(lists1))); // [20, 24]

		List<List<Integer>> lists2 = new ArrayList<>();
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		lists2.add(Arrays.asList(1, 2, 3));
		System.out.println(Arrays.toString(smallestRange(lists2))); // [1, 1]

		// values far apart: the widths pass Integer.MAX_VALUE
		List<List<Integer>> lists3 = new ArrayList<>();
		lists3.add(Arrays.asList(-2_000_000_000, 0));
		lists3.add(Arrays.asList(2_000_000_000));
		System.out.println(Arrays.toString(smallestRange(lists3))); // [0, 2000000000]
	}
}