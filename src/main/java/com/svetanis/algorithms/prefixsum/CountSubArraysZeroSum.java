package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Count the subarrays with sum zero -- no LeetCode number; source unknown
//
// Input: a list nums, negatives and zeros allowed. Returns the number of subarrays whose sum is 0.
// No constraints are given; the method assumes every running sum fits in an int and the count
// fits in an int.
//
// The one idea: the running sum after index i is the total of nums[0 .. i]. The numbers from
// start + 1 to i add up to (running sum at i) - (running sum at start), which is 0 exactly when the
// two running sums are equal. One map entry is one running-sum value and how many earlier prefixes
// had it; each of them starts one zero-sum subarray ending at i. The seed 0 -> 1 is the empty
// prefix before index 0, so a subarray that starts at index 0 is counted the same way.
//
// Time: O(n) -- one pass, one lookup and one update per number, O(1) on average
// Space: O(n) -- at most n + 1 distinct running sums
//
// Siblings: CountSubArraysGivenSum (560) is this method for any target; with target 0 its lookup
// key sum - target becomes sum. AllZeroSumSubarrays returns the subarrays themselves.

public final class CountSubArraysZeroSum {

	public static int subarraySum(List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>(); // MAP: running sum -> how many prefixes had it
		map.put(0, 1); // SEED: the empty prefix
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i); // PREFIX: the total of nums[0 .. i]
			if (map.containsKey(sum)) {
				count += map.get(sum); // RECORD: one subarray per earlier equal sum
			}
			int freq = map.getOrDefault(sum, 0);
			map.put(sum, freq + 1); // ADD after the lookup, so a subarray is never empty
		}
		return count;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(6, -4, 4, 2, -2);
		System.out.println(subarraySum(list)); // 3

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(subarraySum(list1)); // 1

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(subarraySum(list2)); // 0

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(subarraySum(list3)); // 5

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(subarraySum(list4)); // 3
	}
}
