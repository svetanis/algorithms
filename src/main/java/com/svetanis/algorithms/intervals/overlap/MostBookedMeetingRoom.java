package com.svetanis.algorithms.intervals.overlap;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.svetanis.algorithms.intervals.Interval;

// 2402. Meeting Rooms III
//
// n rooms are numbered 0 .. n - 1. meetings[i] = { start, end }, all starts different, and a
// meeting occupies [start, end), so a room whose meeting ends at 10 is free for one starting
// at 10. Meetings are handed out in order of start time: each takes the lowest-numbered free
// room. If no room is free, it waits for the room that frees up first (the lowest number
// among rooms freeing at the same time) and then runs for its original length. Returns the
// room that held the most meetings; on a tie, the lowest room number.
//
// Two heaps. free holds the numbers of the free rooms, smallest on top. busy holds one entry
// per occupied room: its number and the time it becomes free, earliest time on top, lowest
// number among equal times. Before placing a meeting, every busy room that is free by its
// start moves back to free (RELEASE). If free has a room, the meeting takes the smallest
// number (TAKE). If not, the room on top of busy is the one that frees up first; the meeting
// starts there when it frees, so that room's new free time is its old free time plus the
// meeting's length (DELAY). Delays add up, so free times are kept as long: with up to 10^5
// meetings of up to 5 * 10^5 each (LC's bounds, n <= 100 rooms), a free time can pass the
// int range.
//
// Siblings, same problem:
//   MostBookedMeetingRoomPrimitives  the same loop in one method, over int[] meetings sorted
//                                    in place and long[] { busy until, room } entries with a
//                                    comparator lambda -- quicker to write under pressure
// Variable names match between the two: free, busy, count, meeting, released, room, next,
// endTime, roomNum.

public final class MostBookedMeetingRoom {
	// Time Complexity: O(n + m log m + m log n), sorting the m meetings, then at most four heap
	// operations per meeting on heaps of at most n rooms
	// Space Complexity: O(n + m), the sorted copy of the meetings and the two heaps

	public static int mostBooked(int n, int[][] meetings) {
		List<Interval> intervals = intervals(meetings);
		List<Interval> sorted = new ArrayList<>(intervals); // a copy, in order of start time
		sorted.sort((a, b) -> Integer.compare(a.start, b.start)); // SORT
		Queue<Integer> free = free(n);
		int[] count = counts(n, sorted, free);
		return mostBooked(count);
	}

	// ANSWER: the room with the largest count
	private static int mostBooked(int[] count) {
		int roomNum = 0;
		for (int i = 1; i < count.length; i++) {
			if (count[i] > count[roomNum]) { // strictly more, so a tie keeps the lower room number
				roomNum = i;
			}
		}
		return roomNum;
	}

	private static int[] counts(int n, List<Interval> sorted, Queue<Integer> free) {
		int[] count = new int[n];
		Queue<Room> busy = new PriorityQueue<>();
		for (Interval meeting : sorted) {
			// RELEASE: free by this start; the room goes back, not away
			while (!busy.isEmpty() && busy.peek().busyUntil <= meeting.start) {
				Room released = busy.poll();
				free.offer(released.room);
			}
			int room;
			if (!free.isEmpty()) {
				// TAKE: the lowest-numbered free room
				room = free.poll();
				busy.offer(new Room(room, meeting.end));
			} else {
				// DELAY: the room that frees up first; the meeting waits for it
				Room next = busy.poll();
				room = next.room;
				long endTime = next.busyUntil + (meeting.end - meeting.start); // + the duration
				busy.offer(new Room(room, endTime));
			}
			count[room] += 1; // COUNT
		}
		return count;
	}

	// FREE ROOMS: at the start every room is free
	private static Queue<Integer> free(int n) {
		Queue<Integer> free = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			free.offer(i);
		}
		return free;
	}

	private static List<Interval> intervals(int[][] meetings) {
		List<Interval> list = new ArrayList<>();
		for (int[] meeting : meetings) {
			list.add(new Interval(meeting[0], meeting[1]));
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 10 }, { 1, 5 }, { 2, 7 }, { 3, 4 } };
		System.out.println(mostBooked(2, m1)); // 0

		int[][] m2 = { { 1, 20 }, { 2, 10 }, { 3, 5 }, { 4, 9 }, { 6, 8 } };
		System.out.println(mostBooked(3, m2)); // 1

		// the largest inputs: delays push end times past Integer.MAX_VALUE
		int[][] m3 = new int[100_000][];
		for (int i = 0; i < m3.length; i++) {
			m3[i] = new int[] { i, 500_000 - (i % 7) * 1000 };
		}
		System.out.println(mostBooked(3, m3)); // 0
	}

	// BUSY ROOMS: earliest busy until on top, lower room on a tie
	private static class Room implements Comparable<Room> {
		public Room(int room, long busyUntil) {
			this.room = room;
			this.busyUntil = busyUntil;
		}

		private int room;
		private long busyUntil;

		@Override
		public int compareTo(Room other) {
			if (this.busyUntil == other.busyUntil) {
				return Integer.compare(this.room, other.room); // same free time: lower number first
			}
			return Long.compare(this.busyUntil, other.busyUntil);
		}
	}
}
