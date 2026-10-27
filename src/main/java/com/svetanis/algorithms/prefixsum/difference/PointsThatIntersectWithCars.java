package com.svetanis.algorithms.prefixsum.difference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2848. Points That Intersect With Cars
//
// Input: nums[i] = [start, end] -- a car parked over every whole-number point from start to end,
// both ends included. Returns how many points have at least one car over them. On LeetCode:
// 1 <= nums.length <= 100, 1 <= start <= end <= 100. The mark array has 110 cells, so the code
// works for every point from 0 to 108, where end + 1 still fits.
//
// The one idea: one cell delta[p] holds the change in the number of cars over point p: +1 where a
// car begins, -1 at end + 1, because the point end itself is still under the car. Adding the cells
// from left to right gives the number of cars over each point; the answer counts the points where
// that number is above zero, so a point under two cars is still counted once.
//
// Time: O(n + 110) -- two marks per car, then one pass over the 110 cells.
// Space: O(110) -- the mark array, whatever the number of cars.

public final class PointsThatIntersectWithCars {

	public static int numberOfPoints(List<List<Integer>> nums) {
		int[] delta = new int[110]; // MARKS: one cell per point
		for (List<Integer> num : nums) {
			int start = num.get(0);
			int end = num.get(1);
			delta[start]++; // START
			delta[end + 1]--; // STOP: end is still covered; the car is gone one point later
		}
		int curr = 0;
		int total = 0;
		for (int count : delta) { // WALK
			curr += count; // cars over this point
			if (curr > 0) { // COUNT: at least one car, counted once
				total++;
			}
		}
		return total; // RETURN
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
