package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import com.svetanis.algorithms.intervals.Interval;

// 436. Find Right Interval
//
// a[i] is an interval [start, end] with start <= end, and no two intervals share a start. The
// right interval of i is the j with the smallest start >= a[i].end; j may be i itself, when its
// start equals its end. Returns, at each interval's own index, that j, or -1 when no start is
// >= a[i].end. On LeetCode: up to 2 * 10^4 intervals, values between -10^6 and 10^6.
//
// Two max-heaps of indexes, one by start and one by end. Ends come out largest first, so a start
// too small for this end is too small for every later end as well. For each end, DROP starts
// from the top while they are >= the end: the last one dropped is the smallest such start, the
// answer. The others dropped are larger than it, so no later (smaller) end can prefer them -- but
// the answer itself can serve a later end too, so it goes BACK.
//
// Siblings, same problem:
//   RightInterval (search/binary)  sorts the starts once and binary-searches the smallest start
//                                  >= each end; takes int[][] as LeetCode does

public final class NextInterval {
	// Time complexity: O(n * log(n)), every index is pushed into each heap once; a start polled
	// from the start heap is gone for good except the one put back per end, so at most 2n polls
	// Auxiliary Space: O(n), the two heaps

	public static int[] next(Interval[] a) {
		// BY START: largest start on top
		Queue<Integer> starts =
				new PriorityQueue<>(comparingInt((Integer i) -> a[i].start).reversed());
		// BY END: largest end on top
		Queue<Integer> ends = new PriorityQueue<>(comparingInt((Integer i) -> a[i].end).reversed());
		for (int i = 0; i < a.length; i++) {
			starts.add(i);
			ends.add(i);
		}
		int[] next = new int[a.length];
		// ends come out largest first, so a start too small
		// for this end is too small for every end after it
		while (!ends.isEmpty()) {
			int i = ends.poll();
			// DROP: starts until the next one is too small:
			// the last one dropped is the smallest start >= end
			int found = -1;
			while (!starts.isEmpty() && a[starts.peek()].start >= a[i].end) {
				found = starts.poll();
			}
			// ANSWER: at the interval's own index, -1 when nothing was dropped
			next[i] = found;
			if (found != -1) {
				// BACK: put it back, another interval may share it
				starts.add(found);
			}
		}
		return next;
	}

	public static void main(String[] args) {
		Interval i1 = new Interval(2, 3);
		Interval i2 = new Interval(3, 4);
		Interval i3 = new Interval(5, 6);
		Interval[] a1 = { i1, i2, i3 };
		System.out.println(Arrays.toString(next(a1))); // [1, 2, -1]

		Interval i4 = new Interval(1, 5);
		Interval i5 = new Interval(4, 6);
		Interval[] a2 = { i2, i4, i5 };
		System.out.println(Arrays.toString(next(a2))); // [2, -1, -1]

		// two intervals share one next interval
		Interval i6 = new Interval(1, 2);
		Interval i7 = new Interval(0, 2);
		Interval[] a3 = { i6, i7, i1 };
		System.out.println(Arrays.toString(next(a3))); // [2, 2, -1]
	}
}
