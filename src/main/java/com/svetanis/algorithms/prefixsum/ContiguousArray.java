package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 525. Contiguous Array
//
// Input: an array a of 0s and 1s. Returns the length of the longest subarray that holds as many 0s
// as 1s, or 0 when there is none. On LeetCode: 1 <= a.length <= 10^5, a[i] is 0 or 1.
//
// The one idea: count each 1 as +1 and each 0 as -1. A subarray then has equal counts exactly when
// its numbers add up to 0. The running total after index i is the balance of 1s over 0s in
// a[0 .. i]; a subarray from start + 1 to i balances exactly when the running total at i equals
// the running total at start. One map entry is one running-total value and the first index where
// it appeared. The first index is kept and never overwritten, because the earliest start gives the
// longest subarray. The seed 0 -> -1 stands for the empty prefix before index 0, so a subarray
// that starts at index 0 is handled the same way.
//
// Time: O(n) -- one pass, one map lookup per index, O(1) on average
// Space: O(n) -- at most n + 1 distinct running totals, from -n to n
//
// Siblings: none for 525. LongestSubArrayLenWithSumEqualsKHashMap is the same first-index map for
// any target sum, without the remap of 0 to -1.

public final class ContiguousArray {

	public static int maxLen(int[] a) {
		int n = a.length;
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // MAP: running total -> first index with it
		map.put(0, -1); // SEED: the empty prefix ends before index 0
		for (int i = 0; i < n; i++) {
			sum += a[i] == 1 ? 1 : -1; // REMAP: 1 counts +1, 0 counts -1
			if (map.containsKey(sum)) {
				max = Math.max(max, i - map.get(sum)); // RECORD: balanced after the first index
			} else {
				map.put(sum, i); // FIRST only: the earliest start is the longest
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1 };
		System.out.println(maxLen(a1)); // 2

		int[] a2 = { 0, 1, 0 };
		System.out.println(maxLen(a2)); // 2

		int[] a3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(maxLen(a3)); // 0 -- no zeros at all, so no balanced span
	}
}
