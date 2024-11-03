package com.svetanis.algorithms.sorting.mergesort.interval;

import static com.svetanis.java.base.collect.Lists.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 2402. Meeting Rooms III

public final class MostBookedMeetingRoom {

	public static int mostBooked(int n, int[][] meetings) {
		List<Interval> intervals = intervals(meetings);
		// List<Interval> sorted = intervals.stream().sorted(Comparator.comparing(i ->
		// i.start)).collect(Collectors.toList());
		List<Interval> sorted = sort(intervals, i -> i.start);
		Queue<Integer> idle = idle(n);
		int[] count = counts(n, sorted, idle);
		return mostBooked(count);
	}

	private static int mostBooked(int[] count) {
		int max = 0;
		for (int i = 0; i < count.length; i++) {
			if (count[max] < count[i]) {
				max = i;
			}
		}
		return max;
	}

	private static int[] counts(int n, List<Interval> sorted, Queue<Integer> idle) {
		int[] count = new int[n];
		Queue<Room> busy = new PriorityQueue<>();
		for (Interval interval : sorted) {
			int start = interval.start;
			int end = interval.end;
			while (!busy.isEmpty() && busy.peek().end <= start) {
				idle.offer(busy.poll().id);
			}
			int id;
			if (!idle.isEmpty()) {
				id = idle.poll();
				busy.offer(new Room(id, end));
			} else {
				Room room = busy.poll();
				id = room.id;
				int extended = room.end + end - start;
				busy.offer(new Room(id, extended));
			}
			count[id]++;
		}
		return count;
	}

	private static Queue<Integer> idle(int n) {
		Queue<Integer> idle = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			idle.offer(i);
		}
		return idle;
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
	}

	private static class Room implements Comparable<Room> {
		public Room(int id, int end) {
			this.id = id;
			this.end = end;
		}

		private int id;
		private int end;

		@Override
		public int compareTo(Room other) {
			if (this.end == other.end) {
				return this.id - other.id;
			}
			return this.end - other.end;
		}
	}
}
