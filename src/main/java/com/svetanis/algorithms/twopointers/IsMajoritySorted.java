package com.svetanis.algorithms.twopointers;

// 1150. Check If a Number Is Majority Element in a Sorted Array

public final class IsMajoritySorted {
	// Time Complexity: O(log n)

	public static boolean isMajorityElement(int[] nums, int target) {
		int n = nums.length;
		int start = binary(nums, target);
		return start + n/2 < n && nums[start + n/2] == target;
	}
	
	private static int binary(int[] a, int target) {
		int n = a.length;
		int left = 0;
		int right = n - 1;
		
		while(left < right) {
			int mid = left + (right - left)/2;
			if(a[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 4, 5, 5, 5, 5, 5, 6, 6 };
		System.out.println(isMajorityElement(a1, 5)); // true

		int[] a2 = { 10, 100, 101, 101 };
		System.out.println(isMajorityElement(a2, 101)); // false
	}
}
