package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.svetanis.algorithms.intervals.Interval;

// Subarray with a given sum, negatives allowed -- no LeetCode number; source unknown
//
// Input: target and a list of integers, negatives and zeros allowed. A subarray is a run of
// neighbouring elements. Returns one subarray whose sum equals target, as an Interval
// [start, end] of inclusive indices: the one that ends first, and of those ending there, the
// shortest. Returns Optional.empty() when none does. No constraints are given; the method
// assumes every running sum fits in an int.
//
// The one idea: the running sum through i is the total of list[0..i]. The elements from s to i add
// up to (running sum through i) - (running sum through s - 1), so a subarray ending at i sums to
// target exactly when an earlier running sum equals (running sum through i) - target. One map entry
// is one running-sum value and the index where the subarray AFTER it starts -- i + 1 for the
// running sum through i, and 0 for the empty prefix, the seed 0 -> 0 -- so the lookup hands back
// the start directly, where AllGivenSumSubarrays and LongestSubArrayWithSumEqualsKHashMap store i
// and add 1. A repeated running sum is overwritten with the later start, which is why the answer
// is the shortest one ending at i. The first i with a match ends the search.
//
// Time: O(n) -- one pass; each map lookup and insert is O(1) on average.
// Space: O(n) -- up to n + 1 running sums in the map.
//
// Siblings: slidingwindow.array.GivenSumSubArrayHashMap is this question with (-1, -1) for none
// and a separate sum == target check in place of the seed; GivenSumSubArraySlidingWindow there
// needs every value non-negative and uses a window, no map. AllGivenSumSubarrays returns every such
// subarray, and CountSubArraysGivenSum (560) counts them.

public final class SubArrayGivenSum {

	public static Optional<Interval> subarraySum(int target, List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>(); // MAP: running sum -> where the next subarray starts
		map.put(0, 0); // SEED: the empty prefix; a subarray after it starts at 0
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i); // RUNNING SUM through i
			int diff = sum - target; // NEED: the earlier running sum that leaves exactly target
			if (map.containsKey(diff)) {
				return Optional.of(new Interval(map.get(diff), i)); // FIRST end found: stop
			}
			map.put(sum, i + 1); // STORE the start after i; a repeat keeps the later, shorter one
		}
		return Optional.empty(); // NONE sums to target
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, -20, -3, 30, 5, 4);
		System.out.println(subarraySum(7, list)); // Optional[[1, 3]] -- -20 + -3 + 30

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(subarraySum(5, list1)); // Optional[[2, 3]] -- -3 + 8

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(subarraySum(3, list2)); // Optional[[0, 2]]
	}
}
