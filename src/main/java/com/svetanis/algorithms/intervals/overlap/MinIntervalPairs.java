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
// The queries are answered from smallest to largest, not in the order given, and each
// remembers its original position so its answer lands there. One heap entry is an interval
// that starts at or before the current query, stored as { size, right }, smallest size on
// top. For a query, first every interval with left <= query not yet entered goes into the heap.
// Then, while the smallest one on top ends before the query, it is thrown away: it cannot
// contain this query, and since the later queries are larger, it cannot contain any of them
// either. After that the top, if any, starts at or before the query and ends at or after it,
// so it contains the query, and no smaller interval does. Intervals that ended but are not on
// top stay in the heap until they reach the top; they do no harm there because only the top
// is ever read.
//
// This version sorts { value, position } pairs. The sibling MinIntervalPositions sorts the
// positions 0 .. n - 1 by the query value at each; the walk is the same.

public final class MinIntervalPairs {
	// Time Complexity: O(n log n + m log m), the two sorts; each interval enters and leaves the
	// heap at most once
	// Space Complexity: O(n + m), the heap and the sorted pairs

	public static int[] minInterval(int[][] intervals, int[] queries) {
		int n = queries.length;
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // by start
		int[][] pairs = sortQueries(queries); // { value, original position }, by value
		int[] answer = new int[n];
		Arrays.fill(answer, -1); // stays -1 for a query no interval contains
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // { size, right }, smallest size on top
		int j = 0; // next interval not yet entered
		for (int[] pair : pairs) {
			int query = pair[0], position = pair[1];
			// ENTER: every interval starting at or before the query joins the heap
			while (j < intervals.length && intervals[j][0] <= query) {
				int start = intervals[j][0], end = intervals[j][1], size = end - start + 1;
				pq.offer(new int[] { size, end });
				j += 1;
			}
			// DROP: ended before this query, so before every later query too
			while (!pq.isEmpty() && pq.peek()[1] < query) {
				pq.poll();
			}
			// RECORD: the top starts at or before the query and ends at or after it
			if (!pq.isEmpty()) {
				answer[position] = pq.peek()[0];
			}
		}
		return answer;
	}

	private static int[][] sortQueries(int[] queries) {
		int n = queries.length;
		int[][] pairs = new int[n][2];
		for (int i = 0; i < n; i++) {
			pairs[i][0] = queries[i];
			pairs[i][1] = i;
		}
		Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[0], p2[0]));
		return pairs;
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
