package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 349. Intersection of Two Arrays
//
// Input: two int arrays a1 and a2, unsorted, repeats allowed. On LeetCode: 1 <= length <= 1000,
// 0 <= value <= 1000. Returns the values that are in both, each once, in any order (here: the
// order of their first appearance in a2).
//
// The one idea: the values are small, 0 to 1000, so a table of 1001 flags can say, for each
// value, "this value is in a1". Mark every value of a1. Then read a2: a number whose flag is set
// is in both arrays; take it and clear its flag, so a second copy in a2 is not taken again.
//
// Time: O(n + m) -- one pass over each array.
// Space: O(1) -- the table is 1001 flags whatever the input; the returned array is the output.
//
// Siblings: IntersectionTwoArraysII (350) -- the same question with repeats counted.

public final class IntersectionUnsortedHashing {

	public static int[] intersection(int[] a1, int[] a2) {
		boolean[] visited = new boolean[1001]; // visited[v]: v is in a1 and not yet taken
		for (int num : a1) { // MARK every value of a1
			visited[num] = true;
		}
		List<Integer> list = new ArrayList<>();
		for (int num : a2) {
			if (visited[num]) { // TAKE: in both arrays, first time seen in a2
				list.add(num);
				visited[num] = false; // CLEAR: each value is taken once
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 1 };
		int[] a2 = { 2, 2 };
		System.out.println(Arrays.toString(intersection(a1, a2))); // [2]

		int[] a3 = { 4, 9, 5 };
		int[] a4 = { 9, 4, 9, 8, 4 };
		System.out.println(Arrays.toString(intersection(a3, a4))); // [9, 4]
	}
}