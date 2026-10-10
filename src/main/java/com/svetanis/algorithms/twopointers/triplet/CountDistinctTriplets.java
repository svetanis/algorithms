package com.svetanis.algorithms.twopointers.triplet;

import java.util.HashMap;
import java.util.Map;

// 2475. Number of Unequal Triplets in Array
//
// Input: an array of integers.
// Return: how many triples of positions i < j < k hold three pairwise different values.
//
// The one idea: only the GROUPS of equal values matter, not where they sit. Take the groups
// one at a time, in any order. For the current group of size freq, prev numbers sit in
// groups already taken and next = n - prev - freq in groups still to come. A triple of three
// different values is one number from each of three groups, and it is counted exactly once
// -- when its middle group, in this order, is the current one -- as prev * freq * next.
//
// Time: O(n) -- one pass to count, one pass over the groups.
// Space: O(n) for the counts.

public final class CountDistinctTriplets {

	public static int unequalTriplets(int[] nums) {
		int n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();     // value -> size of its group
		for (int num : nums) {
			map.merge(num, 1, Integer::sum);             // COUNT each value
		}
		int prev = 0;                                    // numbers in the groups already taken
		int result = 0;
		for (int freq : map.values()) {                  // FIX the middle group
			int next = n - prev - freq;                  // numbers in the groups still to come
			result += next * freq * prev;                // COUNT: one from each side, one from here
			prev += freq;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 4, 2, 4, 3 };
		System.out.println(unequalTriplets(a1)); // 3

		int[] a2 = { 1, 1, 1, 1, 1 };
		System.out.println(unequalTriplets(a2)); // 0
	}
}
