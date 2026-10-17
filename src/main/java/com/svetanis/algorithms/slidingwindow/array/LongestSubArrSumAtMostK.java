package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// Longest subarray with sum at most k -- no LeetCode number; source unknown

// given an array of integers
// find the length of longest subarray
// whose sum is at most K
// Takes target (the K) and the list. Returns that length; 0 when every
// number is larger than target, or the list is empty.

// The window assumes every number is >= 0 and target >= 0, which is what
// makes it work: with no negatives, adding a number never lowers the sum
// and removing one never raises it, so once the sum is too big the only
// repair is to move the left end. A negative number breaks that and the
// method returns a length too short; a negative target throws
// IndexOutOfBoundsException, because even the empty window is too big.

// Time: O(n) -- each index enters the window once and leaves at most once
// Space: O(1) -- the running sum and two indices

// Siblings:
//   MaxSumSubArrSumLessGivenSum -- the same window and the same "at most",
//     but reports the largest SUM that fits, not the longest length.
//   CountSubArrsSumLessThanK -- counts the subarrays with sum below k.
//   MinSizeSubArraySum (209) -- the mirror: the SHORTEST subarray whose
//     sum reaches the target, so it measures inside the shrink loop.

public final class LongestSubArrSumAtMostK {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sum(int target, List<Integer> list) {
		int sum = 0;
		int left = 0;
		int max = 0;
		for (int right = 0; right < list.size(); right++) {
			sum += list.get(right); // ADD
			while (sum > target) { // SHRINK: while the sum is too big
				sum -= list.get(left); // REMOVE
				left++;
			}
			max = max(max, right - left + 1); // MEASURE: after the shrink, the sum fits
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(sum(4, asList(1, 2, 1, 0, 1, 1, 0))); // 5 : 1,0,1,1,0
		System.out.println(sum(10, asList(1, 6, 3, 1, 2, 4, 5))); // 4 : 3,1,2,4
	}
}
