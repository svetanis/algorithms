package com.svetanis.algorithms.prefixsum;

import static java.lang.Integer.MIN_VALUE;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.algorithms.intervals.Interval;

// Longest subarray with a given sum, as its first and last index -- no LeetCode number; it is
// LeetCode 325's question, which asks only for the length
//
// Input: target and a list of integers, negatives and zeros allowed. A subarray is a run of
// neighbouring elements. Returns the longest subarray whose elements add up to exactly target, as
// an Interval [start, end] of inclusive indices; among equally long ones, the one that ends first;
// [-1, -1] when none does. No constraints are given. The running sum is an int: within 325's
// bounds (n <= 2 * 10^5, |value| <= 10^4, |target| <= 10^9) it can pass 2^31 and wrap, but a wrap
// never makes a false match, because no true subarray sum minus target reaches 2^32. Beyond those
// bounds it can: {Integer.MAX_VALUE, Integer.MAX_VALUE, 2} with target 0 returns [0, 2].
//
// The one idea: the running sum at i is the total of list[0..i]. The elements from s + 1 to i add
// up to (running sum at i) - (running sum at s), so a subarray ending at i sums to target exactly
// when an earlier running sum equals (running sum at i) - target. One map entry is one running-sum
// value and the FIRST index where it appeared, because the earliest start gives the longest
// subarray. The entry 0 -> -1 stands for the empty prefix before index 0, so a subarray starting at
// index 0 is found by the same lookup as any other.
//
// Time: O(n) -- one pass; each map lookup and insert is O(1) on average.
// Space: O(n) -- up to n + 1 distinct running sums in the map.
//
// Siblings: LongestSubArrayLenWithSumEqualsKHashMap (325) is the same loop over an int[], returning
// only the length, with a long running sum. LongestSubArrayWithSumClosestToZero and its TreeMap
// twin call maxSubArray for their second pass.

public final class LongestSubArrayWithSumEqualsKHashMap {

	public static Interval maxSubArray(int target, List<Integer> list) {
		int sum = 0; // RUNNING SUM of list[0..i]
		int max = MIN_VALUE; // BEST length so far; none yet
		int left = -1; // SPAN of the best: [-1, -1] until one is found
		int right = -1;

		Map<Integer, Integer> map = new HashMap<>(); // MAP: running sum -> first index where it appeared
		map.put(0, -1); // SEED: the empty prefix, so a subarray starting at 0 is found too
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			int diff = sum - target; // NEED: the earlier running sum that leaves exactly target
			if (map.containsKey(diff)) {
				int start = map.get(diff); // the subarray is list[start + 1 .. i]
				if (max < i - start) { // LONGER only: a tie keeps the one that ends first
					max = i - start;
					left = start + 1; // RECORD the span
					right = i;
				}
			}
			map.putIfAbsent(sum, i); // KEEP FIRST: a later index would only start a shorter subarray
		}
		return new Interval(left, right);
	}

	public static void main(String[] args) {
		System.out.println(maxSubArray(15, asList(10, 5, 2, 7, 1, 9))); // [1, 4]
		System.out.println(maxSubArray(3, asList(1, -1, 5, -2, 3))); // [0, 3]
	}
}