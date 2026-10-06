package com.svetanis.algorithms.intervals.overlap;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// 850. Rectangle Area II
//
// Each rectangle is { x1, y1, x2, y2 }, bottom-left corner (x1, y1) and top-right corner
// (x2, y2). Returns the total area covered by at least one rectangle, a spot covered by
// several rectangles counting once, modulo 10^9 + 7. At most 200 rectangles, coordinates up to
// 10^9.
//
// Every x1 and every x2 is a boundary, because the covered height can only change where some
// rectangle begins or ends along x. Neighbouring boundaries bound a strip, and inside one strip
// the covered height is the same all the way across, so the strip adds width * covered height.
// A rectangle covers a strip when x1 <= left and x2 >= right; since every x1 and x2 is a
// boundary, a rectangle always covers whole strips, never part of one.
//
// The covered height of a strip is 56 Merge Intervals on the y-stretches { y1, y2 } of the
// rectangles covering it, adding up the merged lengths instead of keeping the merged list. An
// empty strip has height 0. No overlap is ever subtracted: strips share only a boundary line,
// so no area is counted twice along x, and the merge counts overlapping stretches once along y.
//
// Example, [[0,0,2,2], [1,0,2,3], [1,0,3,1]]: boundaries 0 1 2 3, strip heights 2 3 1, area 6.
//
// This version re-checks every rectangle for every strip, which the 200-rectangle limit allows.
// The sibling RectangleAreaII in data-structures (todo/tree/segment) opens and closes the
// rectangles as sorted events and keeps the covered height in a segment tree, for when there
// are too many rectangles to re-check.

public final class RectangleAreaIIStrips {
	// Time Complexity: O(n^2 log n), at most 2n - 1 strips, each checking n rectangles and
	// sorting at most n stretches
	// Space Complexity: O(n) for the boundaries and one strip's stretches

	private static final int MOD = 1_000_000_007;

	public static int rectangleArea(int[][] rectangles) {
		// XS: every x1 and x2, sorted -- neighbours bound a strip
		List<Integer> xs = xcoordinates(rectangles);
		Collections.sort(xs);

		// STRIPS: width * covered height, over the gap between neighbouring boundaries
		long area = 0;
		for (int i = 1; i < xs.size(); i++) {
			int left = xs.get(i - 1), right = xs.get(i);
			List<int[]> stretches = stretches(left, right, rectangles); // OPEN
			int width = right - left;
			long height = coveredHeight(stretches); // HEIGHT
			area = (area + width * height) % MOD; // AREA: long, 10^9 * 10^9 overflows int
		}

		// RETURN
		return (int) (area % MOD);
	}

	private static List<Integer> xcoordinates(int[][] rectangles) {
		Set<Integer> set = new HashSet<>();
		for (int[] rectangle : rectangles) {
			set.add(rectangle[0]); // where something begins
			set.add(rectangle[2]); // where something ends -- a boundary too
		}
		return new ArrayList<>(set);
	}

	private static List<int[]> stretches(int left, int right, int[][] rectangles) {
		List<int[]> stretches = new ArrayList<>();
		for (int[] rectangle : rectangles) {
			int x1 = rectangle[0], y1 = rectangle[1];
			int x2 = rectangle[2], y2 = rectangle[3];
			if (x1 <= left && x2 >= right) { // covers the whole strip
				stretches.add(new int[] { y1, y2 });
			}
		}
		return stretches;
	}

	private static long coveredHeight(List<int[]> stretches) {
		if (stretches.isEmpty()) { // a strip nothing covers
			return 0;
		}
		stretches.sort((a, b) -> Integer.compare(a[0], b[0])); // SORT by y1
		int start = stretches.get(0)[0]; // HOLD the merged stretch so far
		int end = stretches.get(0)[1];
		long total = 0;
		for (int i = 1; i < stretches.size(); i++) {
			int currStart = stretches.get(i)[0], currEnd = stretches.get(i)[1];
			if (end < currStart) { // TEST: apart
				total += end - start; // CLOSE: count its length
				start = currStart;
				end = currEnd;
			} else {
				end = Math.max(end, currEnd); // EXTEND
			}
		}
		total += end - start; // LAST
		return total;
	}

	public static void main(String[] args) {
		int[][] rectangles1 = { { 0, 0, 2, 2 }, { 1, 0, 2, 3 }, { 1, 0, 3, 1 } };
		System.out.println(rectangleArea(rectangles1)); // 6

		int[][] rectangles2 = { { 0, 0, 1000000000, 1000000000 } };
		System.out.println(rectangleArea(rectangles2)); // 49

		int[][] rectangles3 = { { 0, 0, 1, 1 }, { 2, 0, 3, 1 } };
		System.out.println(rectangleArea(rectangles3)); // 2, the strip from 1 to 2 is empty
	}
}
