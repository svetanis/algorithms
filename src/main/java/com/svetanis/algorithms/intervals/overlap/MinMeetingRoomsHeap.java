package com.svetanis.algorithms.intervals.overlap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 253. Meeting Rooms II
//
// Input: intervals[i] = { start, end } of meeting i, start < end (LC guarantees it, with at least
// one meeting). Returns the fewest rooms that hold every meeting with no two meetings in one room at
// the same time. A meeting occupies [start, end): it has left its room at its end time, so a meeting
// ending at 10 and one starting at 10 can use the same room. Both methods sort the caller's array in
// place.
//
// Meetings are taken in order of start time, and the heap holds one entry per room in use: the time
// that room becomes free, the soonest on top. Two variations of the same walk:
//
//   minMeetingRooms  a while frees EVERY room free by this start, so after it the heap is exactly
//                    the meetings running at this start; the answer is the largest size seen.
//   roomsOpened      an if frees at most ONE room, the one the meeting takes; otherwise a new room
//                    is opened. The heap never shrinks, so its size at the end is the rooms opened,
//                    and a room is only opened when all are busy.
//
// A while with the size read only at the end is wrong: [[0,3],[2,4],[6,10]] drains the heap to 1
// after the peak of 2.
//
// The same count other ways: MinMeetingRoomsTwoArrays sorts the starts and the ends apart;
// MinMeetingRoomsDifferenceArray marks +1/-1 in one slot per time. MinPlatformsTwoArrays and its
// siblings answer the same question when a departure at t still holds its platform at t.

public final class MinMeetingRoomsHeap {
	// Time Complexity: O(n log n), the sort; one or two heap operations per meeting
	// Space Complexity: O(n), the heap

	public static int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // SORT by start
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // one free time per room in use
		int highest = 0;
		for (int i = 0; i < intervals.length; i++) {
			int start = intervals[i][0], end = intervals[i][1];
			while (!pq.isEmpty() && pq.peek() <= start) { // FREE: '<=', freed at 10 is free at 10
				pq.poll();
			}
			pq.offer(end); // BOOK: always, reused room or new
			highest = Math.max(highest, pq.size()); // PEAK: the while can shrink the heap
		}
		return highest;
	}

	public static int roomsOpened(int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // one free-at time per room
		for (int[] interval : intervals) {
			if (!pq.isEmpty() && pq.peek() <= interval[0]) { // that room is free: reuse it
				pq.poll();
			}
			pq.offer(interval[1]);
		}
		return pq.size(); // rooms opened
	}

	public static void main(String[] args) {
		int[][][] cases = { { { 0, 30 }, { 5, 10 }, { 15, 20 } }, // 2
				{ { 7, 10 }, { 2, 4 } }, // 1
				{ { 5, 10 }, { 10, 15 } }, // 1: freed at 10, free at 10
				{ { 0, 3 }, { 2, 4 }, { 6, 10 } } }; // 2: the while drains to 1 after the peak
		for (int[][] c : cases) {
			System.out.println(minMeetingRooms(c) + " " + roomsOpened(c));
		}
	}
}
