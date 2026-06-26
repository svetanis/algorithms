package com.svetanis.algorithms.math;

// 3512. Minimum Operations to Make Array Sum Divisible by K

public final class MinOperationsSumDivisibleK {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minOperations(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum % k;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 9, 7 };
		System.out.println(minOperations(a1, 5)); // 4
		int[] a2 = { 4, 1, 3 };
		System.out.println(minOperations(a2, 4)); // 0
		int[] a3 = { 3, 2 };
		System.out.println(minOperations(a3, 6)); // 5
	}
}