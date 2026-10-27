package com.svetanis.algorithms.prefixsum.difference;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// Point on a straight trail that a runner passes most often during a list of sprints.
//
// Not a LeetCode problem: an online-assessment exercise, and no judge has run this file. Its
// original limits were not kept; the code assumes n >= 1 and every sprints[i] in 1 .. n.
//
// Input: n -- points 1 to n lie along a straight trail; sprints -- the training plan, a list of
// points in order: the runner runs from sprints[i] to sprints[i + 1], in either direction. Every
// point from the smaller to the larger of the two, both included, counts as visited once in that
// sprint, so a point where the runner turns is counted once for the sprint that ends there and
// once for the sprint that starts there. Returns the most visited point; on a tie, the smallest
// one; 0 when the plan has fewer than two points, so there is no sprint and no point is visited.
// For example, n = 10 and sprints = [2, 4, 1, 2] run 2 to 4, back from 4 to 1, then 1 to 2:
// point 2 is visited three times, more than any other point.
//
// The one idea: one cell a[p] holds the change in the visit count at point p: +1 where a sprint's
// stretch begins, -1 at its larger end + 1, because that end is itself visited. Adding the cells
// from left to right gives each point's visit count; the first strictly larger count wins, which
// keeps the smallest point on a tie.
//
// Time: O(n + m) -- two marks per sprint (m points in the plan), then one pass over the n points.
// Space: O(n) -- the mark array.
//
// Siblings: todo.assessment.KumatsuAssesment holds the same marks in a list of boxed Integers
// built from java-base Pairs.

public final class MostVisitedMarker {

	public static int mostVisited(int n, List<Integer> sprints) {
		int[] visits = rangeAddition(n, sprints); // MARKS and WALK
		return mostVisited(visits); // BEST
	}

	private static int mostVisited(int[] visits) {
		int max = visits[0]; // point 0 does not exist; its count stays 0
		int ind = 0;
		for (int i = 1; i < visits.length; i++) { // WALK: points 1 to n
			int current = visits[i];
			if (current > max) { // BEST: strict, on a tie the smaller point stays
				max = current;
				ind = i;
			}
		}
		return ind; // RETURN
	}

	private static int[] rangeAddition(int n, List<Integer> sprints) {
		int[] a = new int[n + 1]; // MARKS: one pair per sprint
		for (int i = 1; i < sprints.size(); i++) {
			// a sprint down the trail covers the same points as one up it
			int srt = min(sprints.get(i - 1), sprints.get(i));
			int end = max(sprints.get(i - 1), sprints.get(i));
			a[srt] += 1; // START
			if (end + 1 <= n) { // STOP: a sprint ending at point n needs no -1: nothing lies past it
				a[end + 1] -= 1;
			}
		}
		for (int i = 1; i <= n; i++) { // WALK: points run 1 to n, so point n is summed too
			a[i] += a[i - 1];
		}
		return a; // RETURN: a[p] is the visit count of point p
	}

	public static void main(String[] args) {
		System.out.println(mostVisited(5, asList(2, 4, 1, 3))); // 2
		System.out.println(mostVisited(10, asList(1, 5, 10, 3))); // 5
		System.out.println(mostVisited(5, asList(1, 5))); // 1
		System.out.println(mostVisited(9, asList(9, 7, 3, 1))); // 3
	}
}
