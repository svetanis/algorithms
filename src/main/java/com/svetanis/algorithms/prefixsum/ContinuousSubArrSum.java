package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 523. Continuous Subarray Sum
//
// Input: an array a of non-negative integers and an integer k. Returns true when some subarray of
// at least two elements has a sum that is a multiple of k; 0 counts as a multiple of every k. On
// LeetCode: 1 <= a.length <= 10^5, 0 <= a[i] <= 10^9, the whole sum fits in an int
// (0 <= sum <= 2^31 - 1), 1 <= k <= 2^31 - 1. k = 0 is outside that range and throws
// ArithmeticException at sum % k.
//
// The one idea: the running sum after index i is the total of a[0 .. i]. The numbers from start + 1
// to i add up to (running sum at i) - (running sum at start), and that difference is a multiple
// of k exactly when the two running sums leave the same remainder after division by k. One map
// entry is one remainder and the first index where a running sum left it. Only the first index is
// kept, because the earliest start gives the longest subarray, and the subarray needs at least
// two elements: i - (first index) >= 2. The seed 0 -> -1 stands for the empty prefix before
// index 0, so a subarray that starts at index 0 is checked the same way. Java's % keeps the sign
// of the sum, so a negative remainder is moved up by k; with a[i] >= 0 it never fires.
//
// Time: O(n) -- one pass, one map lookup per index, O(1) on average
// Space: O(min(n, k)) -- one entry per remainder seen: at most n + 1 of them, and at most k
//
// Sibling: HasGoodSubarray asks the same question under a rule where a sum of 0 does not
// count, so [5, 0, 0, 0] with k = 3 is false there and true here.

public final class ContinuousSubArrSum {

	public static boolean sum(int[] a, int k) {
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // MAP: remainder -> first index with it
		map.put(0, -1); // SEED: the empty prefix ends before index 0
		for(int i = 0; i < a.length; i++) {
			sum += a[i]; // PREFIX: the total of a[0 .. i]
			int remainder = sum % k; // KEY: the remainder, not the sum
			if(remainder < 0) {
				remainder += k; // SHIFT a negative remainder into [0, k)
			}
			if(map.containsKey(remainder) && i - map.get(remainder) >= 2) { // TWO or more elements
				return true; // RETURN: a sum that is a multiple of k
			}
			map.putIfAbsent(remainder, i); // FIRST only: the earliest start
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 23, 2, 4, 6, 7 };
		System.out.println(sum(a1, 6)); // true

		int[] a2 = { 23, 2, 6, 4, 7 };
		System.out.println(sum(a2, 6)); // true

		int[] a3 = { 23, 2, 6, 4, 7 };
		System.out.println(sum(a3, 13)); // false

		int[] a4 = { 23, 2, 4, 6, 6 };
		System.out.println(sum(a4, 7)); // true
	}
}
