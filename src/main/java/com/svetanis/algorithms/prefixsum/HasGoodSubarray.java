package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Good subarray: a sum that is a multiple of k -- no LeetCode number; source unknown
//
// Determine if a given array contains a subarray of at least two elements whose sum is a
// multiple of a specified number k. Examples from the source:
//   [23, 2, 4, 7], k = 3 -> [2, 4]
//   [5, 0, 0, 0],  k = 3 -> none
// Input: a list nums (negatives and zeros allowed) and k >= 1. Returns true when such a subarray
// exists. k = 0 is outside that range and throws ArithmeticException at Math.floorMod.
//
// The second example does not count [0, 0], although its sum 0 = 3 x 0 is a multiple of 3, so
// this method counts only a sum that is a multiple of k and not 0. LeetCode 523 (Continuous
// Subarray Sum) asks the same question and counts a sum of 0: see the sibling below.
//
// The one idea: prefix[i] is the sum of the first i numbers, so nums[start .. end - 1] sums to
// prefix[end] - prefix[start]. That difference is a multiple of k exactly when the two prefix
// sums leave the same remainder after division by k, and it is not 0 exactly when the two
// prefix sums differ. So for each end, the question is whether some earlier start, at least two
// places back, has the same remainder and a different sum. Count the starts by remainder and by
// sum: the starts with the same remainder outnumber the starts with the same sum exactly when
// one of them has a different sum.
//
// A sliding window cannot answer this: adding a number can make the sum a multiple of k and the
// next number can undo it, so there is no rule for when the left end should move.
//
// Time: O(n) -- one pass builds the prefix sums, one pass adds each start once and looks up
//   two counts per end; each map operation O(1) on average
// Space: O(n) -- the prefix sums and the two maps of counts
//
// Siblings: ContinuousSubArrSum (523) answers the same question under LeetCode's rule, where a
// sum of 0 counts: it keeps only the first index of each remainder, so [5, 0, 0, 0] with k = 3
// is true there and false here.

public final class HasGoodSubarray {

	public static boolean hasGoodSubarray(List<Integer> nums, int k) {
		int n = nums.size();
		long[] prefix = new long[n + 1]; // prefix[i] = the sum of the first i numbers
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + nums.get(i); // PREFIX: long, so no sum overflows
		}
		Map<Integer, Integer> byRemainder = new HashMap<>(); // remainder -> how many starts leave it
		Map<Long, Integer> bySum = new HashMap<>(); // prefix sum -> how many starts equal it
		for (int end = 2; end <= n; end++) { // the subarray is nums[start .. end - 1]
			long startSum = prefix[end - 2]; // JOIN: start = end - 2 leaves two numbers before end
			byRemainder.merge(Math.floorMod(startSum, k), 1, Integer::sum); // never negative
			bySum.merge(startSum, 1, Integer::sum);
			long endSum = prefix[end]; // END: the sum of nums[0 .. end - 1]
			int sameRemainder = byRemainder.getOrDefault(Math.floorMod(endSum, k), 0); // MULTIPLE
			int sameSum = bySum.getOrDefault(endSum, 0); // ZERO: of those, the ones summing to 0
			if (sameRemainder > sameSum) {
				return true; // RETURN: a start with the same remainder and a different sum
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(hasGoodSubarray(asList(23, 2, 4, 7), 3)); // true, [2, 4]
		System.out.println(hasGoodSubarray(asList(5, 0, 0, 0), 3)); // false, [0, 0] sums to 0
		System.out.println(hasGoodSubarray(asList(23, 2, 4, 7), 6)); // true
		System.out.println(hasGoodSubarray(asList(4, 5), 6)); // false
		System.out.println(hasGoodSubarray(asList(4, 8), 6)); // true
		System.out.println(hasGoodSubarray(asList(-3, -1, -2, -5, -7), 3)); // true
	}
}
