package com.svetanis.algorithms.intervals.merge;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.algorithms.intervals.Interval;

// 759. Employee Free Time
//
// schedule holds one list per employee of the intervals that employee works, each with
// start < end. Returns, sorted, the intervals of positive length inside the span of the schedule
// when no employee works. Two working intervals that only touch leave no free time between them.
// The caller's intervals are not changed. At least one interval is assumed; an empty schedule
// throws. LC guarantees 1 to 50 employees with 1 to 50 intervals each, 0 <= start < end <= 10^8.
//
// Who works does not matter, only whether anyone does, so all the intervals go into one sequence
// ordered by start. end is the latest moment reached by any interval taken so far. When the next
// interval starts after end, nobody works between end and its start: every interval taken so far
// has finished by end, and every interval not yet taken starts no earlier than this one. So
// [end, start] is free time. eft orders the intervals with a heap, eft2 with a sort.
//
// EmployeeFreeTimeKWayMerge reaches the same order without putting every interval in the heap at
// once: it keeps one entry per employee and relies on each employee's list being sorted.

public final class EmployeeFreeTimeAllIntervals {
	// Time Complexity: eft, eft2: O(n log n), n intervals in all, each offered to and polled from
	// a heap that holds all of them, or sorted once
	// Space Complexity: O(n), the heap or the combined list, and the output

	public static List<Interval> eft(List<List<Interval>> schedule) {
		List<Interval> list = new ArrayList<>();
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.start, b.start));
		for (List<Interval> intervals : schedule) {
			for (Interval interval : intervals) {
				pq.offer(interval);
			}
		}
		// the furthest end reached so far, kept in an int so that no Interval of the
		// caller is ever written to
		int end = pq.poll().end; // throws on an empty schedule
		while (!pq.isEmpty()) {
			Interval curr = pq.poll();
			if (curr.start > end) { // >: touching intervals leave no free time
				list.add(new Interval(end, curr.start));
			}
			end = Math.max(end, curr.end); // an interval inside earlier work must not pull end back
		}
		return list;
	}

	public static List<Interval> eft2(List<List<Interval>> schedule) {
		List<Interval> intervals = new ArrayList<>();
		for (List<Interval> employee : schedule) {
			for (Interval interval : employee) {
				intervals.add(interval);
			}
		}
		Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
		int end = intervals.get(0).end; // throws on an empty schedule
		List<Interval> list = new ArrayList<>();
		for (Interval interval : intervals) { // the first one is compared with itself: no change
			if (interval.start > end) { // >: touching intervals leave no free time
				list.add(new Interval(end, interval.start));
			}
			end = Math.max(end, interval.end);
		}
		return list;
	}

	public static void main(String[] args) {
		List<List<Interval>> schedule0 = new ArrayList<>();
		schedule0.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
		schedule0.add(Arrays.asList(new Interval(2, 4)));
		schedule0.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
		System.out.println(eft(schedule0)); // [5,6], [7,9]

		List<List<Interval>> schedule1 = new ArrayList<>();
		schedule1.add(asList(new Interval(1, 3), new Interval(5, 6)));
		schedule1.add(asList(new Interval(2, 3), new Interval(6, 8)));
		System.out.println(eft(schedule1)); // [[3, 5]]

		List<List<Interval>> schedule2 = new ArrayList<>();
		schedule2.add(asList(new Interval(1, 3), new Interval(9, 12)));
		schedule2.add(asList(new Interval(2, 4)));
		schedule2.add(asList(new Interval(6, 8)));
		System.out.println(eft(schedule2)); // [[4, 6], [8, 9]]

		List<List<Interval>> schedule3 = new ArrayList<>();
		schedule3.add(asList(new Interval(1, 3)));
		schedule3.add(asList(new Interval(2, 4)));
		schedule3.add(asList(new Interval(3, 5), new Interval(7, 9)));
		System.out.println(eft(schedule3)); // [[5, 7]]
	}
}