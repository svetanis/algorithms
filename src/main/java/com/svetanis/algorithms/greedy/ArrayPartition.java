package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 561. Array Partition
//
// Input: a -- an array of 2n integers. Split it into n pairs; each pair scores the smaller of its
// two numbers. Returns the largest possible total score. On LeetCode: 1 <= n <= 10^4,
// a.length == 2 * n, -10^4 <= a[i] <= 10^4.
//
// The one idea: the smallest number scores in whatever pair it lands in, because nothing in its
// pair is smaller, and its partner never scores. So the partner is wasted either way, and the
// cheapest number to waste is the second-smallest: any bigger partner is a number that could have
// scored in a later pair. Pair the two smallest, then the next two, and so on. After sorting those
// pairs sit side by side, and the score of each is its first number: a[0], a[2], a[4], ...
//
// Time: O(n log n) -- the sort; the sum is one pass over every other element.
// Space: O(1) -- beyond the sort, which works in place: a comes back sorted.

public final class ArrayPartition {

	public static int pairSum(int[] a) {
		Arrays.sort(a); // SORT: neighbours in sorted order become the pairs
		int sum = 0;
		for (int i = 0; i < a.length; i += 2) { // WALK: one step per pair
			sum += a[i]; // TAKE the smaller of the pair (a[i], a[i + 1])
		}
		return sum; // RETURN: at most 10^4 * 10^4 = 10^8 in size, fits an int
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 3, 2 };
		System.out.println(pairSum(a1)); // 4

		int[] a2 = { 6, 2, 6, 5, 1, 2 };
		System.out.println(pairSum(a2)); // 9
	}
}
