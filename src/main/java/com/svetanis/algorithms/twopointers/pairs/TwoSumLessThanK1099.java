package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;

// 1099. Two Sum Less Than K
//
// a is an array of positive integers in any order, and target is the bound k. Returns the largest
// sum a[i] + a[j] of two elements at different positions that is strictly less than target, or
// -1 when no pair sums below target. On LeetCode: up to 100 numbers, each up to 1000, and k up
// to 2000.
//
// Sort, then put one index on the smallest number (left) and one on the largest (right).
// a[right] is the largest partner a[left] still has. If their sum is below target, it is the best
// sum a[left] can make with anyone left: record it, and a[left] has nothing more to offer, so it
// is dropped. If the sum is target or more, a[right] is too big even with the smallest number
// left, so it is too big with every number, and it is dropped. Each step drops one number that
// cannot improve the answer, so the best pair is never skipped.
//
// Related, the same converging loop on other questions:
//   twopointers.TwoSumSorted167          the positions of the one pair that sums exactly to target
//   twopointers.pairs.PairsLessGivenSum  counts the pairs below the bound instead of the best one

public final class TwoSumLessThanK1099 {
	// Time Complexity: O(n log n), the sort; the loop after it takes at most n - 1 steps, since
	// every step drops one number
	// Space Complexity: O(1) besides the sort's own working space; a is sorted in place

	public static int maxSum(int[] a, int target) {
		Arrays.sort(a);                   // SORT: the sort creates the pattern
		int max = -1;                     // ANSWER: -1 is the problem's "no pair" answer
		int left = 0;                     // START: the smallest number
		int right = a.length - 1;         // START: the largest number
		while (left < right) {            // STOP: a pair needs two different positions
			int sum = a[left] + a[right];
			if (sum < target) {
				max = Math.max(max, sum); // RECORD: this pair qualifies, then try bigger
				left++;                   // DROP LEFT: its best partner has been seen
			} else {
				right--;                  // DROP RIGHT: too big for any partner
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 34, 23, 1, 24, 75, 33, 54, 8 };
		System.out.println(maxSum(a, 60)); // 58 -- 24 + 34, LeetCode's own example 1

		int[] a1 = { 10, 20, 30 };
		System.out.println(maxSum(a1, 15)); // -1 -- no pair sums below k
	}
}