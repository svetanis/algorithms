package com.svetanis.algorithms.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.svetanis.java.base.utils.Print;

// 1851. Minimum Interval to Include Each Query

public final class MinInterval {
	// Time Complexity: O(n*log n + m log m)
	// Space Complexity: O(n + m)

	public static int[] minInterval(int[][] intervals, int[] queries) {
		// sort intervals by start point
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		// pair query with its index and sort by value
		int[][] sorted = sortQueries(queries);
		int[] result = new int[queries.length];
		Arrays.fill(result, -1);
		// min heap to store intervals with smallest size on top
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		int index = 0;
		for (int[] qwi : sorted) {
			int query = qwi[0];
			// add intervals to pq where start is <= query value
			while (index < intervals.length && intervals[index][0] <= query) {
				int start = intervals[index][0];
				int end = intervals[index][1];
				pq.offer(new int[] { end - start + 1, end });
				index++;
			}
			// remove intervals from pq which end before the query value
			while (!pq.isEmpty() && pq.peek()[1] < query) {
				pq.poll();
			}
			// if pq not empty, there is an interval covering the query value
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
		Arrays.sort(matrix, (a, b) -> a[0] - b[0]);
		return matrix;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 4 }, { 2, 4 }, { 3, 6 }, { 4, 4 } };
		int[] queries1 = { 2, 3, 4, 5 };
		Print.print(minInterval(intervals1, queries1)); // 3,3,1,4

		int[][] intervals2 = { { 2, 3 }, { 2, 5 }, { 1, 8 }, { 20, 25 } };
		int[] queries2 = { 2, 19, 5, 22 };
		Print.print(minInterval(intervals2, queries2)); // 2,-1,4,6
	}
}