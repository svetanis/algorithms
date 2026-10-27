package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 974. Subarray Sums Divisible by K
//
// Input: an array a (nums on LeetCode) and k. A subarray is a run of neighbouring elements, at
// least one long. Returns how many subarrays have a sum divisible by k. On LeetCode:
// 1 <= a.length <= 3 * 10^4, -10^4 <= a[i] <= 10^4, 2 <= k <= 10^4. Outside that range: k = 0
// throws ArithmeticException, and a negative k gives wrong counts, because adding a negative k
// moves a negative remainder further below 0 instead of into 0 .. k - 1 ({-3, 5} with k = -5
// returns 0 where the answer is 1).
//
// The one idea: the running sum through i is the total of a[0..i]. A subarray a[s..i] sums to
// (running sum through i) - (running sum through s - 1), and that difference is divisible by k
// exactly when the two running sums leave the same remainder after dividing by k. One map entry is
// one remainder and how many running sums so far have left it; the seed 0 -> 1 is the empty
// prefix, so a subarray starting at index 0 is counted too. Each new running sum pairs with every
// earlier one of the same remainder, so its count is added before its own is raised. Java's %
// keeps the sign of the left side, so a negative running sum gives a negative remainder; adding k
// moves it into 0 .. k - 1, so that -1 and 4 (k = 5) land on the same entry.
//
// Time: O(n) -- one pass; each map lookup and update is O(1) on average.
// Space: O(min(n, k)) -- at most k different remainders, and at most n + 1 running sums.
//
// Siblings: none for this problem. ContinuousSubArrSum (523) keys the same map by remainder and
// asks whether a subarray of length at least 2 exists.

public final class SubArraySumDivisibleByK {

	public static int count(int[] a, int k) {
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // MAP: remainder -> how many running sums left it
		map.put(0, 1); // SEED: the empty prefix, remainder 0
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i]; // RUNNING SUM through i
			int remainder = sum % k;
			if (remainder < 0) {
				remainder += k; // FOLD: a negative remainder into 0 .. k - 1
			}
			count += map.getOrDefault(remainder, 0); // PAIR with every earlier running sum of this remainder
			map.put(remainder, map.getOrDefault(remainder, 0) + 1); // RECORD after counting
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 5, 0, -2, -3, 1 };
		System.out.println(count(a1, 5)); // 7

		int[] a2 = { 5 };
		System.out.println(count(a2, 9)); // 0
	}
}
