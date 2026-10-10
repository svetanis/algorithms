package com.svetanis.algorithms.twopointers.quadruple;

import java.util.HashMap;
import java.util.Map;

// Count quadruples with a given sum from four arrays, by hashing pair sums
//
// Input: four arrays of the same length n and a target. Neither sorting nor distinct values
// is needed here; repeats are counted once per choice of positions.
// Return: how many ways there are to take one number from each array so that the four
// sum to the target.
//
// The one idea: split the four into two pairs. Count every a1 + a2 sum in a map -- n^2 of
// them -- then for every a3 + a4 sum, the first pair must make target - sum, and the map
// says in one lookup how many pairs do.
//
// Siblings -- the same count:
//   twopointers.quadruple.CountQuadruplesGivenSumBinary      -- fixes three numbers, binary
//                                                              searches the fourth: O(n^3 log n)
//   twopointers.quadruple.CountQuadruplesGivenSumTwoPointers -- fixes two numbers and
//                                                              converges on a3 and a4: O(n^3)
//
// Time: O(n^2) -- n^2 sums counted, n^2 lookups.
// Space: O(n^2) for the map of sums.

public final class CountQuadruplesGivenSumHashing {

	public static int count(int[] a1, int[] a2, int[] a3, int[] a4, int target) {
		int n = a1.length;
		int count = 0;
		Map<Integer, Integer> map = sums(a1, a2);        // a1 + a2 sum -> how many pairs make it
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {                // FIX the pair from a3 and a4
				int sum = a3[i] + a4[j];
				count += map.getOrDefault(target - sum, 0); // COUNT every first pair that completes it
			}
		}
		return count;
	}

	private static Map<Integer, Integer> sums(int[] a1, int[] a2) {
		int n = a1.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = a1[i] + a2[j];
				map.merge(sum, 1, Integer::sum);         // COUNT the pair under its sum
			}
		}
		return map;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 5, 6 };
		int[] a2 = { 2, 3, 7, 8 };
		int[] a3 = { 1, 4, 6, 10 };
		int[] a4 = { 2, 4, 7, 8 };
		System.out.println(count(a1, a2, a3, a4, 30)); // 4
	}
}
