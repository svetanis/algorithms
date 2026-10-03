package com.svetanis.algorithms.intervals.overlap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 218. The Skyline Problem
//
// Takes buildings as [left, right, height], height >= 1, in any order; each building stands on
// [left, right): at x = right it is gone. Returns the outline seen from far away as key points
// [x, h]: from x onward the outline has height h, up to the next key point. The last key point has
// height 0, and no two key points in a row have the same height. Sorts the caller's array in place.
//
// A key point is written exactly where the tallest standing height changes. The outline can only
// change at an edge, so the walk stops at every left and every right edge, in order -- a right edge
// is where the outline can drop. The standing buildings are kept on a max-heap by height. A
// building that ends is not always on top, and a heap only removes its top cheaply -- but an ended
// building lower down is never read, so it does no harm until it reaches the top. So the heap holds
// the WHOLE building, which carries its right edge, and at every stop the ended buildings on top
// are thrown away before the top is read.
//
// At each stop: put on the heap every building that begins here (added counts the buildings
// already on it, so the next one waiting is buildings[added]); throw away from the top every
// building whose right edge is <= x; then read the top, and write [x, height] only if the height
// changed. The read comes after both, so a building ending where another begins at the same height
// writes nothing between them.
//
// The same problem three more ways, in todo/interval: SkylineSweepEvents sweeps 2n events with a
// heap of bare heights and a count of retired ones; SkylinePaint raises every stretch between two
// edges a building covers, O(n * k); SkylineDivideAndConquer merges two halves' outlines the way
// merge sort merges two sorted lists.

public final class SkylineSweepBuildings {
	// Time Complexity: O(n log n), sorting the buildings and the 2n stops; each building is offered
	// once and polled at most once, O(log n) each
	// Space Complexity: O(n), the stops and the heap

	public static List<List<Integer>> skyline(int[][] buildings) {
		Arrays.sort(buildings, (a, b) -> Integer.compare(a[0], b[0])); // PUT ON needs left-edge order
		List<Integer> stops = new ArrayList<>(); // every left AND right edge
		for (int[] building : buildings) {
			stops.add(building[0]);
			stops.add(building[1]);
		}
		Collections.sort(stops);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2])); // tallest on top
		int added = 0; // buildings already put on the heap
		int lastHeight = 0; // height of the last key point written; the ground before the first
		List<List<Integer>> answer = new ArrayList<>();
		for (int x : stops) {
			while (added < buildings.length && buildings[added][0] <= x) { // PUT ON: begins at this stop
				pq.offer(buildings[added]);
				added += 1;
			}
			while (!pq.isEmpty() && pq.peek()[1] <= x) { // THROW AWAY: '<=', gone at its right edge
				pq.poll(); // top only; ended ones lower down wait
			}
			int height = pq.isEmpty() ? 0 : pq.peek()[2]; // READ: after both, 0 when nothing stands
			if (height != lastHeight) { // WRITE: '!=', the outline goes down as well as up
				lastHeight = height;
				answer.add(Arrays.asList(x, height));
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] b1 = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
		System.out.println(skyline(b1)); // [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]

		int[][] b2 = { { 0, 2, 3 }, { 2, 5, 3 } };
		System.out.println(skyline(b2)); // [[0, 3], [5, 0]]: touching at 2, same height, no key point

		int[][] b3 = { { 5, 10, 3 }, { 1, 3, 2 } };
		System.out.println(skyline(b3)); // [[1, 2], [3, 0], [5, 3], [10, 0]]: not sorted, so sorted first
	}
}
