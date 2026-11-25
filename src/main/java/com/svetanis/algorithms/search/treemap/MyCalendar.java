package com.svetanis.algorithms.search.treemap;

import java.util.Map;
import java.util.TreeMap;

// 729. My Calendar I
//
// book(start, end) adds the event [start, end) -- start included, end excluded -- and returns true,
// unless it would overlap an event already booked; then it books nothing and returns false. Two
// events that only touch, [10, 20) and [20, 30), do not overlap.
//
// Booked events never overlap one another, so ordered by start they are ordered by end as well. A
// TreeMap from start to end keeps them in that order. A new event can clash with only two of them:
// the last one starting at or before start (floorEntry), which clashes if it ends after start, and
// the first one starting at or after start (ceilingEntry), which clashes if it starts before end.
// Every other event lies wholly before the first of these or wholly after the second.
//
// Time: O(log n) per book, n = the events booked so far -- two lookups and one put, each a walk
//       down a balanced search tree holding n keys.
// Space: O(n) -- one map entry per booked event.
//
// Siblings, same package -- bookings arrive one at a time in all of them:
//   MyCalendar     (729)  refuses any overlap: checks only the floor and ceiling neighbours
//                         (this file)
//   MyCalendarII   (731)  allows two events at once, refuses a third: +1 / -1 marks in a TreeMap,
//                         walked in time order, both marks taken back out on a refusal
//   MyCalendarIITwoLists (731)  the same problem without counts: the booked events and the
//                         parts two of them share, in two lists
//   MyCalendarIII  (732)  refuses nothing, returns the most events on at once: the same marks,
//                         walked for the largest running total

public final class MyCalendar {

	public MyCalendar() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map; // start -> end of each booked event, sorted by start

	public boolean book(int start, int end) {
		Map.Entry<Integer, Integer> floor = map.floorEntry(start); // FLOOR: latest start <= start
		if (floor != null && floor.getValue() > start) { // OVERLAP: reject; >, ending AT start touches
			return false;
		}
		Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(start); // CEILING: next start >= start
		if (ceiling != null && ceiling.getKey() < end) { // OVERLAP: reject; <, starting AT end touches
			return false;
		}
		map.put(start, end); // BOOK
		return true;
	}

	public static void main(String[] args) {
		MyCalendar mc = new MyCalendar();
		System.out.println(mc.book(10, 20)); // true
		System.out.println(mc.book(15, 25)); // false
		System.out.println(mc.book(20, 30)); // true

		MyCalendar mc1 = new MyCalendar();
		System.out.println(mc1.book(47, 50)); // true
		System.out.println(mc1.book(33, 41)); // true
		System.out.println(mc1.book(39, 45)); // false
		System.out.println(mc1.book(33, 42)); // false
		System.out.println(mc1.book(25, 32)); // true
		System.out.println(mc1.book(26, 35)); // false
		System.out.println(mc1.book(19, 25)); // true
		System.out.println(mc1.book(3, 8)); // true
		System.out.println(mc1.book(8, 13)); // true
		System.out.println(mc1.book(18, 27)); // false
	}
}
