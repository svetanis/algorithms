package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.algorithms.intervals.Interval;

// Every subarray with sum zero -- no LeetCode number; source unknown
//
// Input: a list of integers, negatives and zeros allowed. Returns every subarray whose sum is 0,
// as an Interval [start, end] of inclusive indices, ordered by end and then by start. No
// constraints are given; the method assumes every running sum fits in an int.
//
// The one idea: a prefix sum is the total of the numbers from index 0 up to and including i. The
// numbers from start + 1 to i add up to (prefix sum at i) - (prefix sum at start), which is 0
// exactly when the two prefix sums are equal. So a subarray ending at i sums to 0 once for every
// earlier index whose running sum was the same value. One map entry is one prefix-sum value and the
// list of every index where the running sum had that value -- a list, because each of those
// indices starts its own subarray. The subarrays that start at index 0 have no earlier index, so
// they are checked on their own with sum == 0.
//
// Time: O(n + r) -- one pass over the list, plus one step per subarray reported; r can reach
//   n(n + 1) / 2, as on a list of zeros
// Space: O(n + r) -- the map holds n indices in all, and the result holds r intervals
//
// Siblings: AllGivenSumSubarrays is the same method for any target; with target 0 its lookup key
// sum - target becomes sum. ZeroSumSubArrays in the data-structures repo
// (datastructures.array.subarray.zerosum) is the same loop over an int[], returning pairs.
// CountSubArraysZeroSum returns only how many there are.
// AllZeroSumSubarraysGuava is this method with Guava's ArrayListMultimap in place of the
// HashMap of lists, returning an ImmutableList.

public final class AllZeroSumSubarrays {

	public static List<Interval> zeroSumSubarrays(List<Integer> list) {
		int sum = 0;
		List<Interval> intervals = new ArrayList<>();
		Map<Integer, List<Integer>> multi = new HashMap<>(); // MAP: prefix sum -> all its indices
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i); // PREFIX: the total of list[0 .. i]
			if (sum == 0) {
				intervals.add(new Interval(0, i)); // FROM 0: the whole prefix sums to 0
			}
			if (multi.containsKey(sum)) { // LOOK UP: an earlier equal prefix sum
				for (int start : multi.get(sum)) {
					intervals.add(new Interval(start + 1, i)); // RECORD: one per earlier index
				}
			}
			multi.computeIfAbsent(sum, key -> new ArrayList<>()).add(i); // ADD after the lookup
		}
		return intervals;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(6, -4, 4, 2, -2);
		System.out.println(zeroSumSubarrays(list)); // [[1, 2], [1, 4], [3, 4]]

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(zeroSumSubarrays(list1)); // [[1, 2]]

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(zeroSumSubarrays(list2)); // []

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(zeroSumSubarrays(list3)); // [[2, 4], [2, 6], [5, 6], [6, 9], [0, 10]]

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(zeroSumSubarrays(list4)); // [[0, 2], [2, 3], [1, 5]]
	}
}
