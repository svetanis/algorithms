package com.svetanis.algorithms.search.treemap;

import java.util.ArrayList;
import java.util.List;

// 731. My Calendar II
//
// book(start, end) adds the event [start, end) -- start included, end excluded -- and returns true,
// unless adding it would put three events on at the same moment (a triple booking); then it books
// nothing and returns false. Two events at once are allowed.
//
// Two lists instead of counts: every booked event, and every part two events share -- the stretches
// already double-booked. A new event is refused if it overlaps any shared part, since it would be the
// third event there. Otherwise its shared part with each booked event it overlaps, [max of the
// starts, min of the ends), is added to the shared parts, and the event is booked.
//
// Two intervals overlap exactly when their shared part is not empty: max of the starts < min of the
// ends. Strict, because an end is excluded -- [10, 20) and [20, 30) share nothing.
//
// The shared parts never overlap each other -- a moment inside two of them would be covered by three
// events -- but the booked events can, so each book walks both lists in full. No sorted order is
// needed, and the route allows two events at once and no more: three would need a third list.
//
// Time: O(n) per book, n = the events booked so far -- one walk over the booked events, and one
//       over the shared parts, which are disjoint and end at booked times, so fewer than 2n.
// Space: O(n).
//
// Siblings, same package -- bookings arrive one at a time in all of them:
//   MyCalendar            (729)  refuses any overlap: checks only the floor and ceiling neighbours
//   MyCalendarII          (731)  the same problem with counts: +1 / -1 marks in a TreeMap, walked
//   MyCalendarIITwoLists  (731)  the booked events and their shared parts in two lists (this file)
//   MyCalendarIII         (732)  refuses nothing, returns the most events on at once: the same marks,
//                                walked for the largest running total

public final class MyCalendarIITwoLists {

	public MyCalendarIITwoLists() {
		this.bookings = new ArrayList<>();
		this.overlaps = new ArrayList<>();
	}

	private List<int[]> bookings; // every booked event, {start, end}
	private List<int[]> overlaps; // every part two booked events share: two events on there

	public boolean book(int start, int end) {
		for (int[] o : overlaps) { // REFUSE: it would be the third event somewhere in o
			if (overlap(start, end, o[0], o[1])) {
				return false;
			}
		}
		for (int[] b : bookings) { // RECORD: its shared part with each booked event
			if (overlap(start, end, b[0], b[1])) {
				overlaps.add(new int[] { Math.max(start, b[0]), Math.min(end, b[1]) });
			}
		}
		bookings.add(new int[] { start, end }); // BOOKED
		return true;
	}

	// SHARED PART NOT EMPTY: [max of starts, min of ends) holds at least one moment
	private static boolean overlap(int start1, int end1, int start2, int end2) {
		return Math.max(start1, start2) < Math.min(end1, end2);
	}

	public static void main(String[] args) {
		MyCalendarIITwoLists mc = new MyCalendarIITwoLists();
		System.out.println(mc.book(10, 20)); // true
		System.out.println(mc.book(50, 60)); // true
		System.out.println(mc.book(10, 40)); // true, two events on during 10..20
		System.out.println(mc.book(5, 15)); // false, three would be on during 10..15
		System.out.println(mc.book(5, 10)); // true
		System.out.println(mc.book(25, 55)); // true
	}
}
