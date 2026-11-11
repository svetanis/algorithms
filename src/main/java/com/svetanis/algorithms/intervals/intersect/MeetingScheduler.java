package com.svetanis.algorithms.intervals.intersect;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1229. Meeting Scheduler
//
// Input: two people's free time slots as [start, end], in any order, where one person's slots do
// not overlap each other, and a meeting duration. Returns the earliest [start, start + duration]
// that fits inside a slot of each person, or an empty list if there is none. A slot exactly as
// long as the meeting fits. Sorts both input arrays in place. On LeetCode: 1 <= slots1.length,
// slots2.length <= 10^4, 0 <= start < end <= 10^9 in every slot, 1 <= duration <= 10^6, so
// end - start and start + duration fit in an int.
//
// The one idea: one index per person points at that person's current slot, and the time both
// people have free inside the two current slots runs from the later start to the earlier end. If
// it is at least duration long, the meeting goes at its start. Otherwise move past the slot that
// ends first: every later slot of the other person starts after the other current slot ends, so
// after this one ends too, and the slot that ends first can share no more time with anyone. With
// both lists sorted by start, the pairs are met in order of their common start, so the first fit
// is the earliest.
//
// Time: O(n log n + m log m) -- the two sorts; the walk after them is O(n + m).
// Space: O(1) -- beyond the sorts, which work on the input arrays.
//
// Siblings: none for 1229. IntervalIntersection (986) makes the same walk and keeps every shared
// stretch instead of stopping at the first long enough.

public final class MeetingScheduler {

	public static List<Integer> ms(int[][] slots1, int[][] slots2, int duration) {
		int n = slots1.length;
		int m = slots2.length;
		int first = 0; // POINT: one index per person
		int second = 0;
		Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0])); // SORT each person's by start
		Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));
		while (first < n && second < m) { // STOP: either person out of slots
			int start = Math.max(slots1[first][0], slots2[second][0]); // SHARE: both are free
			int end = Math.min(slots1[first][1], slots2[second][1]); //        until either is not
			if (end - start >= duration) { // FIT; >=: an exact fit is enough
				return Arrays.asList(start, start + duration); // RETURN: the earliest fit
			}
			if (slots1[first][1] < slots2[second][1]) { // RETIRE the slot that ends first
				first++;
			} else {
				second++;
			}
		}
		return new ArrayList<>(); // NONE: no pair of slots is long enough
	}

	public static void main(String[] args) {
		int[][] slots1 = { { 10, 50 }, { 60, 120 }, { 140, 210 } };
		int[][] slots2 = { { 0, 15 }, { 25, 50 }, { 60, 70 }, { 80, 100 } };
		System.out.println(ms(slots1, slots2, 8)); // [25, 33]
	}
}
