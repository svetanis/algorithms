package com.svetanis.algorithms.intervals;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.filterValues;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

// lamp[i][0] - coordinate
// lamp[i][1] - radius

// range: lamp[i][0] - lamp[i][1] to lamp[i][0] + lamp[i][1]

public final class OverlappingIntervals {

	public static Optional<Integer> overlap(int[][] m) {
		List<Interval> list = newArrayList();
		for (int[] lamp : m) {
			int start = lamp[0] - lamp[1];
			int end = lamp[0] + lamp[1];
			list.add(new Interval(start, end));
		}
		Map<Integer, Integer> map = newHashMap();
		for (Interval interval : list) {
			for (int i = interval.start; i <= interval.end; i++) {
				map.put(i, map.getOrDefault(i, 0) + 1);
			}
		}
		Map<Integer, Integer> filtered = filterValues(map, v -> v == list.size());
		if (filtered.size() == 1) {
			return of(filtered.keySet().iterator().next());
		}
		return absent();
	}

	public static void main(String[] args) {
		int[][] m1 = { { -2, 3 }, { 2, 3 }, { 2, 1 } };
		int[][] m2 = { { -2, 1 }, { 2, 1 } };
		System.out.println(overlap(m1)); // 1
		System.out.println(overlap(m2)); // ?? -3
	}
}
