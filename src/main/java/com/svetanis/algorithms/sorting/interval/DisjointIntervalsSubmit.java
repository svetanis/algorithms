package com.svetanis.algorithms.sorting.interval;

import static com.svetanis.java.base.utils.Print.print;

import java.util.TreeMap;

// 352. Data Stream as Disjoint Intervals

public final class DisjointIntervalsSubmit {
	// Time Complexity: O(n)

	public DisjointIntervalsSubmit() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, int[]> map;

	public void addNum(int value) {
		// interval immediately before the new value
		Integer left = map.floorKey(value);
		// interval immediately after the new value
		Integer right = map.ceilingKey(value);
		boolean notNull = left != null && right != null;
		if (notNull && map.get(left)[1] + 1 == value && map.get(right)[0] - 1 == value) {
			// merge both intervals
			map.get(left)[1] = map.get(right)[1];
			// remove the redundant interval
			map.remove(right);
		} else if (left != null && value <= map.get(left)[1] + 1) {
			// extend the interval to the left
			map.get(left)[1] = Math.max(value, map.get(left)[1]);
		} else if (right != null && value >= map.get(right)[0] - 1) {
			// extend the interval to the right
			map.get(right)[0] = Math.min(value, map.get(right)[0]);
		} else {
			int[] interval = { value, value };
			map.put(value, interval);
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

	public static void main(String[] args) {
		DisjointIntervalsSubmit srs = new DisjointIntervalsSubmit();
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
