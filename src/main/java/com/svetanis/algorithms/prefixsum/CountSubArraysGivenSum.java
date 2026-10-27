package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 560. Subarray Sum Equals K
//
// Input: an integer target (LeetCode's k) and a list nums, negatives allowed. Returns the number of
// subarrays whose sum equals target. On LeetCode: 1 <= nums.length <= 2 * 10^4,
// -1000 <= nums[i] <= 1000, -10^7 <= k <= 10^7.
//
// The one idea: the running sum after index i is the total of nums[0 .. i]. The numbers from
// start + 1 to i add up to (running sum at i) - (running sum at start), so a subarray ending at i
// sums to target once for every earlier running sum equal to (running sum at i) - target. One map
// entry is one running-sum value and how many earlier prefixes had it. The seed 0 -> 1 is the empty
// prefix before index 0, so a subarray that starts at index 0 is counted the same way. A sliding
// window cannot do this: with negative numbers, growing the window can lower its sum.
//
// Time: O(n) -- one pass, one lookup and one update per number, O(1) on average
// Space: O(n) -- at most n + 1 distinct running sums
//
// Siblings: CountSubArraysZeroSum is this method with target 0. AllGivenSumSubarrays
// returns the subarrays themselves instead of how many.

public final class CountSubArraysGivenSum {

	public static int subarraySum(int target, List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>(); // MAP: running sum -> how many prefixes had it
		map.put(0, 1); // SEED: the empty prefix
		int sum = 0;
		int count = 0;
		for (int num : nums) {
			sum += num; // PREFIX: the total so far
			int diff = sum - target; // LOOK UP: the running sum a start would need
			count += map.getOrDefault(diff, 0); // RECORD: one subarray per such start
			int freq = map.getOrDefault(sum, 0);
			map.put(sum, freq + 1); // ADD after the lookup, so a subarray is never empty
		}
		return count;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, 1, 1);
		System.out.println(subarraySum(2, list)); // 2

		List<Integer> list2 = asList(10, 5, -5, -20, 10);
		System.out.println(subarraySum(-10, list2)); // 3
	}
}
