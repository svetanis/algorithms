package com.svetanis.algorithms.prefixsum;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// given an integer array nums,
// return a product array such that
// product[i] equals to the 
// product of all the elements
// of the array except nums[i]

public final class ArrayProductExceptSelfMemoryOptimized {
	// Time Complexity: O(n)

	private static ImmutableList<Integer> pes(List<Integer> nums) {
		int n = nums.size();
		int left = 1;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(left);
			left *= nums.get(i);
		}
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			int product = list.get(i) * right;
			list.set(i, product);
			right *= nums.get(i);
		}
		return newList(list);
	}

	public static void main(String[] args) {
		System.out.println(pes(asList(1, 2, 3, 4))); // [24,12,8,6]
		System.out.println(pes(asList(-1, 1, 0, -3, 3))); // [0,0,9,0,0]
	}
}