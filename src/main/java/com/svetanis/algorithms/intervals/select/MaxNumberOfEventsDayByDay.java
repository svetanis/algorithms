package com.svetanis.algorithms.intervals.select;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// 1353. Maximum Number of Events That Can Be Attended
//
// Input: events as [startDay, endDay], both days included: an event can be attended on any one
// day in that range. Only one event can be attended per day. Returns the most events that can be
// attended. The last day must be below Integer.MAX_VALUE, or the day counter never passes it;
// LC keeps days within 1 <= start <= end <= 10^5, with 1 to 10^5 events.
//
// The one idea: one queue entry is one event that has started and has not been attended yet,
// held as its end day. Walk the calendar one day at a time. On each day: drop the events whose
// end day has passed, add the events that start today, then attend the one whose end day is
// soonest. That choice is safe: if a best plan spends today on some other waiting event, swap the
// two. The soonest-ending event takes today, and the other one takes the day the soonest-ending
// event had in the plan, if it had one. The other one ends no sooner, so it is still open on that
// day. Events that start on a day are found through a map from start day to their end days, so
// nothing is sorted.
//
// Time: O(d + n log n) -- d = last day - first day: the loop visits every day in that range, and
// each event enters and leaves the queue once.
// Space: O(n) -- the map and the queue hold each event once.
//
// Siblings: MaxNumberOfEventsSortByStart makes the same choice each day but sorts the events by
// start and jumps over the days with nothing waiting. No sort is needed here, which pays off while
// d is no larger than n. When the days are sparse, MaxNumberOfEventsSortByStart skips the empty
// days instead of visiting them.

public final class MaxNumberOfEventsDayByDay {

	public static int maxEvents(int[][] events) {
		int first = Integer.MAX_VALUE;
		int last = 0;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int[] event : events) {
			int start = event[0];
			int end = event[1];
			map.computeIfAbsent(start, k -> new ArrayList<>()).add(end); // GROUP by start day
			first = Math.min(first, start); // SPAN: the calendar runs first .. last
			last = Math.max(last, end);
		}
		int count = 0;
		Queue<Integer> pq = new PriorityQueue<>();
		for (int day = first; day <= last; day++) { // WALK: every day, one at a time
			while (!pq.isEmpty() && pq.peek() < day) {
				pq.poll(); // DROP: ended before today, can never be attended
			}
			// the shared empty list: most days start no event
			List<Integer> list = map.getOrDefault(day, emptyList());
			pq.addAll(list); // ADD: the events that start today
			if (!pq.isEmpty()) {
				pq.poll(); // ATTEND the waiting event that ends soonest
				count++;
			}
		}
		return count; // RETURN: one event per day at most
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
		System.out.println(maxEvents(g1)); // 3
		int[][] g2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 2 } };
		System.out.println(maxEvents(g2)); // 4
		int[][] g3 = { { 1, 4 }, { 4, 4 }, { 2, 2 }, { 3, 4 }, { 1, 1 } };
		System.out.println(maxEvents(g3)); // 4
		int[][] g4 = { { 1, 2 }, { 1, 2 }, { 3, 3 }, { 1, 5 }, { 1, 5 } };
		System.out.println(maxEvents(g4)); // 5
	}
}
