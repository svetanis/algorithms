package com.svetanis.algorithms.intervals.overlap;

// 253. Meeting Rooms II
//
// Input: intervals[i] = { start, end } of meeting i, start < end. Returns the fewest rooms that hold
// every meeting with no two meetings in one room at the same time. A meeting occupies [start, end),
// so a meeting ending at 10 and one starting at 10 can use the same room. Every time must lie in
// [0, 1000010), which LC's 0 <= start < end <= 10^6 keeps.
//
// One slot a[t] per time t. Each meeting puts +1 at its start and -1 at its end. Adding the slots up
// from left to right, the running total at t is the number of meetings in progress at t (the -1
// sits on the end itself, so a meeting has left by then). The largest running total is the answer.
//
// The same count other ways: MinMeetingRoomsHeap keeps the free time of each room in use;
// MinMeetingRoomsTwoArrays sorts the starts and the ends apart. This one needs no sort, and pays
// for it with a pass over every slot whatever n is.

public final class MinMeetingRoomsDifferenceArray {
	// Time Complexity: O(n + T), T = 1,000,010 slots added up on every call
	// Space Complexity: O(T), the slot array

	public static int minMeetingRooms(int[][] intervals) {
		int n = 1000010; // one slot per time; every start and end must be below this
		int[] a = new int[n];
		for (int[] interval : intervals) {
			a[interval[0]]++;
			a[interval[1]]--; // gone at its end time, so a meeting starting then does not clash
		}
		int max = a[0];
		for (int i = 1; i < n; i++) {
			a[i] += a[i - 1]; // a[i] becomes the number of meetings in progress at i
			max = Math.max(max, a[i]);
		}
		return max;
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
