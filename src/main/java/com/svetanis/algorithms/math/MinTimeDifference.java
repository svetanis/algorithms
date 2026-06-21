package com.svetanis.algorithms.math;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 539. Minimum Time Difference

public final class MinTimeDifference {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int minDiffSimple(List<String> points) {
		int n = points.size();
		int[] minutes = new int[n];
		for (int i = 0; i < n; i++) {
			String time = points.get(i);
			int hour = Integer.parseInt(time.substring(0, 2));
			int minute = Integer.parseInt(time.substring(3));
			minutes[i] = hour * 60 + minute;
		}
		Arrays.sort(minutes);
		int min = 24 * 60;
		for (int i = 0; i < minutes.length - 1; i++) {
			min = Math.min(min, minutes[i + 1] - minutes[i]);
		}
		int last = 24 * 60 - minutes[n - 1] + minutes[0];
		return Math.min(min, last);
	}

	public static int minDiff(List<String> points) {
		List<Integer> minutes = points.stream()
				.map(t -> minutes(t))
				.sorted().toList();
		List<Integer> list = new ArrayList<>();
		list.addAll(minutes);
		list.add(minutes.get(0) + 24 * 60);
		int min = 24 * 60;
		for (int i = 1; i < list.size(); i++) {
			int diff = list.get(i) - list.get(i - 1);
			min = Math.min(min, diff);
		}
		return min;
	}

	private static int minutes(String s) {
		int hours = Integer.parseInt(s.substring(0, 2));
		int minutes = Integer.parseInt(s.substring(3));
		return hours * 60 + minutes;
	}

	public static void main(String[] args) {
		System.out.println(minDiff(asList("23:59", "00:00"))); // 1
		System.out.println(minDiff(asList("00:00", "23:59", "00:00"))); // 0

		System.out.println(minDiffSimple(asList("23:59", "00:00"))); // 1
		System.out.println(minDiffSimple(asList("00:00", "23:59", "00:00"))); // 0
	}
}