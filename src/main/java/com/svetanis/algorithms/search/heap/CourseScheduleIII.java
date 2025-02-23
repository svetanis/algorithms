package com.svetanis.algorithms.search.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 630. Course Schedule III

public final class CourseScheduleIII {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (c1, c2) -> c1[1] - c2[1]);
		PriorityQueue<Integer> pq = new PriorityQueue<>((d1, d2) -> d2 - d1);
		int time = 0;
		for (int[] course : courses) {
			int duration = course[0];
			int lastDay = course[1];
			pq.offer(duration);
			time += duration;
			while (time > lastDay) {
				time -= pq.poll();
			}
		}
		return pq.size();
	}

	public static void main(String args[]) {
		int[][] g1 = { { 100, 200 }, { 200, 1300 }, { 1000, 1250 }, { 2000, 3200 } };
		System.out.println(scheduleCourse(g1)); // 3
		int[][] g2 = { { 1, 2 } };
		System.out.println(scheduleCourse(g2)); // 1
		int[][] g3 = { { 3, 2 }, { 4, 3 } };
		System.out.println(scheduleCourse(g3)); // 0
	}
}
