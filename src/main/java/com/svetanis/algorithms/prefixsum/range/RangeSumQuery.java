package com.svetanis.algorithms.prefixsum.range;

import static java.util.Arrays.asList;

import java.util.List;

// Range sum, rebuilt per call -- not a LeetCode submission; LeetCode 303's question as a static
// method over a List
//
// Input: a list nums and two indices, 0 <= left <= right < nums.size(). Returns nums[left] + ... +
// nums[right]. Within 303's bounds (n <= 10^4, |nums[i]| <= 10^5) every total fits in an int.
//
// The one idea: prefix[i] holds the total of the first i elements, nums[0] + ... + nums[i - 1],
// and prefix[0] = 0 for no elements. The range is the total of the first right + 1 elements minus
// the total of the first left: prefix[right + 1] - prefix[left]. The leading 0 means left == 0
// needs no case of its own. The prefix array is built inside the call and thrown away after it, so
// every query pays the whole build -- the same cost as adding up the range directly. Answering
// many queries in O(1) each needs the build done once and kept, which a static method has nowhere
// to keep.
//
// Time: O(n) per query -- the build, every call.
// Space: O(n) -- the prefix array, rebuilt every call.
//
// Siblings: RangeSumQueries is the same rebuild-per-call over an int[], using PrefixSumArray's
// inclusive totals and a left == 0 case. RangeSumQuerySubmit and RangeSumQuerySubmit2 are
// LeetCode 303 itself: the array is built once, in the constructor, and each query is O(1).

public final class RangeSumQuery {

	public static int rangeSumQuery(List<Integer> nums, int left, int right) {
		int[] prefix = prefix(nums); // BUILD: on every call
		return prefix[right + 1] - prefix[left]; // RANGE: first right + 1 elements, minus first left
	}

	private static int[] prefix(List<Integer> nums) {
		int[] prefix = new int[nums.size() + 1]; // prefix[0] = 0: no elements
		for (int i = 0; i < nums.size(); i++) {
			prefix[i + 1] = prefix[i] + nums.get(i); // EXTEND: one more element
		}
		return prefix;
	}

	public static void main(String[] args) {
		System.out.println(rangeSumQuery(asList(1, 2, 3, 4), 1, 3)); // 9
		System.out.println(rangeSumQuery(asList(3, 0, 1, 4, 2), 1, 3)); // 5
		System.out.println(rangeSumQuery(asList(-2, 0, 3, -5, 2, -1), 0, 2)); // 1
		System.out.println(rangeSumQuery(asList(-2, 0, 3, -5, 2, -1), 2, 5)); // -1
		System.out.println(rangeSumQuery(asList(-2, 0, 3, -5, 2, -1), 0, 5)); // -3
	}
}
