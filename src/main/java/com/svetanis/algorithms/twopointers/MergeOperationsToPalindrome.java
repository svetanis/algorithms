package com.svetanis.algorithms.twopointers;

// 2422. Merge Operations to Turn Array Into a Palindrome

public final class MergeOperationsToPalindrome {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minOperations(int[] nums) {
		int n = nums.length;
		int left = 0;
		int right = n - 1;
		int a = nums[left], b = nums[right];
		int count = 0;
		while (left < right) {
			if (a < b) {
				left += 1;
				a += nums[left];
				count += 1;
			} else if (b < a) {
				right -= 1;
				b += nums[right];
				count += 1;
			} else {
				left += 1;
				right -= 1;
				a = nums[left];
				b = nums[right];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 3, 2, 1, 2, 3, 1 };
		System.out.println(minOperations(a1)); // 2

		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(minOperations(a2)); // 3
	}
}
