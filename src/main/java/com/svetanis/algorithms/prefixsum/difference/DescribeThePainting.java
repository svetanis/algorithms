package com.svetanis.algorithms.prefixsum.difference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

// 1943. Describe the Painting
//
// Input: segments[i] = [start, end, color] -- paints the half-open stretch [start, end) with
// color, a positive number, every color different. Where segments overlap their colors mix into a
// set, and a mix is written as the sum of its colors. Returns the painted stretches as
// [start, end, sum], left to right, leaving out the unpainted gaps. A stretch ends at every point
// where some segment starts or ends, even when the sum is the same on both sides of that point,
// since the set of colors is not. On LeetCode: 1 <= segments.length <= 2 * 10^4,
// 1 <= start < end <= 10^5, 1 <= color <= 10^9, so a sum can pass the int range.
//
// The one idea: one entry of the map is a point where the set of colors changes, and its value is
// the change in the color sum there: +color where a segment starts, -color at end itself, not
// end + 1, because the stretch is half-open. Walking the points from left to right with a running
// sum gives the sum on the stretch from each point to the next one. A point whose changes cancel
// out keeps its entry with value 0, so the stretch still ends there.
//
// Time: O(n log n) -- 2n tree map updates, then one walk over at most 2n points.
// Space: O(n) -- the map and the output.

public final class DescribeThePainting {

	public static List<List<Long>> splitPainting(int[][] segments) {
		TreeMap<Integer, Long> map = painting(segments); // MARKS
		if (map.isEmpty()) { // EMPTY: no segments, so nothing is painted
			return new ArrayList<>();
		}
		long sum = 0L;
		long prev = map.firstKey();
		List<List<Long>> list = new ArrayList<>();
		for (long position : map.keySet()) { // WALK: the points in increasing order
			if (sum > 0) { // EMIT: 0 means nothing is painted between prev and position
				list.add(Arrays.asList(prev, position, sum));
			}
			prev = position;
			sum += map.get((int) position); // now: the sum on [position, next point)
		}
		return list; // RETURN
	}

	private static TreeMap<Integer, Long> painting(int[][] segments) {
		TreeMap<Integer, Long> map = new TreeMap<>();
		for (int[] segment : segments) {
			int start = segment[0];
			int end = segment[1];
			long color = segment[2]; // long: a sum of many colors can pass the int range
			map.put(start, map.getOrDefault(start, 0L) + color); // START: the color joins the mix
			map.put(end, map.getOrDefault(end, 0L) - color); // STOP: half-open, gone from end on
		}
		return map; // RETURN
	}

	public static void main(String[] args) {
		int[][] segments1 = { { 1, 4, 5 }, { 4, 7, 7 }, { 1, 7, 9 } };
		System.out.println(splitPainting(segments1)); // [[1, 4, 14], [4, 7, 16]]

		int[][] segments2 = { { 1, 7, 9 }, { 6, 8, 15 }, { 8, 10, 7 } };
		System.out.println(splitPainting(segments2)); // [[1, 6, 9], [6, 7, 24], [7, 8, 15], [8, 10, 7]]

		int[][] segments3 = { { 1, 4, 5 }, { 1, 4, 7 }, { 4, 7, 1 }, { 4, 7, 11 } };
		System.out.println(splitPainting(segments3)); // [[1, 4, 12], [4, 7, 12]]
	}
}
