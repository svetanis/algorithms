package com.svetanis.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;

// 1005. Maximize Sum Of Array After K Negations
//
// Input: nums -- an array of whole numbers; k -- how many times to negate. One negation picks an
// index i and replaces nums[i] with -nums[i]; exactly k negations must be made, and the same index
// may be picked again. Returns the largest possible sum. On LeetCode: 1 <= nums.length <= 10^4,
// -100 <= nums[i] <= 100, 1 <= k <= 10^4.
//
// The one idea: negating a negative number -x adds 2x to the sum, so the negations go to the
// negative numbers first, the most negative first, because that adds the most per negation. The
// map counts how many times each value appears, and the first loop walks the values from -100 up
// to -1, turning as many copies as k allows into their positive values. A negation spent anywhere
// else would add less or take away, so these are never undone. If negations are left once no
// negative number remains, two negations on the same number cancel, so only whether the count left
// is odd matters. An odd one out must land somewhere: on a 0 it changes nothing; with no 0 it goes
// on the smallest positive number, which costs the least. The second loop finds that number among
// values 1 to 100 AFTER the first loop, so a number that was just turned positive is a candidate
// too.
//
// Time: O(n) -- counting is one pass over nums; the two value loops run at most 100 times each, and
// the final sum reads at most 201 distinct values.
// Space: O(1) -- the map holds at most 201 distinct values, -100 to 100.

public final class MaximizeSumAfterKNegations {

	public static int largestSumAfterKNegations(int[] nums, int k) {
		Map<Integer, Integer> map = frequency(nums); // COUNT each value
		for (int num = -100; num < 0 && k > 0; num++) { // WALK: most negative first
			if (map.getOrDefault(num, 0) > 0) {
				int flips = Math.min(map.get(num), k); // TAKE as many copies as k allows
				map.merge(num, -flips, Integer::sum);
				map.merge(-num, flips, Integer::sum); // they reappear as positives
				k -= flips;
			}
		}
		if (k % 2 != 0 && map.getOrDefault(0, 0) == 0) { // ODD one left and no 0 to absorb it
			for (int num = 1; num <= 100; num++) { // FIND the smallest positive value
				if (map.getOrDefault(num, 0) > 0) {
					map.merge(num, -1, Integer::sum);
					map.merge(-num, 1, Integer::sum); // NEGATE one copy of it
					break;
				}
			}
		}
		int totalSum = 0;
		for (int num : map.keySet()) {
			totalSum += num * map.get(num); // SUM: value times its count
		}
		return totalSum; // RETURN: the largest sum
	}

	private static Map<Integer, Integer> frequency(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.merge(num, 1, Integer::sum); // COUNT one more copy of num
		}
		return map;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 2, 3 };
		System.out.println(largestSumAfterKNegations(a1, 1)); // 5

		int[] a2 = { 3, -1, 0, 2 };
		System.out.println(largestSumAfterKNegations(a2, 3)); // 6

		int[] a3 = { 2, -3, -1, 5, -4 };
		System.out.println(largestSumAfterKNegations(a3, 2)); // 13
	}
}
