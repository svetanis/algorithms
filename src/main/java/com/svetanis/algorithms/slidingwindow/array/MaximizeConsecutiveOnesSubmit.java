package com.svetanis.algorithms.slidingwindow.array;

// 1004. Max Consecutive Ones III

// Given an array containing 0s and 1s, 
// if you are allowed to replace no more than ‘k’ 0s with 1s, 
// find the length of the longest subarray having all 1s.

public final class MaximizeConsecutiveOnesSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sw(int[] a, int k) {
		int max = 0;
		int left = 0;
		int zeros = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 0) {
				zeros++;
			}
			while (zeros > k) {
				if (a[left] == 0) {
					zeros--;
				}
				left++;
			}
			int len = right - left + 1;
			max = Math.max(max, len);
		}
		return max;
	}

	public static int subStrLen(int[] a, int k) {
		int max = 0;
		int ones = 0;
		int left = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 1) {
				ones++;
			}
			if (right - left + 1 - ones > k) {
				if (a[left] == 1) {
					ones--;
				}
				left++;
			}
			int len = right - left + 1;
			max = Math.max(max, len);
		}
		return max;
	}

	public static void main(String args[]) {
		int[] a1 = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		System.out.println(sw(a1, 2)); // 6

		int[] a2 = { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
		System.out.println(sw(a2, 3)); // 10
		
    int[] a3 = { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 };
    System.out.println(subStrLen(a3, 2));

    int[] a4 = { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 };
    System.out.println(subStrLen(a4, 3));
	}
}