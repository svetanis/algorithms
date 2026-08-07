package com.svetanis.algorithms.twopointers.dups;

// 287. Find the Duplicate Number

// Given an array of integers nums containing n + 1 integers 
// where each integer is in the range [1, n] inclusive.

// There is only one repeated number in nums, return this repeated number.

// You must solve the problem without modifying 
// the array nums and using only constant extra space.

// 1 <= n <= 105
// nums.length == n + 1
// 1 <= nums[i] <= n
// All the integers in nums appear only once except 
// for precisely one integer which appears two or more times.
    
public final class DuplicateInPlaceBinary {
	// Time Complexity: O(n log n)

	public static int duplicate(int[] a) {
		int low = 0;
		int high = a.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			int count = count(mid, a);
			if (count > mid) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		// when low == high, duplicate is found
		return low;
	}

	private static int count(int mid, int[] a) {
		int count = 0;
		for (int element : a) {
			if (element <= mid) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
