package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 1283. Find the Smallest Divisor Given a Threshold

public final class SmallestDivisorGivenThreshold {
	// Time Complexity: O(n log m)
	
	public static int smallestDivisor(int[] nums, int threshold) {
		int left = 1; // min possible divisor
		int right = Arrays.stream(nums).max().getAsInt();
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (sum(nums, mid) <= threshold) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int sum(int[] nums, int divisor) {
		int sum = 0;
		for (int num : nums) {
			sum += Math.ceilDiv(num, divisor);
			// sum += (num + divisor - 1)/divisor;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 5, 9 };
		System.out.println(smallestDivisor(a1, 6)); // 5
		int[] a2 = { 44, 22, 33, 11, 1 };
		System.out.println(smallestDivisor(a2, 5)); // 44
	}
}
