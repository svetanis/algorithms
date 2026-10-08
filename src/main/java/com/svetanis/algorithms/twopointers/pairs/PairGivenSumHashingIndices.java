package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1. Two Sum
//
// Input: an array of integers and a target. Exactly one pair of numbers at two
// different positions adds up to the target.
// Return: the two positions {i, j}, i < j.
// The List overload does the same on a List<Integer>; where no pair exists it returns
// {-1, -1}, and the array version throws.
//
// The one idea: walk left to right with a map from each value seen to its position.
// The only partner of a[i] is target - a[i]; if the map holds it, its position and i
// are the answer. Looking up BEFORE storing a[i] keeps a number from pairing with
// itself, so {3, 3} with target 6 finds positions 0 and 1.
//
// Siblings:
//   twopointers.TwoSumSorted167 -- sorted input, converging pointers, O(1) space,
//     positions counted from 1
//   PairGivenSumHashing -- the same pass with a set, returns the values
//   PairGivenSumStream (LC 170) -- the numbers arrive one at a time
//
// Time: O(n) -- one lookup per number.
// Space: O(n) -- the map.

public final class PairGivenSumHashingIndices {

	public static int[] pair(int[] a, int target) {
		Map<Integer, Integer> map = new HashMap<>();   // value -> its position
		for (int i = 0; i < a.length; i++) {
			int diff = target - a[i];                  // the only partner a[i] can have
			if (map.containsKey(diff)) {
				return new int[] { map.get(diff), i }; // FOUND: the partner came earlier
			}
			map.put(a[i], i);                          // SEE a[i] for the numbers after it
		}
		throw new IllegalArgumentException("No such pair found");
	}

	public static int[] pair(List<Integer> list, int target) {
		int n = list.size();
		Map<Integer, Integer> map = new HashMap<>();   // value -> its position
		for (int i = 0; i < n; i++) {
			int diff = target - list.get(i);           // the only partner list.get(i) can have
			if (map.containsKey(diff)) {
				return new int[] { map.get(diff), i }; // FOUND: the partner came earlier
			}
			map.put(list.get(i), i);                   // SEE list.get(i) for the numbers after it
		}
		return new int[] { -1, -1 };                   // RETURN: no pair
	}

	public static void main(String[] args) {
		List<Integer> list = List.of(1, 4, 45, 6, 10, -8);
		System.out.println(Arrays.toString(pair(list, 16))); // [3, 4]

		int[] a1 = { 2, 7, 11, 15 };
		System.out.println(Arrays.toString(pair(a1, 9))); // [0, 1]

		int[] a2 = { 3, 2, 4 };
		System.out.println(Arrays.toString(pair(a2, 6))); // [1, 2]

		int[] a3 = { 3, 3 };
		System.out.println(Arrays.toString(pair(a3, 6))); // [0, 1]
	}
}
