package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.google.common.collect.ImmutableList;
import com.svetanis.algorithms.sorting.interval.Interval;

// for k employees, given a list of intervals
// representing the working hours of each employee
// find out if there is a free interval that is
// common to all employees. 
// each list of employee working hours is 
// sorted on the start time

public final class EmployeeFreeTimePQ {
	// Time Complexity: O(n*log n)
	// Space Complexity: O(n)

	public static ImmutableList<Interval> eft(List<List<Interval>> schedule) {
		List<Interval> list = newArrayList();
		PriorityQueue<EmployeeInterval> pq = epq(schedule);
		Interval prev = pq.peek().interval;
		while (!pq.isEmpty()) {
			EmployeeInterval top = pq.poll();
			Interval eti = top.interval;
			// if prev interval is not overlapping with
			// the next interval, save a free interval
			if (prev.end < eti.start) {
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
		return newList(list);
	}

	// initialize priority queue and store first interval from each employee
	private static PriorityQueue<EmployeeInterval> epq(List<List<Interval>> schedule) {
		Comparator<EmployeeInterval> eic = comparing(i -> i.interval.start);
		PriorityQueue<EmployeeInterval> pq = new PriorityQueue<>(eic);
		for (int i = 0; i < schedule.size(); i++) {
			Interval interval = schedule.get(i).get(0);
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

		List<List<Interval>> schedule1 = newArrayList();
		schedule1.add(asList(new Interval(1, 3), new Interval(5, 6)));
		schedule1.add(asList(new Interval(2, 3), new Interval(6, 8)));
		System.out.println(eft(schedule1)); // [[3, 5]]

		List<List<Interval>> schedule2 = newArrayList();
		schedule2.add(asList(new Interval(1, 3), new Interval(9, 12)));
		schedule2.add(asList(new Interval(2, 4)));
		schedule2.add(asList(new Interval(6, 8)));
		System.out.println(eft(schedule2)); // [[4, 6], [8, 9]]

		List<List<Interval>> schedule3 = newArrayList();
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
