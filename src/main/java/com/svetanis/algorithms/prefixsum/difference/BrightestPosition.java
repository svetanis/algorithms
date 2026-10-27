package com.svetanis.algorithms.prefixsum.difference;

import java.util.TreeMap;

// 2021. Brightest Position on Street
//
// Input: lights[i] = [position, range] -- a lamp that lights every point from position - range
// to position + range, both ends included. The brightness of a point is the number of lamps that
// light it. Returns the brightest point; when several points tie, the smallest of them. On
// LeetCode: 1 <= lights.length <= 10^5, -10^8 <= position <= 10^8, 0 <= range <= 10^8, so
// position + range + 1 fits in an int. With no lights it throws: no point has a brightness, and
// LeetCode always gives at least one lamp.
//
// The one idea: one entry of the map is a point where the brightness changes, and its value is
// by how much: +1 where a lamp's stretch begins, -1 at right end + 1, because the right end
// itself is still lit. Between two neighbouring points of the map the brightness does not change,
// so the leftmost brightest point is always one of the map's points. Walking them from left to
// right with a running sum gives the brightness at each one; keeping only a strictly larger sum
// keeps the smallest point on a tie.
//
// Time: O(n log n) -- 2n tree map updates, then one walk over at most 2n points.
// Space: O(n) -- the map.

public final class BrightestPosition {

	private static final String NO_LIGHTS = "no lights, so no position has a brightness";

	public static int brightestPosition(int[][] lights) {
		if (lights.length == 0) { // GUARD: no lamp, no brightest point
			throw new IllegalArgumentException(NO_LIGHTS);
		}
		// key: a point where the brightness changes; value: by how much
		TreeMap<Integer, Integer> map = new TreeMap<>(); // MARKS
		for (int[] light : lights) {
			int center = light[0];
			int range = light[1];
			int left = center - range;
			int right = center + range + 1; // first dark point: center + range is still lit
			map.merge(left, 1, Integer::sum); // START: one more lamp from left on
			map.merge(right, -1, Integer::sum); // STOP: one lamp fewer from right on
		}
		int max = 0;
		int curr = 0;
		int point = 0;
		for (int position : map.keySet()) { // WALK: the points in increasing order
			curr += map.get(position); // brightness from this point up to the next one
			if (curr > max) { // BEST: strict, so a tie further right does not replace it
				max = curr;
				point = position;
			}
		}
		return point; // RETURN
	}

	public static void main(String[] args) {
		int[][] lights = { { 5, 3 }, { 7, 2 } };
		System.out.println(brightestPosition(lights)); // 5
	}
}
