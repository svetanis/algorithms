package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeSet;

// 220. Contains Duplicate III
//
// Input: an array a, an index distance dist and a value difference diff.
// Return: true if two different positions i and j have |i - j| <= dist and
// |a[i] - a[j]| <= diff; false otherwise.
//
// The one idea: walk the array left to right, and keep a sorted set of the values at
// the dist positions just before i -- the only earlier positions close enough to pair
// with i. A value close enough to a[i] lies between a[i] - diff and a[i] + diff. The
// sorted set answers "the smallest value at or above a[i] - diff" in one lookup
// (ceiling). If that value is at most a[i] + diff, it is a match. If it is above
// a[i] + diff, every other value at or above a[i] - diff is larger still, and every
// value below a[i] - diff is too small, so no value in the window matches.
//
// The set holds each value once, and that is safe: if a value equal to a[i] were
// already in the set, the lookup would find it and return true before a[i] is added
// (diff >= 0). So the set never holds two copies, its size is the number of positions
// in the window, and removing a[i - dist] by value removes exactly the position that
// has just gone out of reach. The arithmetic is in long, so a[i] - diff and a[i] + diff
// cannot wrap around near the int limits.
//
// dist must be 0 or more: a negative dist reads a[i - dist] past the end of the array
// and throws ArrayIndexOutOfBoundsException. A negative diff returns false, which is
// right, since no absolute difference is negative.
//
// Time: O(n log m) -- each position is added once and removed at most once, and each
// add, remove and ceiling on the sorted set costs O(log m), m = min(n, dist + 1).
// Space: O(m) -- the set holds at most dist + 1 values, and dist after the removal.
//
// Siblings: ContainsDuplicateIIIFloorCeiling is the same window with two lookups, the
// floor and the ceiling of a[i], in int -- exact within LeetCode's range only.
// DuplicatesKDistAwaySW (data-structures, hashmap) is 219, the same window of
// the last k positions with diff fixed at 0, so a hash set's contains replaces the
// sorted lookup. DuplicatesKDistAway is 219 with a map from value to its last index
// and no window. ContainsDuplicate217 has no distance limit: any repeat anywhere.

public final class ContainsDuplicateIII {

	public static boolean duplicate(int[] a, int dist, int diff) {
		TreeSet<Long> set = new TreeSet<>();
		for (int i = 0; i < a.length; i++) {
			long target = (long) a[i] - (long) diff; // LOW: smallest value close enough to a[i]
			long upper = (long) a[i] + (long) diff; // HIGH: largest value close enough to a[i]
			Long ceil = set.ceiling(target); // FIND the smallest window value >= LOW
			if (ceil != null && ceil <= upper) { // MATCH: it is also <= HIGH
				return true; // RETURN
			}
			set.add((long) a[i]); // ADD a[i] for the positions after it
			if (set.size() > dist) { // DROP: a[i - dist] is out of reach of position i + 1
				set.remove((long) a[i - dist]);
			}
		}
		return false; // RETURN: no pair close in both position and value
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 1 };
		System.out.println(duplicate(a1, 3, 0)); // true

		int[] a2 = { 1, 5, 9, 1, 5, 9 };
		System.out.println(duplicate(a2, 2, 3)); // false

		int[] a3 = { 1, 0, 1, 1 };
		System.out.println(duplicate(a3, 1, 2)); // true

		int[] a4 = { 1, 1 };
		System.out.println(duplicate(a4, 0, 0)); // false

		int[] a5 = { Integer.MIN_VALUE, Integer.MAX_VALUE };
		System.out.println(duplicate(a5, 1, Integer.MAX_VALUE)); // false
	}
}
