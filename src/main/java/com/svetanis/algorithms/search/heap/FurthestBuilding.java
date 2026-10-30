package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1642. Furthest Building You Can Reach
//
// Walk from building 0 to the right. Stepping down or level is free; a climb of d needs either
// d bricks or one ladder. Given heights, bricks and ladders, returns the index of the furthest
// building that can be reached.
//
// A ladder is worth most on the biggest climbs, but the biggest climbs are not known in advance.
// So give every climb a ladder for now and keep those climbs in a min-heap. When the heap holds
// more climbs than there are ladders, the SMALLEST of them is the one to pay for in bricks instead.
// The walk stops at the first building where the bricks go negative.
//
// Time: O(n log l) -- the heap never holds more than ladders + 1 climbs
// Space: O(l), l = ladders
//
// Siblings, same move (take now, give back the worst when a limit breaks):
//   CourseScheduleIII (630)            -- the heap holds the durations of the courses taken
//   greedy/MinNumOfRefuelingStops (871) -- the mirror: pass now, take the best one back later

public final class FurthestBuilding {

	public static int furthestBuilding(int[] heights, int bricks, int ladders) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();  // MIN-HEAP: the climbs holding a ladder, smallest on top
		for (int i = 0; i < heights.length - 1; i++) {
			int diff = heights[i + 1] - heights[i];
			if (diff > 0) {                                  // CLIMB: only an upward step costs anything
				pq.offer(diff);                              // LADDER: give this climb a ladder for now
				if (pq.size() > ladders) {
					bricks -= pq.poll();                     // SWAP OUT: the smallest laddered climb is paid in bricks
				}
				if (bricks < 0) {
					return i;                                // STOP: building i + 1 cannot be reached
				}
			}
		}
		return heights.length - 1;                           // REPORT: the last building was reached
	}

	public static void main(String[] args) {
		int[] heights = { 4, 2, 7, 6, 9, 14, 12 };
		System.out.println(furthestBuilding(heights, 5, 1)); // 4
		int[] heights2 = { 4, 12, 2, 7, 3, 18, 20, 3, 19 };
		System.out.println(furthestBuilding(heights2, 10, 2)); // 7
		int[] heights3 = { 14, 3, 19, 3 };
		System.out.println(furthestBuilding(heights3, 17, 0)); // 3
	}
}