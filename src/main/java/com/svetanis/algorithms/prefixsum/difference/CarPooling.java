package com.svetanis.algorithms.prefixsum.difference;

// 1094. Car Pooling
//
// Input: trips[i] = [count, start, end] -- count passengers get on at location start and get off
// at location end; capacity -- the seats in the car. Returns true when the car never holds more
// than capacity passengers at once. Passengers who get off at end no longer take a seat there,
// so a trip fills the half-open stretch [start, end): one trip may drop off at the same location
// where another picks up. On LeetCode: 1 <= trips.length <= 1000, 1 <= count <= 100,
// 0 <= start < end <= 1000, 1 <= capacity <= 10^5. The mark array has one cell for each location
// 0 to 1000, so a location above 1000 would throw.
//
// The one idea: one cell passengers[p] holds the change in the number of people in the car at
// location p: +count where a trip gets on, -count where it gets off. The -count goes at end
// itself, not at end + 1, because the stretch is half-open. Adding the cells from left to right
// gives, at each location, the number of people in the car there; the answer is false as soon as
// that running sum passes capacity.
//
// Time: O(n + 1001) -- two marks per trip, then one pass over the 1001 locations.
// Space: O(1001) -- the mark array, whatever the number of trips.

public final class CarPooling {

	public static boolean carPooling(int[][] trips, int capacity) {
		int[] passengers = new int[1001]; // MARKS: one cell per location 0 to 1000
		for (int[] trip : trips) {
			int count = trip[0];
			int start = trip[1];
			int end = trip[2];
			passengers[start] += count; // ON: they take seats from start
			passengers[end] -= count; // OFF: half-open, their seats are free again at end
		}
		int current = 0;
		for (int passenger : passengers) { // WALK: locations left to right
			current += passenger; // people in the car at this location
			if (current > capacity) { // FAIL: more people than seats here
				return false;
			}
		}
		return true; // RETURN: never over capacity
	}

	public static void main(String[] args) {
		int[][] trips1 = { { 2, 1, 5 }, { 3, 3, 7 } };
		System.out.println(carPooling(trips1, 4)); // false
		System.out.println(carPooling(trips1, 5)); // true
	}
}
