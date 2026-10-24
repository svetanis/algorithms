package com.svetanis.algorithms.greedy;

// 2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
//
// Input: a1, a2 -- two arrays of numbers that are 0 or more. Every 0 in both arrays must be
// replaced by a number of at least 1, so that the two arrays end up with the same sum. Returns the
// smallest such equal sum, or -1 if it cannot be done. On LeetCode: 1 <= a1.length, a2.length <=
// 10^5, 0 <= a1[i], a2[i] <= 10^6. A sum can reach 10^11, so the sums are longs.
//
// The one idea: a 0 must become at least 1, so the smallest sum an array can have counts each of
// its zeros as 1 -- that is what Math.max(num, 1) adds. An array with at least one 0 can reach ANY
// sum at or above that smallest sum, by raising one of its replaced zeros as far as needed. An array
// with no 0 is stuck at exactly its own sum. So: if the two smallest sums are equal, that is the
// answer. Otherwise the array with the smaller smallest sum must grow to meet the other one, which
// works only if it has a 0, and the answer is then the larger smallest sum -- no equal sum can be
// below it, since neither array can go under its own smallest. hasZero is tracked for a1 only;
// when a1's smallest sum is the larger, the method calls itself with the arrays swapped, so a1 is
// always the array that has to grow.
//
// Time: O(n + m) -- each array is summed once, or twice when the arrays are swapped once.
// Space: O(1) -- a few counters; the swapped call happens at most once, because after the swap a1's
// sum is the smaller one.

public final class MinEqualSumOfTwoArrays {

	public static long minSum(int[] a1, int[] a2) {
		long sum1 = 0;
		boolean hasZero = false;
		for (int num : a1) {
			hasZero |= num == 0; // RECORD whether a1 can grow
			sum1 += Math.max(num, 1); // SMALLEST: each 0 counts as 1
		}
		long sum2 = 0;
		for (int num : a2) {
			sum2 += Math.max(num, 1);
		}
		if (sum1 > sum2) { // SWAP so that a1 is the array that has to grow
			return minSum(a2, a1);
		}
		if (sum1 == sum2) { // EQUAL already: the smallest sums meet
			return sum1;
		}
		return hasZero ? sum2 : -1; // RETURN: a1 grows to sum2, if it has a 0 to raise
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 0, 1, 0 };
		int[] a2 = { 6, 5, 0 };
		System.out.println(minSum(a1, a2)); // 12

		int[] a3 = { 2, 0, 2, 0 };
		int[] a4 = { 1, 4 };
		System.out.println(minSum(a3, a4)); // -1

		int[] a5 = { 0, 16, 28, 12, 10, 15, 25, 24, 6, 0, 0 };
		int[] a6 = { 20, 15, 19, 5, 6, 29, 25, 8, 12 };
		System.out.println(minSum(a5, a6)); // 139
	}
}
