package com.svetanis.algorithms.prefixsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2848. Points That Intersect With Cars

public final class PointsThatIntersectWithCars {
	// Time complexity: O(n)

	public static int numberOfPoints(List<List<Integer>> nums) {
		int[] delta = new int[110];
		for (List<Integer> num : nums) {
			int start = num.get(0);
			int end = num.get(1);
			delta[start]++;
			delta[end + 1]--;
		}
		int curr = 0;
		int total = 0;
		for (int count : delta) {
			curr += count;
			if (curr > 0) {
				total++;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists1 = new ArrayList<>();
		lists1.add(Arrays.asList(3, 6));
		lists1.add(Arrays.asList(1, 5));
		lists1.add(Arrays.asList(4, 7));
		System.out.println(numberOfPoints(lists1)); // 7

		List<List<Integer>> lists2 = new ArrayList<>();
		lists2.add(Arrays.asList(1, 3));
		lists2.add(Arrays.asList(5, 8));
		System.out.println(numberOfPoints(lists2)); // 7
	}
}
