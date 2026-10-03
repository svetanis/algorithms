package com.svetanis.algorithms.intervals.merge;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.algorithms.intervals.Interval;

// 759. Employee Free Time
//
// schedule holds one list per employee of the intervals that employee works, each with
// start < end; each list is sorted by start and has no two overlapping intervals, and the code
// relies on that. Returns, sorted, the intervals of positive length inside the span of the
// schedule when no employee works. Two working intervals that only touch leave no free time
// between them. The caller's intervals are not changed. Every employee is assumed to have at
// least one interval; an empty list throws. LC guarantees 1 to 50 employees with 1 to 50
// intervals each, 0 <= start < end <= 10^8.
//
// One heap entry, an EmployeeInterval, is one interval together with which employee it belongs
// to and its position in that employee's list. The heap holds at most one entry per employee:
// that employee's earliest interval not yet taken. Because each list is sorted, the entry with
// the smallest start in the heap is the earliest-starting interval not yet taken by anyone.
// Polling it and pushing the same employee's next interval visits every interval in order of
// start while the heap stays at k entries or fewer. prev is the interval taken so far that
// reaches furthest. When the next interval starts after prev ends, nobody works in between.
//
// EmployeeFreeTimeAllIntervals puts every interval into one heap, or one sorted list, instead, and
// so does not need each employee's list to be sorted.

public final class EmployeeFreeTimeKWayMerge {
	// Time Complexity: O(n log k), n intervals in all, k employees; each interval is offered and
	// polled once, on a heap that never holds more than one interval per employee
	// Space Complexity: O(k) for the heap, plus O(n) for the output

	public static List<Interval> eft(List<List<Interval>> schedule) {
		List<Interval> list = new ArrayList<>();
		PriorityQueue<EmployeeInterval> pq = epq(schedule);
		Interval prev = pq.peek().interval; // the first poll compares it with itself: no change
		while (!pq.isEmpty()) {
			EmployeeInterval top = pq.poll(); // earliest start among intervals not yet taken
			Interval eti = top.interval;
			// if prev interval is not overlapping with
			// the next interval, save a free interval
			if (prev.end < eti.start) { // <: touching intervals leave no free time
				list.add(new Interval(prev.end, eti.start));
				prev = eti;
			} else if (prev.end < eti.end) {
				// overlapping intervals, update the prev interval
				prev = eti;
			}
			// if there are more intervals available for the same employee,
			// add their next interval to the priority queue
			int tei = top.empIndex;
			List<Interval> intervals = schedule.get(tei);
			if (top.intIndex < intervals.size() - 1) {
				int next = top.intIndex + 1;
				Interval interval = intervals.get(next);
				pq.offer(new EmployeeInterval(interval, tei, next));
			}
		}
		return list;
	}

	// initialize priority queue and store first interval from each employee
	private static PriorityQueue<EmployeeInterval> epq(List<List<Interval>> schedule) {
		Comparator<EmployeeInterval> eic = (a, b) -> Integer.compare(a.interval.start, b.interval.start);
		PriorityQueue<EmployeeInterval> pq = new PriorityQueue<>(eic);
		for (int i = 0; i < schedule.size(); i++) {
			Interval interval = schedule.get(i).get(0); // throws for an employee with no intervals
			pq.offer(new EmployeeInterval(interval, i, 0));
		}
		return pq;
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

	public static final class EmployeeInterval {
		protected Interval interval;
		protected int empIndex;
		protected int intIndex;

		public EmployeeInterval(Interval interval, int empIndex, int intIndex) {
			this.interval = interval;
			this.empIndex = empIndex;
			this.intIndex = intIndex;
		}
	}
}
