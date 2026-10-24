package com.svetanis.algorithms.greedy;

// 1785. Minimum Elements to Add to Form a Given Sum
//
// Input: a -- an array in which every element's absolute value is at most limit; goal -- the sum
// wanted. Returns the fewest elements to add, each also of absolute value at most limit, so that
// the array sums to goal. On LeetCode: 1 <= a.length <= 10^5, 1 <= limit <= 10^6, -limit <= a[i]
// <= limit, -10^9 <= goal <= 10^9. The sum can reach 10^11, so it is a long; the answer is at most
// about 10^9 + 10^5, which fits in an int.
//
// The one idea: the gap to close is the distance between goal and the current sum, diff =
// |goal - sum|. One added element closes at most limit of the gap, so k elements close at most
// k * limit, and fewer than diff / limit elements, rounded up, cannot be enough. That many is
// always enough: add elements of size limit, pointing towards goal, and make the last one exactly
// the size of what is left, which is at most limit. (diff + limit - 1) / limit is diff / limit
// rounded up, in whole-number arithmetic.
//
// Time: O(n) -- one pass to sum.
// Space: O(1) -- the sum and the gap.

public final class MinElementToAddToGivenSum {

	public static int minElements(int[] a, int limit, int goal) {
		long sum = 0; // a long: up to 10^5 elements of up to 10^6
		for (int num : a) {
			sum += num;
		}
		long diff = Math.abs(goal - sum); // GAP to close, either direction
		return (int) ((diff + limit - 1) / limit); // RETURN: full-size elements, rounded up
	}

	public static void main(String[] args) {
		int[] a1 = { 1, -1, 1 };
		System.out.println(minElements(a1, 3, -4)); // 2

		int[] a2 = { 1, -10, 9, 1 };
		System.out.println(minElements(a2, 100, 0)); // 1
	}
}
