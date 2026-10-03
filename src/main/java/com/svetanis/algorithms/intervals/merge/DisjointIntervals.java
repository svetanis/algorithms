package com.svetanis.algorithms.intervals.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.svetanis.algorithms.intervals.Interval;

// 352. Data Stream as Disjoint Intervals
//
// addNum adds a number to the stream; adding one already seen changes nothing. getIntervals
// returns every number seen so far as closed ranges [start, end] of consecutive integers, sorted,
// with no two ranges overlapping or touching. The returned entries are the class's own objects.
// Numbers must stay clear of the int limits: end + 1 and start - 1 are computed and must not wrap
// around, which LC's 0 <= value <= 10^4 ensures.
//
// map holds the ranges. The key of a range is the number that first created it; the range may
// later grow in both directions, but its key always stays inside it, so the keys are in the same
// order as the ranges. For a new value, left is the range with the largest key at or below it and
// right the one with the smallest key at or above it; only these two can hold the value or sit
// directly next to it. Four cases, checked in order: the value fills the one-number gap between
// left and right, so they join; it lies inside left or one past left's end, so left grows; it lies
// inside right or one before right's start, so right grows; otherwise it starts a new range of its
// own.
//
// The same class twice, once per way a range is held, so each source's form can be copied as is:
//
//   class        a range is     getIntervals returns
//   OfArrays     int[] {s, e}   int[][] (LC)
//   OfIntervals  Interval       List<Interval>

public final class DisjointIntervals {
	// Time Complexity: addNum: O(log m), m ranges stored, a few lookups in a balanced tree map;
	// getIntervals: O(m), one copy of the ranges
	// Space Complexity: O(m), one map entry per range

	public static final class OfArrays {
		private final TreeMap<Integer, int[]> map = new TreeMap<>();

		public void addNum(int value) {
			Integer left = map.floorKey(value); // range immediately before the new value
			Integer right = map.ceilingKey(value); // range immediately after the new value
			boolean notNull = left != null && right != null;
			if (notNull && map.get(left)[1] + 1 == value && map.get(right)[0] - 1 == value) {
				map.get(left)[1] = map.get(right)[1]; // the value fills the gap: join the two
				map.remove(right);
			} else if (left != null && value <= map.get(left)[1] + 1) {
				map.get(left)[1] = Math.max(value, map.get(left)[1]); // grow the left range at its end
			} else if (right != null && value >= map.get(right)[0] - 1) {
				map.get(right)[0] = Math.min(value, map.get(right)[0]); // grow the right range; its key stays inside
			} else {
				map.put(value, new int[] { value, value });
			}
		}

		public int[][] getIntervals() {
			int[][] intervals = new int[map.size()][2];
			int i = 0;
			for (int[] interval : map.values()) {
				intervals[i++] = interval;
			}
			return intervals;
		}
	}

	public static final class OfIntervals {
		private final TreeMap<Integer, Interval> map = new TreeMap<>();

		public void addNum(int value) {
			Integer left = map.floorKey(value); // range immediately before the new value
			Integer right = map.ceilingKey(value); // range immediately after the new value
			boolean notNull = left != null && right != null;
			if (notNull && map.get(left).end + 1 == value && map.get(right).start - 1 == value) {
				map.get(left).end = map.get(right).end; // the value fills the gap: join the two
				map.remove(right);
			} else if (left != null && value <= map.get(left).end + 1) {
				map.get(left).end = Math.max(value, map.get(left).end); // grow the left range at its end
			} else if (right != null && value >= map.get(right).start - 1) {
				map.get(right).start = Math.min(value, map.get(right).start); // grow the right range; its key stays inside
			} else {
				map.put(value, new Interval(value, value));
			}
		}

		public List<Interval> getIntervals() {
			return new ArrayList<>(map.values());
		}
	}

	public static void main(String[] args) {
		OfArrays arrays = new OfArrays();
		OfIntervals intervals = new OfIntervals();
		// after 1: [[1, 1]]; 3: [[1, 1], [3, 3]]; 7: [[1, 1], [3, 3], [7, 7]];
		// 2: [[1, 3], [7, 7]]; 6: [[1, 3], [6, 7]] -- each line printed twice
		for (int value : new int[] { 1, 3, 7, 2, 6 }) {
			arrays.addNum(value);
			intervals.addNum(value);
			System.out.println(Arrays.deepToString(arrays.getIntervals()));
			System.out.println(intervals.getIntervals());
		}
	}
}
