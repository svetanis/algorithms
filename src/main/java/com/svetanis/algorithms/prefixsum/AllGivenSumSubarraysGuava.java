package com.svetanis.algorithms.prefixsum;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.svetanis.algorithms.intervals.Interval;

// Every subarray with a given sum -- no LeetCode number; source unknown
//
// Input: target and a list of integers, negatives and zeros allowed. Returns every subarray whose
// sum equals target, as an Interval [start, end] of inclusive indices, ordered by end and then by
// start. No constraints are given; the method assumes every running sum fits in an int.
//
// The one idea: a prefix sum is the total of the numbers from index 0 up to and including i. The
// numbers from start + 1 to i add up to (prefix sum at i) - (prefix sum at start), so a subarray
// ending at i sums to target exactly when an earlier prefix sum equals (prefix sum at i) - target.
// One map entry is one prefix-sum value and the list of every index where the running sum had that
// value. A list, not one index: every earlier index with the right value starts its own subarray,
// and all of them are wanted. The subarrays that start at index 0 have no earlier index, so they
// are checked on their own with sum == target.
//
// Time: O(n + r) -- one pass over the list, plus one step per subarray reported; r can reach
//   n(n + 1) / 2, as on a list of zeros with target 0
// Space: O(n + r) -- the map holds n indices in all, and the result holds r intervals
//
// Siblings: AllGivenSumSubarrays is the same method in plain Java, with a HashMap from each prefix sum
// to a list of indices; here Guava's ArrayListMultimap holds that list itself, so put(sum, i)
// appends without creating it first, and the answer comes back as an ImmutableList.

public final class AllGivenSumSubarraysGuava {

	public static ImmutableList<Interval> givenSumSubarrays(int target, List<Integer> list) {
		int sum = 0;
		List<Interval> intervals = newArrayList();
		Multimap<Integer, Integer> multi = ArrayListMultimap.create(); // MAP: prefix sum -> all its indices
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i); // PREFIX: the total of list[0 .. i]
			if (sum == target) {
				intervals.add(new Interval(0, i)); // FROM 0: the whole prefix is a subarray
			}
			int diff = sum - target; // LOOK UP: the prefix sum a start would need
			if (multi.containsKey(diff)) {
				for (int start : multi.get(diff)) {
					intervals.add(new Interval(start + 1, i)); // RECORD: one per earlier index
				}
			}
			multi.put(sum, i); // ADD after the lookup: put appends to the key's list
		}
		return ImmutableList.copyOf(intervals); // RETURN: immutable
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, -20, -3, 30, 5, 4);
		System.out.println(givenSumSubarrays(7, list)); // [[1, 3]]

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(givenSumSubarrays(5, list1)); // [[2, 3], [4, 4]]

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(givenSumSubarrays(3, list2)); // [[0, 2]]

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(givenSumSubarrays(0, list3)); // [[2, 4], [2, 6], [5, 6], [6, 9], [0, 10]]

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(givenSumSubarrays(0, list4)); // [[0, 2], [2, 3], [1, 5]]
	}
}
