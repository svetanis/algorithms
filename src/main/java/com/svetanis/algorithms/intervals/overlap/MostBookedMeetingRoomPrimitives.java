package com.svetanis.algorithms.intervals.overlap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 2402. Meeting Rooms III
//
// n rooms are numbered 0 .. n - 1. meetings[i] = { start, end }, all starts different, and a
// meeting occupies [start, end), so a room whose meeting ends at 10 is free for one starting
// at 10. Meetings are handed out in order of start time: each takes the lowest-numbered free
// room. If no room is free, it waits for the room that frees up first (the lowest number
// among rooms freeing at the same time) and then runs for its original length. Returns the
// room that held the most meetings; on a tie, the lowest room number.
//
// Two heaps, because free and busy rooms are chosen by two different orders. free holds room
// numbers, lowest on top: once a room is free, when it became free no longer matters. busy
// holds { busy until, room }, earliest on top, lower room on a tie. Every room is in exactly
// one of the two, so one of TAKE and DELAY always finds a room and neither needs a guard.
// There is no clock variable: each room's busy until is its own clock. The rows need no
// index: the answer is about rooms, and rooms are never sorted.
//
// Siblings, same problem:
//   MostBookedMeetingRoom  the same two heaps over an Interval list and a Room class that
//                          implements Comparable, split into small methods; the right shape
//                          for production code, more to type under pressure
// Variable names match between the two: free, busy, count, meeting, released, room, next,
// endTime, roomNum.

public final class MostBookedMeetingRoomPrimitives {
	// Time complexity: O(n + m * log(m) + m * log(n)), sorting the m meetings, then at most
	// four heap operations per meeting on heaps of at most n rooms
	// Auxiliary Space: O(n), the two heaps; the meetings are sorted in place

	public static int mostBooked(int n, int[][] meetings) {
		// SORT: by start; this is also the rule "the earlier original start gets the room"
		Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

		// FREE ROOMS: lowest number on top; at the start every room is free
		PriorityQueue<Integer> free = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			free.offer(i);
		}

		// BUSY ROOMS: { busy until, room }, earliest on top, tie -> lowest room.
		// long: delays add up, about 10^5 meetings * 5 * 10^5 each, past int
		PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> a[0] == b[0]
				? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));

		int[] count = new int[n]; // meetings held, per room

		for (int[] meeting : meetings) {
			// RELEASE: every busy room with busy until <= start moves back to free;
			// a room polled and not offered is lost for the rest of the run
			while (!busy.isEmpty() && busy.peek()[0] <= meeting[0]) {
				long[] released = busy.poll();
				free.offer((int) released[1]);
			}
			int room;
			if (!free.isEmpty()) {
				// TAKE: the lowest free number, busy until the meeting's end
				room = free.poll();
				busy.offer(new long[] { meeting[1], room });
			} else {
				// DELAY: the room that frees first; the meeting keeps its duration,
				// so the room's new busy until is its old one plus end - start
				long[] next = busy.poll();
				room = (int) next[1];
				long endTime = next[0] + (meeting[1] - meeting[0]);
				busy.offer(new long[] { endTime, room });
			}
			// COUNT
			count[room] += 1;
		}

		// ANSWER: the room with the largest count; strict > keeps the lower number on a tie
		int roomNum = 0;
		for (int i = 1; i < n; i++) {
			if (count[i] > count[roomNum]) {
				roomNum = i;
			}
		}
		return roomNum;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 10 }, { 1, 5 }, { 2, 7 }, { 3, 4 } };
		System.out.println(mostBooked(2, m1)); // 0

		int[][] m2 = { { 1, 20 }, { 2, 10 }, { 3, 5 }, { 4, 9 }, { 6, 8 } };
		System.out.println(mostBooked(3, m2)); // 1

		// the largest inputs: delays push busy until past Integer.MAX_VALUE;
		// with an int busy until this returns 1
		int[][] m3 = new int[100_000][];
		m3[0] = new int[] { 0, 1 };
		for (int i = 1; i < m3.length; i++) {
			m3[i] = new int[] { i, 500_000 };
		}
		System.out.println(mostBooked(2, m3)); // 0
	}
}
