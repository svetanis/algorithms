package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 759. Employee Free Time

public final class EmployeeFreeTime759 {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static List<Interval> eft(List<List<Interval>> schedule) {
		List<Interval> list = new ArrayList<>();
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
		for (List<Interval> intervals : schedule) {
			for (Interval interval : intervals) {
				pq.offer(interval);
			}
		}
		Interval prev = pq.poll();
		while (!pq.isEmpty()) {
			Interval curr = pq.poll();
			if (curr.start > prev.end) {
				list.add(new Interval(prev.end, curr.start));
			} else {
				curr.end = Math.max(curr.end, prev.end);
			}
			prev = curr;
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
		Collections.sort(intervals, (a, b) -> a.start - b.start);
		int end = intervals.get(0).end;
		List<Interval> list = new ArrayList<>();
		for (Interval interval : intervals) {
			if (interval.start > end) {
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
}