package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.List;

// given an array of integers
// calculate the sum of elements
// between indices left and right (inclusive)
// need to answer multiple queries
// efficiently. required to preprocess
// the array so that each query can be
// answered in constant time

public final class RangeSumQuery {
	// Query Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int rangeSumQuery(List<Integer> nums, int left, int right) {
		int[] prefix = prefix(nums);
		return prefix[right + 1] - prefix[left];
	}

	private static int[] prefix(List<Integer> nums) {
		int[] prefix = new int[nums.size() + 1];
		for (int i = 0; i < nums.size(); i++) {
			prefix[i + 1] = prefix[i] + nums.get(i);
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
