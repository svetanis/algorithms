package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeSet;

// 220. Contains Duplicate III
//
// Input: an array a, an index distance dist and a value difference diff.
// Return: true if two different positions i and j have |i - j| <= dist and
// |a[i] - a[j]| <= diff; false otherwise. On LeetCode: 2 <= a.length <= 10^5,
// -10^9 <= a[i] <= 10^9, 1 <= dist <= a.length, 0 <= diff <= 10^9.
//
// The one idea: walk the array left to right, and keep a sorted set of the values at
// the dist positions just before i. The set itself is the position check -- everything
// in it is close enough in position -- so only the value check is left. Of the values in
// the set, the one closest to a[i] decides: if it is not within diff, none is. The
// closest is always one of the two neighbours, the floor (largest value <= a[i]) or the
// ceiling (smallest value >= a[i]), so two lookups replace a scan of the window.
//
// The set holds each value once, and that is safe: if a value equal to a[i] were already
// in the set, the floor would find it and return true before a[i] is added (diff >= 0).
//
// The subtractions are in int. Within LeetCode's range the largest difference is
// 2 * 10^9, below Integer.MAX_VALUE, so they are exact. Over the whole int range they
// wrap: on { Integer.MIN_VALUE, Integer.MAX_VALUE } with diff 0, MAX - MIN is -1 and this
// returns true where the answer is false. ContainsDuplicateIII computes in long for that.
//
// Time: O(n log m) -- each position is added once and removed at most once, and each
// add, remove, floor and ceiling costs O(log m), m = min(n, dist).
// Space: O(m) -- the set never holds more than dist values after a step.
//
// Siblings: ContainsDuplicateIII is the same window with one lookup instead of two,
// ceiling(a[i] - diff), checked against a[i] + diff, in long. DuplicatesKDistAwaySW
// (data-structures, hashmap) is 219, the same window with diff fixed at 0, so a hash
// set's contains replaces the sorted lookups.

public final class ContainsDuplicateIIIFloorCeiling {

	public static boolean duplicate(int[] a, int dist, int diff) {
		TreeSet<Integer> set = new TreeSet<>(); // WINDOW: the last dist values, sorted
		for (int i = 0; i < a.length; i++) { // WALK
			Integer floor = set.floor(a[i]); // BELOW: Integer, null when there is none
			if (floor != null && a[i] - floor <= diff) {
				return true; // close enough in value
			}
			Integer ceiling = set.ceiling(a[i]); // ABOVE
			if (ceiling != null && ceiling - a[i] <= diff) {
				return true; // close enough in value
			}
			set.add(a[i]); // ADD a[i] for the positions after it
			if (i >= dist) {
				set.remove(a[i - dist]); // DROP: too far from position i + 1
			}
		}
		return false; // NONE: no pair close in both position and value
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 1 };
		System.out.println(duplicate(a1, 3, 0)); // true

		int[] a2 = { 1, 5, 9, 1, 5, 9 };
		System.out.println(duplicate(a2, 2, 3)); // false

		int[] a3 = { 1, 0, 1, 1 };
		System.out.println(duplicate(a3, 1, 2)); // true
	}
}
