package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.List;

// Pair With the Largest Sum From Two Arrays
//
// Input: two non-empty lists of distinct positive integers.
// Return: {x, y}, x from list1 and y from list2, with the largest sum x + y.
//
// The one idea: the two numbers come from different lists, so neither choice limits
// the other: take the largest of each list.
//
// Sibling: PairLargestSum -- both numbers from one array, at two different positions
//
// Time: O(n + m) -- one pass over each list.
// Space: O(1).

public final class PairMaxSumTwoArrays {

	public static int[] maxPair(List<Integer> list1, List<Integer> list2) {
		int max1 = max(list1); // the largest of list1
		int max2 = max(list2); // the largest of list2
		return new int[] { max1, max2 };
	}

	// the largest number of the list
	private static int max(List<Integer> list) {
		int max = Integer.MIN_VALUE;
		for (int x : list) {
			max = Math.max(max, x);
		}
		return max;
	}

	public static void main(String[] args) {
		List<Integer> list1 = List.of(10, 2, 3);
		List<Integer> list2 = List.of(3, 4, 7);
		System.out.println(Arrays.toString(maxPair(list1, list2))); // [10, 7]
	}
}
