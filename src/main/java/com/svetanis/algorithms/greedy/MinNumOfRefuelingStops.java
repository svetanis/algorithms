package com.svetanis.algorithms.greedy;

import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;

// 871. Minimum Number of Refueling Stops
//
// Input: target -- the miles to the destination; startFuel -- the liters in the tank at the start;
// stations[i] = { position, fuel } -- a station that many miles from the start, holding that many
// liters. The car burns one liter per mile and its tank has no limit; stopping at a station takes
// all of its fuel. Arriving anywhere with 0 liters still counts. Returns the fewest stops that reach
// target, or -1 if it cannot be reached. On LeetCode: 1 <= target, startFuel <= 10^9, 0 <=
// stations.length <= 500, 1 <= position_i < position_(i+1) < target, 1 <= fuel_i < 10^9. The code
// needs the stations in that position order; the inner loop stops at the first station out of
// reach. fuel stays inside an int: the loop adds one station only while fuel < target <= 10^9, so
// fuel stays below 2 * 10^9.
//
// The one idea: fuel here is not what is left in the tank but how far the car can get -- startFuel
// plus every station chosen so far -- because one liter is one mile. A station at position p can be
// used exactly when p <= fuel, and choosing it adds its whole fuel to the reach. Every station in
// reach goes into a heap, largest fuel on top, without deciding anything yet. Only when the reach
// falls short of target must one more stop be made, and it should be the station in the heap with
// the most fuel: when the car stopped never matters, only which stations it stopped at, and the
// biggest one pushes the reach furthest, which brings in at least as many new stations as any
// other choice would. So each forced stop is the best possible, and none is ever taken back. An
// empty heap with the target still out of reach means -1.
//
// Time: O(n log n) -- each station enters the heap once and leaves at most once; the outer loop
// takes one station from the heap per round, so it runs at most n + 1 times.
// Space: O(n) -- the heap.

public final class MinNumOfRefuelingStops {

	public static int mrs(int target, int startFuel, int[][] stations) {
		int n = stations.length;
		int count = 0;
		int index = 0;
		int fuel = startFuel; // REACH: the furthest mile the chosen stops get to
		PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder()); // HEAP: largest fuel on top
		while (fuel < target) {
			while (index < n && stations[index][0] <= fuel) {
				pq.offer(stations[index++][1]); // KEEP: every station in reach goes into the heap
			}
			if (pq.isEmpty()) {
				return -1; // FAIL: out of reach, nothing left to take
			}
			fuel += pq.poll(); // STOP: take the biggest station passed
			count++;
		}
		return count; // RETURN: the forced stops
	}

	public static void main(String[] args) {
		int[][] s1 = {};
		System.out.println(mrs(1, 1, s1)); // 0
		int[][] s2 = { { 10, 100 } };
		System.out.println(mrs(100, 1, s2)); // -1
		int[][] s3 = { { 10, 60 }, { 20, 30 }, { 30, 30 }, { 60, 40 } };
		System.out.println(mrs(100, 10, s3)); // 2
	}
}
