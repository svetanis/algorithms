package com.svetanis.algorithms.intervals.overlap;

import java.util.Arrays;

// 253. Meeting Rooms II
//
// Input: intervals[i] = { start, end } of meeting i, start < end (LC guarantees it, with at least
// one meeting). Returns the fewest rooms that hold every meeting with no two meetings in one room at
// the same time. A meeting occupies [start, end), so a meeting ending at 10 and one starting at 10
// can use the same room. The caller's array is not changed.
//
// The number running at time t is (starts <= t) - (ends <= t), and neither count needs to know which
// end belongs to which start -- so the starts and the ends are copied out and sorted apart. The count
// only rises at a start, so the walk steps through the starts. ended counts the ends already passed
// and, the ends being sorted, is also the position of the next end to check. It answers how many
// rooms, not which room: the end being crossed off is no longer tied to its meeting, which is what
// the heap in MinMeetingRoomsHeap keeps.
//
// The same count other ways: MinMeetingRoomsHeap keeps the free time of each room in use;
// MinMeetingRoomsDifferenceArray marks +1/-1 in one slot per time.

public final class MinMeetingRoomsTwoArrays {
	// Time Complexity: O(n log n), the two sorts; ended moves forward at most n times
	// Space Complexity: O(n), the two arrays

	public static int minMeetingRooms(int[][] intervals) {
		int n = intervals.length;
		int[] starts = new int[n]; // SPLIT: the pairing is not needed for a count
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = intervals[i][0];
			ends[i] = intervals[i][1];
		}
		Arrays.sort(starts); // SORT each on its own
		Arrays.sort(ends);
		int ended = 0; // count of finished = position of the next end; never passes i
		int highest = 0;
		for (int i = 0; i < n; i++) { // WALK the starts: a new peak is always at a start
			while (ends[ended] <= starts[i]) { // CROSS OFF: <=, a room freed at 10 is free at 10
				ended++;
			}
			int running = (i + 1) - ended; // COUNT: started minus finished
			highest = Math.max(highest, running); // PEAK
		}
		return highest;
	}

	public static void main(String[] args) {
		System.out.println(minMeetingRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } })); // 2
		System.out.println(minMeetingRooms(new int[][] { { 7, 10 }, { 2, 4 } })); // 1
		System.out.println(minMeetingRooms(new int[][] { { 5, 10 }, { 10, 15 } })); // 1: freed at 10, free at 10
	}
}
