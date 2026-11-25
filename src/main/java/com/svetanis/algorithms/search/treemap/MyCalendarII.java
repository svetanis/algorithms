package com.svetanis.algorithms.search.treemap;

import java.util.TreeMap;

// 731. My Calendar II
//
// book(start, end) adds the event [start, end) -- start included, end excluded -- and returns true,
// unless adding it would put three events on at the same moment (a triple booking); then it books
// nothing and returns false. Two events at once are allowed.
//
// The map is a difference array over time that stays sorted while events arrive one at a time: +1
// at each start, -1 at each end, keyed by time in a TreeMap. Walking it in time order with a running
// total gives how many events are on at each moment. book adds the new event's two marks, walks the
// map, and if the total ever passes 2 takes the two marks back out. The bookings are online -- each
// book must answer before the next one arrives -- so the events cannot be sorted once up front, as
// a line sweep would; the TreeMap keeps them sorted as they come.
//
// An end and a start at the same time share one key, so their -1 and +1 are added together before
// the total is tested: [10, 20) and [20, 30) never count as two events on at once.
//
// Time: O(n) per book, n = the events booked so far -- two puts at O(log n), then one walk over up
//       to 2n keys, and the walk is the larger.
// Space: O(n) -- two map entries per event.
//
// Siblings, same package -- bookings arrive one at a time in all of them:
//   MyCalendar     (729)  refuses any overlap: checks only the floor and ceiling neighbours
//   MyCalendarII   (731)  allows two events at once, refuses a third: the marks walked, taken
//                         back out on a refusal (this file)
//   MyCalendarIITwoLists (731)  the same problem without counts: the booked events and the
//                         parts two of them share, in two lists
//   MyCalendarIII  (732)  refuses nothing, returns the most events on at once: the same marks,
//                         walked for the largest running total

public final class MyCalendarII {

	public MyCalendarII() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map; // time -> change in the number of events on

	public boolean book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1); // MARK +1: the event starts
		map.put(end, map.getOrDefault(end, 0) - 1); // MARK -1: and is over from end on, end excluded
		int active = 0; // events on from the current key to the next
		for (int count : map.values()) { // WALK: in time order
			active += count; // COUNT: apply this time's marks
			if (active > 2) { // TRIPLE: > 2, a third event at once -- refuse
				map.put(start, map.get(start) - 1); // UNMARK -1: take the start mark back
				map.put(end, map.get(end) + 1); // UNMARK +1: take the end mark back
				return false;
			}
		}
		return true; // BOOKED
	}

	public static void main(String[] args) {
		MyCalendarII mc = new MyCalendarII();
		System.out.println(mc.book(10, 20)); // true
		System.out.println(mc.book(50, 60)); // true
		System.out.println(mc.book(10, 40)); // true, two events on during 10..20
		System.out.println(mc.book(5, 15)); // false, three would be on during 10..15
		System.out.println(mc.book(5, 10)); // true
		System.out.println(mc.book(25, 55)); // true
	}
}
