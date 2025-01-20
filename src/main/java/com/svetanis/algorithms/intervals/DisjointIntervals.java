package com.svetanis.algorithms.intervals;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.TreeMap;

import com.google.common.collect.ImmutableList;

// 352. Data Stream as Disjoint Intervals

public final class DisjointIntervals {
	// Time Complexity: O(n)

	public DisjointIntervals() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Interval> map;

	public void addNum(int value) {
		// interval immediately before the new value
		Integer left = map.floorKey(value);
		// interval immediately after the new value
		Integer right = map.ceilingKey(value);
		boolean notNull = left != null && right != null;
		if (notNull && map.get(left).end + 1 == value && map.get(right).start - 1 == value) {
			// merge both intervals
			map.get(left).end = map.get(right).end;
			// remove the redundant interval
			map.remove(right);
		} else if (left != null && value <= map.get(left).end + 1) {
			// extend the interval to the left
			map.get(left).end = max(value, map.get(left).end);
		} else if (right != null && value >= map.get(right).start - 1) {
			// extend the interval to the right
			map.get(right).start = min(value, map.get(right).start);
		} else {
			Interval interval = new Interval(value, value);
			map.put(value, interval);
		}
	}

	public ImmutableList<Interval> getIntervals() {
		return newList(map.values());
	}

	public static void main(String[] args) {
		DisjointIntervals srs = new DisjointIntervals();
		srs.addNum(1); // 1
		print(srs.getIntervals()); // [[1,1]]

		srs.addNum(3); // 1, 3
		print(srs.getIntervals()); // [[1,1], [3,3]]

		srs.addNum(7); // 1, 3, 7
		print(srs.getIntervals()); // [[1,1], [3,3], [7,7]]

		srs.addNum(2); // 1, 2, 3, 7
		print(srs.getIntervals()); // [[1,3], [7,7]]

		srs.addNum(6); // 1, 2, 3, 6, 7
		print(srs.getIntervals()); // [[1,3], [6,7]]
	}
}
