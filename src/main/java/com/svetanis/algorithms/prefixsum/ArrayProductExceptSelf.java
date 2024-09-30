package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// given an integer array nums,
// return a product array such that
// product[i] equals to the 
// product of all the elements
// of the array except nums[i]

public final class ArrayProductExceptSelf {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private static List<Integer> pes(List<Integer> nums) {
		int[] left = leftPrefix(nums);
		int[] right = rightPrefix(nums);
		List<Integer> product = new ArrayList<>();
		for (int i = 0; i < nums.size(); i++) {
			product.add(right[i] * left[i]);
		}
		return product;
	}

	private static final int[] leftPrefix(List<Integer> list) {
		int n = list.size();
		int[] a = new int[n];
		a[0] = 1;
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] * list.get(i - 1);
		}
		return a;
	}

	private static final int[] rightPrefix(List<Integer> list) {
		int n = list.size();
		int[] a = new int[n];
		a[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			a[i] = a[i + 1] * list.get(i + 1);
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(pes(asList(1, 2, 3, 4))); // [24,12,8,6]
		System.out.println(pes(asList(-1, 1, 0, -3, 3))); // [0,0,9,0,0]
	}
}