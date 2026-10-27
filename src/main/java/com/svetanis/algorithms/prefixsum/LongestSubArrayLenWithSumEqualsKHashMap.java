package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 325. Maximum Size Subarray Sum Equals k
//
// Input: target (k on LeetCode) and an array a; note the order, target first. A subarray is a run
// of neighbouring elements. Returns the length of the longest subarray whose elements add up to
// exactly target, or 0 when none does. On LeetCode: 1 <= a.length <= 2 * 10^5,
// -10^4 <= a[i] <= 10^4, -10^9 <= k <= 10^9.
//
// The one idea: the running sum at i is the total of a[0..i]. The elements from s + 1 to i add up
// to (running sum at i) - (running sum at s), so a subarray ending at i sums to target exactly when
// an earlier running sum equals (running sum at i) - target. One map entry is one running-sum value
// and the FIRST index where it appeared: the earliest start gives the longest subarray, so a later
// index with the same running sum is never stored. The entry 0 -> -1 stands for the empty prefix
// before index 0, so a subarray that starts at index 0 is found by the same lookup as any other.
// Values can be negative, so the running sum goes down as well as up; the map does not care.
//
// Time: O(n) -- one pass; each map lookup and insert is O(1) on average.
// Space: O(n) -- up to n + 1 distinct running sums in the map.
//
// Siblings: LongestSubArrayWithSumEqualsKHashMap is the same loop over a List<Integer>, returning
// the subarray's first and last index instead of its length, with an int running sum.

public final class LongestSubArrayLenWithSumEqualsKHashMap {

	public static int maxSubArrLen(int target, int[] a) {
		int max = 0; // BEST length so far; 0 when nothing sums to target
		long sum = 0; // RUNNING SUM of a[0..i]; long, since 2 * 10^5 values of 10^4 pass 2^31
		Map<Long, Integer> map = new HashMap<>(); // MAP: running sum -> first index where it appeared
		map.put(0L, -1); // SEED: the empty prefix, so no separate sum == target check is needed
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			long diff = sum - target; // NEED: the earlier running sum that leaves exactly target
			if (map.containsKey(diff)) {
				int start = map.get(diff); // the subarray is a[start + 1 .. i]
				max = Math.max(max, i - start); // RECORD: its length is i - start
			}
			map.putIfAbsent(sum, i); // KEEP FIRST: a later index would only start a shorter subarray
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxSubArrLen(15, new int[] {10, 5, 2, 7, 1, 9})); // 4 -- 5, 2, 7, 1
		System.out.println(maxSubArrLen(3, new int[] {1, -1, 5, -2, 3})); // 4 -- 1, -1, 5, -2
		System.out.println(maxSubArrLen(1, new int[] {-2, -1, 2, 1})); // 2 -- -1, 2
	}
}