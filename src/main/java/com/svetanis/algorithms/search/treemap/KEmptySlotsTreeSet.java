package com.svetanis.algorithms.search.treemap;

import java.util.TreeSet;

// 683. K Empty Slots
//
// Input: bulbs, where bulbs[d - 1] is the position (1..n) of the bulb turned on on day d; each
// position appears exactly once. Returns the first day on which some two lit bulbs have exactly
// k bulbs between them and all k of those are off, or -1 if no day does.
//
// Two lit bulbs a < b have b - a - 1 bulbs strictly between them. For all of those to be off,
// no lit bulb may sit between a and b, so the only lit bulbs that can pair with today's bulb are
// its nearest lit neighbours: the floor below it and the ceiling above it in a sorted set of the
// lit positions. Both lookups run before today's bulb is added, or they return the bulb itself.
// Lighting a bulb never creates "all off between" for an old pair, it can only break one, so a
// pair qualifies first on the day its second bulb lights -- checking today's bulb finds the first
// day.
//
// Time: O(n log n) -- per day two lookups and one add, each O(log n).
// Space: O(n) -- the set of lit positions.
//
// Siblings: KEmptySlots (todo, bit) checks the same two candidates, the positions k + 1 to the
// left and right, and counts the lit bulbs between them with a Fenwick tree.

public final class KEmptySlotsTreeSet {

	public static int kEmptySlots(int[] bulbs, int k) {
		TreeSet<Integer> set = new TreeSet<>(); // LIT: positions turned on so far, sorted
		for (int day = 0; day < bulbs.length; day++) { // DAY: bulbs[day] lights on day + 1
			Integer floor = set.floor(bulbs[day]); // LEFT: before the add, or it returns itself
			if (floor != null && bulbs[day] - floor - 1 == k) {
				return day + 1; // exactly k between: b - a - 1
			}
			Integer ceiling = set.ceiling(bulbs[day]); // RIGHT
			if (ceiling != null && ceiling - bulbs[day] - 1 == k) {
				return day + 1;
			}
			set.add(bulbs[day]); // LIGHT
		}
		return -1; // NONE: no day had such a pair
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 2 };
		System.out.println(kEmptySlots(a1, 1)); // 2
		int[] a2 = { 1, 2, 3 };
		System.out.println(kEmptySlots(a2, 1)); // -1
		int[] a3 = { 2, 1, 4, 3 };
		System.out.println(kEmptySlots(a3, 1)); // 3
		int[] a4 = { 3, 1, 5, 4, 2 };
		System.out.println(kEmptySlots(a4, 1)); // 2
		int[] a5 = { 6, 5, 8, 9, 7, 1, 10, 2, 3, 4 };
		System.out.println(kEmptySlots(a5, 2)); // 8
	}
}
