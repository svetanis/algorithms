package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// given an array of integers
// find the length of longest subarray
// whose sum is at most K

public final class LongestSubArrSumAtMostK {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sum(int target, List<Integer> list) {
		int sum = 0;
		int left = 0;
		int max = MIN_VALUE;
		for (int right = 0; right < list.size(); right++) {
			sum += list.get(right);
			while (sum > target) {
				sum -= list.get(left);
				left++;
			}
			max = max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(sum(4, asList(1, 2, 1, 0, 1, 1, 0)));
		System.out.println(sum(10, asList(1, 6, 3, 1, 2, 4, 5))); // 4 : 3,1,2,4
	}
}
