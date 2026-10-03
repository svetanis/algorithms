package com.svetanis.algorithms.intervals.intersect;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1229. Meeting Scheduler
//
// Takes two people's free time slots as [start, end], in any order, where one person's slots do
// not overlap each other, and a meeting duration. Returns the earliest [start, start + duration]
// that fits inside a slot of each person, or an empty list if there is none. A slot exactly as
// long as the meeting fits. Sorts both input arrays in place. LC bounds: times 0 to 10^9,
// start < end in every slot, 1 <= duration <= 10^6, so end - start and start + duration fit in
// an int.
//
// Sort each person's slots by start and keep one pointer in each list. The time both people have
// free inside the two current slots runs from the later start to the earlier end; if it is at
// least duration long, the meeting goes at its start. Then move past the slot that ends first:
// every later slot of the other person starts after the other current slot ends, so after this
// one ends too. The pairs are met in order of their common start, so the first fit is the
// earliest.

public final class MeetingScheduler {
	// Time Complexity: O(n log n + m log m), the two sorts; the walk is O(n + m)
	// Space Complexity: O(1) beyond the sorts, which work on the input arrays

	public static List<Integer> ms(int[][] slots1, int[][] slots2, int duration) {
		int n = slots1.length;
		int m = slots2.length;
		int first = 0;
		int second = 0;
		Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
		Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));
		while (first < n && second < m) {
			int start = Math.max(slots1[first][0], slots2[second][0]);
			int end = Math.min(slots1[first][1], slots2[second][1]);
			if (end - start >= duration) { // >=: an exact fit is enough
				return Arrays.asList(start, start + duration);
			}
			if (slots1[first][1] < slots2[second][1]) { // move past the slot that ends first
				first++;
			} else {
				second++;
			}
		}
		return new ArrayList<>();
	}

	public static void main(String[] args) {
		int[][] slots1 = { { 10, 50 }, { 60, 120 }, { 140, 210 } };
		int[][] slots2 = { { 0, 15 }, { 25, 50 }, { 60, 70 }, { 80, 100 } };
		System.out.println(ms(slots1, slots2, 8)); // [25,33]
	}
}
