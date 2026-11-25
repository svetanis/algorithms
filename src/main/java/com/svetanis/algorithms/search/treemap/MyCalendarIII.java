package com.svetanis.algorithms.search.treemap;

import java.util.TreeMap;

// 732. My Calendar III
//
// book(start, end) adds the event [start, end) -- start included, end excluded -- and returns the
// largest number of events that are on at the same moment, over everything booked so far.
//
// The same map as MyCalendarII (731): +1 at each start, -1 at each end, keyed by time in a TreeMap
// that stays sorted while events arrive one at a time. A running total over it in time order is the
// number of events on at each moment, and the answer is its largest value. Nothing is ever refused,
// so nothing is undone. The bookings are online, so this walks the whole map on every book rather
// than sorting all events once.
//
// An end and a start at the same time share one key, so their -1 and +1 are added together before
// the largest total is taken: [10, 20) and [20, 30) never count as two events on at once.
//
// Time: O(n) per book, n = the events booked so far -- two puts at O(log n), then one walk over up
//       to 2n keys, and the walk is the larger.
// Space: O(n) -- two map entries per event.
//
// Siblings, same package -- bookings arrive one at a time in all of them:
//   MyCalendar     (729)  refuses any overlap: checks only the floor and ceiling neighbours
//   MyCalendarII   (731)  allows two events at once, refuses a third: the same marks, taken back
//                         out on a refusal
//   MyCalendarIITwoLists (731)  the same problem without counts: the booked events and the
//                         parts two of them share, in two lists
//   MyCalendarIII  (732)  refuses nothing, returns the most events on at once: the marks walked
//                         for the largest running total (this file)

public final class MyCalendarIII {

	public MyCalendarIII() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map; // time -> change in the number of events on

	public int book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1); // MARK +1: the event starts
		map.put(end, map.getOrDefault(end, 0) - 1); // MARK -1: and is over from end on, end excluded
		int active = 0; // events on from the current key to the next
		int max = 0; // most events on at once so far
		for (int count : map.values()) { // WALK: in time order
			active += count; // COUNT: apply this time's marks
			max = Math.max(max, active); // BEST: most events on at once
		}
		return max; // REPORT
	}

	public static void main(String[] args) {
		MyCalendarIII mc = new MyCalendarIII();
		System.out.println(mc.book(10, 20)); // 1
		System.out.println(mc.book(50, 60)); // 1
		System.out.println(mc.book(10, 40)); // 2
		System.out.println(mc.book(5, 15)); // 3
		System.out.println(mc.book(5, 10)); // 3
		System.out.println(mc.book(25, 55)); // 3
	}
}
