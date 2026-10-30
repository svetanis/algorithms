package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

import java.util.Arrays;
import java.util.PriorityQueue;

// 630. Course Schedule III
//
// courses[i] = [duration, lastDay]: a course takes duration days in a row and must finish on or
// before lastDay. Starting on day 1, take courses one at a time, never two at once. Returns the
// largest number of courses that can be taken. Sorts the input array in place.
//
// Any set of courses that can be taken at all can be taken in deadline order, so walk the courses
// by lastDay and keep the set taken so far. Take each new course; if the running time now passes
// its deadline, drop the LONGEST course in the set -- the count goes back to what it was, and the
// time falls by the most it can, which leaves the most room for later courses. A max-heap of
// durations names that longest course. The dropped one may be the new course itself.
//
// Time: O(n log n) -- the sort, then each course is pushed once and polled at most once
// Space: O(n) for the heap
//
// Siblings, same move (take now, give back the worst when a limit breaks):
//   FurthestBuilding (1642)            -- the heap holds the climbs given ladders
//   greedy/MinNumOfRefuelingStops (871) -- the mirror: pass now, take the best one back later

public final class CourseScheduleIII {

	public static int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, comparingInt(c -> c[1]));   // ORDER: earliest deadline first
		PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder()); // MAX-HEAP: longest taken course on top
		int time = 0;
		for (int[] course : courses) {
			int duration = course[0];
			int lastDay = course[1];
			pq.offer(duration);                          // TAKE: take it, then repair if it does not fit
			time += duration;
			while (time > lastDay) {
				time -= pq.poll();                       // EVICT: drop the longest course taken so far
			}
		}
		return pq.size();                                // REPORT: the courses still taken
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
