package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 2422. Merge Operations to Turn Array Into a Palindrome
//
// Input: an array of positive integers. One operation replaces two ADJACENT numbers
// with their sum.
// Return: the fewest operations that turn the array into a palindrome.
//
// The one idea: the two ends of the finished array must be equal, and only merging
// can change an end. Hold the sum at each end and compare. The smaller sum can only
// grow by absorbing its inner neighbour -- the numbers are positive, so the larger
// sum can never come down to meet it -- so merge on the smaller side, one operation.
// When the two sums are equal they are a matched pair: settle them and move both
// ends inward. Settling at the FIRST moment they are equal is safe: the shortest
// equal-sum front and back leave the most numbers for the middle.
// The two sums are long: one end can absorb most of the array, and with up to 10^5
// numbers of up to 10^6 that is up to 10^11, past int's 2^31 - 1.
//
// Time: O(n) -- every step either absorbs one number or settles two.
// Space: O(1).

public final class MergeOperationsToPalindrome {

	public static int minOperations(int[] nums) {
		int n = nums.length;
		int left = 0;             // START: the two ends
		int right = n - 1;
		long a = nums[left];      // the sum held at the left end
		long b = nums[right];     // the sum held at the right end
		int count = 0;
		while (left < right) {    // STOP: the ends met -- a middle needs no partner
			if (a < b) {
				left += 1;
				a += nums[left];  // MERGE LEFT: the smaller end absorbs its neighbour
				count += 1;
			} else if (b < a) {
				right -= 1;
				b += nums[right]; // MERGE RIGHT: the smaller end absorbs its neighbour
				count += 1;
			} else {
				left += 1;        // SETTLE: equal ends are a matched pair
				right -= 1;
				a = nums[left];   // START the next pair from single numbers
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

		// 3,000 x 1,000,000 | 1 | 3,000 | 3,000 x 999,999: the two outer sums are equal only
		// once every outer number is merged, at 3 * 10^9 -- past int's 2^31 - 1
		int[] a3 = new int[6002];
		Arrays.fill(a3, 0, 3000, 1_000_000);
		a3[3000] = 1;
		a3[3001] = 3_000;
		Arrays.fill(a3, 3002, 6002, 999_999);
		System.out.println(minOperations(a3)); // 5999
	}
}
