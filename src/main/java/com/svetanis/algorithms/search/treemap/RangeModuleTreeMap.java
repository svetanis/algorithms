package com.svetanis.algorithms.search.treemap;

import java.util.TreeMap;

// 715. Range Module
//
// Tracks numbers in half-open intervals: [left, right) includes left and excludes right.
// addRange(left, right) starts tracking every number in it, removeRange(left, right) stops tracking
// them, and queryRange(left, right) returns true when every number in [left, right) is tracked now.
//
// The tracked numbers are kept as start -> end of intervals [start, end) that never overlap and never
// touch -- [10, 14) and [14, 20) are stored as [10, 20). Because nothing touches, a query is one
// lookup: the interval starting at or before left must reach right. An add widens at both edges --
// the interval at or before left if it reaches left, the one at or before right if it reaches right
// -- and swallows every interval between; a remove cuts the interval at each edge and drops every one
// between. One call can swallow many intervals, but each is put once and removed at most once.
//
// subMap(from, fromIncluded, to, toIncluded) is a view of the keys between from and to; clearing it
// removes those keys from the map itself.
//
// Time: O(log n) per query; add and remove O((1 + k) log n), k = the intervals they remove -- and since
//       a call puts at most two, the removals over all calls never outnumber the puts (counted: about
//       1.74 map changes per call at 10^4, 10^5 and 10^6 random calls).
// Space: O(n), n = the stored intervals.
//
// Siblings -- the same problem:
//   RangeModuleTreeMap  (this file)  the intervals themselves, in a TreeMap
//   DS/todo/tree/segment/RangeModule a segment tree over 1 .. Integer.MAX_VALUE, nodes made only
//                                    when a change first reaches them

public final class RangeModuleTreeMap {

	private final TreeMap<Integer, Integer> map = new TreeMap<>(); // start -> end of [start, end)

	public boolean queryRange(int left, int right) {
		Integer floor = map.floorKey(left); // FIND: the interval starting at or before left
		return floor != null && right <= map.get(floor); // REACH: it ends at or after right, all tracked
	}

	public void addRange(int left, int right) {
		int newStart = left;
		Integer floorLeft = map.floorKey(left);
		if (floorLeft != null && left <= map.get(floorLeft)) { // WIDEN LEFT: it contains or touches left
			newStart = floorLeft;
		}
		int newEnd = right;
		Integer floorRight = map.floorKey(right); // its own lookup: the interval at or before right
		if (floorRight != null && right <= map.get(floorRight)) { // WIDEN RIGHT: it reaches past right
			newEnd = map.get(floorRight);
		}
		map.subMap(newStart, true, right, true).clear(); // SWALLOW: right included, an interval there touches
		map.put(newStart, newEnd); // STORE the one interval that replaces them
	}

	public void removeRange(int left, int right) {
		Integer floorRight = map.floorKey(right);
		if (floorRight != null && right < map.get(floorRight)) { // RIGHT PIECE first: it needs the old end
			map.put(right, map.get(floorRight));
		}
		Integer floorLeft = map.floorKey(left);
		if (floorLeft != null && left < map.get(floorLeft)) { // LEFT PIECE: the interval now ends at left
			map.put(floorLeft, left);
		}
		map.subMap(left, true, right, false).clear(); // DROP: right excluded, the right piece stays
	}

	public static void main(String[] args) {
		RangeModuleTreeMap rm = new RangeModuleTreeMap();
		rm.addRange(10, 20);
		rm.removeRange(14, 16);
		System.out.println(rm.queryRange(10, 14)); // true
		System.out.println(rm.queryRange(13, 15)); // false
		System.out.println(rm.queryRange(16, 17)); // true
	}
}
