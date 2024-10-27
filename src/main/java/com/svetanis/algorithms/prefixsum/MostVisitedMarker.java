package com.svetanis.algorithms.prefixsum;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.ArrayList;
import java.util.List;

import com.svetanis.java.base.Pair;

// Imagine there are n points along a straight trail, 
// while a runner run sprints of intervals between those point.
// The training plan is an array a[], 
// which implies the runner should run from point a[i] to point a[i+1].

// For example, given n = 10, a = [2, 4, 1, 2].
// The runner should run from point 2 to point 4,
// then turn back from point 4 to point 1,
// and then from point 1 to point 2.

// Find the point that visited the most by runner after he finished training, 
// i.e. in above example, point 2 is the most visited.
// If more than one point are visited the most, find the point with minimum index.

public final class MostVisitedMarker {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int mostVisited(int n, List<Integer> sprints) {
		List<Pair<Integer, Integer>> pairs = pairs(sprints);
		List<Integer> markers = rangeAddition(n, pairs);
		return mostVisited(markers);
	}

	private static int mostVisited(List<Integer> list) {
		int max = list.get(0);
		int ind = 0;
		for (int i = 1; i < list.size(); i++) {
			int current = list.get(i);
			if (current > max) {
				max = current;
				ind = i;
			}
		}
		return ind;
	}

	private static List<Integer> rangeAddition(int n, List<Pair<Integer, Integer>> pairs) {
		Integer[] a = new Integer[n + 1];
		fill(a, 0);
		for (Pair<Integer, Integer> pair : pairs) {
			int srt = min(pair.getLeft(), pair.getRight());
			int end = max(pair.getLeft(), pair.getRight());
			a[srt] += 1;
			if (end + 1 < n) {
				a[end + 1] -= 1;
			}
		}
		for (int i = 1; i < n; i++) {
			a[i] += a[i - 1];
		}
		return asList(a);
	}

	public static void main(String[] args) {
		System.out.println(mostVisited(5, asList(2, 4, 1, 3))); // 2
		System.out.println(mostVisited(10, asList(1, 5, 10, 3))); // 5
		System.out.println(mostVisited(5, asList(1, 5))); // 1
		System.out.println(mostVisited(9, asList(9, 7, 3, 1))); // 3
	}

	private static List<Pair<Integer, Integer>> pairs(List<Integer> sprints) {
		List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		for (int i = 1; i < sprints.size(); i++) {
			pairs.add(Pair.build(sprints.get(i - 1), sprints.get(i)));
		}
		return pairs;
	}
}