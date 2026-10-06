package com.svetanis.algorithms.intervals.overlap;


import java.util.Arrays;
import java.util.PriorityQueue;


// 1851. Minimum Interval to Include Each Query
//
// intervals[i] = { left, right } is the closed interval [left, right]; its size is
// right - left + 1, the number of whole numbers in it. For each query, the answer is the
// size of the smallest interval with left <= query <= right, or -1 if no interval contains it.
// Returns the answers in the order of the queries. Sorts the caller's intervals in place.
//
// Two orders do two jobs. Intervals sorted by start decide WHEN an interval enters the heap:
// one index j only moves forward, so each interval enters exactly once. The heap, ordered by
// size, decides WHICH interval is read. An interval must not enter before the walk reaches its
// start; a small interval that has not started yet would sit on top and be read as the answer.
//
// The queries are answered from smallest to largest. Throwing away an ended top is safe only
// because of that order: every later query is at least this one, so an interval ending before
// this query cannot contain a later one. Only the top is checked; an ended interval lower down
// is never read until it reaches the top, and then the same loop throws it out.
//
// This version sorts the positions 0 .. n - 1 by the query value at each, so positions[i] is
// where the i-th smallest query sits. The sibling MinIntervalPairs sorts { value, position }
// pairs instead; the walk is the same.

public final class MinIntervalPositions {
	// Time Complexity: O(n log n + m log m), the two sorts; each interval enters and leaves the
	// heap at most once
	// Space Complexity: O(n + m), the heap and the sorted positions

	public static int[] minInterval(int[][] intervals, int[] queries) {
		int n = queries.length;

		// SORT: intervals by start; query positions by their value
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		Integer[] positions = new Integer[n]; // Integer, not int: Arrays.sort with a
		for (int i = 0; i < n; i++) { // comparator only works on object arrays
			positions[i] = i;
		}
		Arrays.sort(positions, (i, j) -> Integer.compare(queries[i], queries[j]));

		// HEAP: { size, right }, smallest size on top
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

		int j = 0; // next interval not yet entered
		int[] answer = new int[n];
		for (int i = 0; i < n; i++) {
			int position = positions[i], query = queries[position];

			// ENTER: every interval that has started by this query
			while (j < intervals.length && intervals[j][0] <= query) {
				int start = intervals[j][0], end = intervals[j][1], size = end - start + 1;
				pq.offer(new int[] { size, end });
				j += 1;
			}

			// DROP: the top has ended, and so it ends before every later query too
			while (!pq.isEmpty() && pq.peek()[1] < query) { // [1] is the right end
				pq.poll();
			}

			// RECORD: into the query's original slot
			int minSize = pq.isEmpty() ? -1 : pq.peek()[0];
			answer[position] = minSize;
		}

		// RETURN
		return answer;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 4 }, { 2, 4 }, { 3, 6 }, { 4, 4 } };
		int[] queries1 = { 2, 3, 4, 5 };
		System.out.println(Arrays.toString(minInterval(intervals1, queries1))); // [3, 3, 1, 4]

		int[][] intervals2 = { { 2, 3 }, { 2, 5 }, { 1, 8 }, { 20, 25 } };
		int[] queries2 = { 2, 19, 5, 22 };
		System.out.println(Arrays.toString(minInterval(intervals2, queries2))); // [2, -1, 4, 6]
	}
}
