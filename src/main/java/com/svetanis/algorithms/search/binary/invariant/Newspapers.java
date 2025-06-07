package com.svetanis.algorithms.search.binary.invariant;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

public final class Newspapers {
	// Time Complexity: O(n log m)
	// Space Complexity: O(1)

	public static int newspapers(List<Integer> times, int n) {
		int left = Collections.max(times);
		int right = times.stream().reduce(0, Integer::sum);
		int count = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (feasible(times, n, mid)) {
				count = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return count;
	}

	private static boolean feasible(List<Integer> times, int n, int limit) {
		int time = 0;
		int workers = 0;
		for (int readTime : times) {
			if (time + readTime > limit) {
				time = 0;
				workers++;
			}
			time += readTime;
		}
		if (time != 0) {
			workers++;
		}
		return workers <= n;
	}

	public static void main(String[] args) {
		System.out.println(newspapers(asList(7, 2, 5, 10, 8), 2)); // 18
		System.out.println(newspapers(asList(1, 4, 4), 3)); // 4
		System.out.println(newspapers(asList(1, 1, 1, 1, 1, 1), 6)); // 1
		System.out.println(newspapers(asList(15, 15, 15, 15), 4)); // 15
	}
}
