package com.svetanis.algorithms.intervals.overlap;


import java.util.Arrays;
import java.util.PriorityQueue;


// 1851. Minimum Interval to Include Each Query
//
// intervals[i] = { left, right } is the closed interval [left, right]; its size is
// right - left + 1, the number of whole numbers in it. For each query q, the answer is the
// size of the smallest interval with left <= q <= right, or -1 if no interval contains q.
// Returns the answers in the order of the queries. Sorts the caller's intervals in place.
//
// The queries are answered from smallest to largest, not in the order given, and each
// remembers its original position so its answer lands there. One heap entry is an interval
// that starts at or before the current query, stored as { size, right }, smallest size on
// top. For a query q, first every interval with left <= q not yet seen goes into the heap.
// Then, while the smallest one on top ends before q, it is thrown away: it cannot contain q,
// and since the later queries are larger, it cannot contain any of them either. After that the
// top, if any, starts at or before q and ends at or after q, so it contains q, and no smaller
// interval does. Intervals that ended but are not on top stay in the heap until they reach
// the top; they do no harm there because only the top is ever read.

public final class MinInterval {
	// Time Complexity: O(n log n + m log m), the two sorts; each interval enters and leaves the
	// heap at most once
	// Space Complexity: O(n + m), the heap and the sorted copy of the queries

	public static int[] minInterval(int[][] intervals, int[] queries) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // by left end
		int[][] sorted = sortQueries(queries); // { value, original position }, by value
		int[] result = new int[queries.length];
		Arrays.fill(result, -1); // stays -1 for a query no interval contains
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // smallest on top
		int index = 0;
		for (int[] qwi : sorted) {
			int query = qwi[0];
			// every interval starting at or before the query joins the heap
			while (index < intervals.length && intervals[index][0] <= query) {
				int start = intervals[index][0];
				int end = intervals[index][1];
				pq.offer(new int[] { end - start + 1, end });
				index++;
			}
			// ended before this query, so before every later query too
			while (!pq.isEmpty() && pq.peek()[1] < query) {
				pq.poll();
			}
			// the top starts at or before the query and ends at or after it
			if (!pq.isEmpty()) {
				result[qwi[1]] = pq.peek()[0];
			}
		}
		return result;
	}

	private static int[][] sortQueries(int[] queries) {
		int[][] matrix = new int[queries.length][2];
		for (int i = 0; i < queries.length; i++) {
			matrix[i] = new int[] { queries[i], i };
		}
		Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));
		return matrix;
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