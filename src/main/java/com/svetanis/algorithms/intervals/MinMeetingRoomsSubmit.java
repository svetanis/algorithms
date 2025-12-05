package com.svetanis.algorithms.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

// 253. Meeting Rooms II

// Given a list of intervals representing 
// the start and end time of ‘N’ meetings, 
// find the minimum number of rooms required 
// to hold all the meetings.

public final class MinMeetingRoomsSubmit {
	// Time Complexity: O(n*log n)
	// Space Complexity: O(n)

	public static int mmr(int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int[] interval : intervals) {
			if (!pq.isEmpty() && pq.peek() <= interval[0]) {
				pq.poll();
			}
			pq.offer(interval[1]);
		}
		return pq.size();
	}

	public static int minMeetingRooms(int[][] intervals) {
		int n = 1000010;
		int[] a = new int[n];
		for (int[] interval : intervals) {
			a[interval[0]]++;
			a[interval[1]]--;
		}
		int min = a[0];
		for (int i = 1; i < n; i++) {
			a[i] += a[i - 1];
			min = Math.max(min, a[i]);
		}
		return min;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 4, 5 }, { 2, 3 }, { 2, 4 }, { 3, 5 } };
		System.out.println(minMeetingRooms(intervals1)); // 2

		int[][] intervals2 = { { 1, 4 }, { 2, 5 }, { 7, 9 } };
		System.out.println(minMeetingRooms(intervals2)); // 2

		int[][] intervals3 = { { 6, 7 }, { 2, 4 }, { 8, 12 } };
		System.out.println(minMeetingRooms(intervals3)); // 1
	}
}
