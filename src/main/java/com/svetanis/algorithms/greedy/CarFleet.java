package com.svetanis.algorithms.greedy;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

// 853. Car Fleet
//
// Input: target -- the mile marker every car drives to; positions[i] and speed[i] -- where car i
// starts and how fast it drives. All cars drive the same way along one lane, and a car may not
// pass the car ahead: when it catches up, it slows down and the two drive on together as one
// fleet. A car that catches up exactly at target joins that fleet too. Returns the number of
// fleets that arrive. On LeetCode: 1 <= n <= 10^5, 0 < target <= 10^6,
// 0 <= positions[i] < target, all positions different, 0 < speed[i] <= 10^6.
//
// The one idea: look at the cars from the one closest to target backwards. A car's own time, if
// the road ahead were empty, is (target - position) / speed. The car closest to target is never
// held up. A car further back either needs more time than the fleet just ahead of it -- then it
// never catches that fleet and is the front of a new one -- or needs no more time, and then it
// catches up on the way and arrives with that fleet. Joining a fleet never changes the fleet's
// time, so the only number to keep is the time of the fleet just ahead (prev), and a car starts a
// new fleet exactly when its own time is larger than prev.
//
// The times are doubles. Two different times here, each a fraction with a numerator and a
// denominator of at most 10^6, differ by at least one part in 10^12 of their size, far more than
// a double rounds away, and two equal fractions round to the same double. So > compares exactly.
//
// Time: O(n log n) -- sorting the indices; the walk is one pass.
// Space: O(n) -- the array of indices, boxed so it can be sorted with a comparator.

public final class CarFleet {

	public static int carFleet(int target, int[] positions, int[] speed) {
		Integer[] indices = indices(positions); // ORDER: closest to target first
		int count = 0;
		double prev = 0; // the time of the fleet just ahead; every time is above 0
		for (int index : indices) {
			int start = positions[index];
			double time = 1.0 * (target - start) / speed[index]; // TIME on an empty road
			if (time > prev) { // NEW FLEET: slower than the fleet ahead, never catches it
				count++;
				prev = time;
			} // otherwise it catches up and arrives with the fleet ahead
		}
		return count; // RETURN: fleets
	}

	private static Integer[] indices(int[] positions) {
		int n = positions.length;
		Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;
		}
		Arrays.sort(indices, comparingInt((Integer i) -> positions[i]).reversed()); // SORT: largest position first
		return indices; // RETURN: car indices, closest to target first
	}

	public static void main(String[] args) {
		int[] p1 = { 10, 8, 0, 5, 3 };
		int[] s1 = { 2, 4, 1, 1, 3 };
		System.out.println(carFleet(12, p1, s1)); // 3
		int[] p2 = { 3 };
		int[] s2 = { 3 };
		System.out.println(carFleet(10, p2, s2)); // 1
		int[] p3 = { 0, 2, 4 };
		int[] s3 = { 4, 2, 1 };
		System.out.println(carFleet(100, p3, s3)); // 1
	}
}
