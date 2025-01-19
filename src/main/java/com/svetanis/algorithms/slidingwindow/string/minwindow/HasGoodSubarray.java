package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.util.Arrays.asList;

import java.util.List;

// Determine if a given array contains a subarray 
// of at least two elements whose sum is a multiple 
// of a specified number k.

// An array is considered to have a "good subarray" 
// if there exists at least one subarray 
// (consisting of two or more elements) such that 
// the sum of the elements in this subarray is a multiple of k.

public final class HasGoodSubarray {
	// Time Complexity: O(n)

	public static boolean hasGoodSubarray(List<Integer> nums, int k) {
		if (nums == null || nums.size() == 0) {
			return false;
		}
		int left = 0;
		int sum = 0;
		int size = 1;
		for (int right = 0; right < nums.size(); right++) {
			sum += nums.get(right);
			size += 1;
			boolean valid = sum % k == 0 && size >= 2;
			if (valid) {
				return true;
			}
			while (valid) {
				sum -= nums.get(left);
				size -= 1;
				left++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(hasGoodSubarray(asList(23, 2, 4, 7), 6)); // true
		System.out.println(hasGoodSubarray(asList(4, 5), 6)); // false
		System.out.println(hasGoodSubarray(asList(4, 8), 6)); // true
		System.out.println(hasGoodSubarray(asList(-3, -1, -2, -5, -7), 3)); // true
	}
}
