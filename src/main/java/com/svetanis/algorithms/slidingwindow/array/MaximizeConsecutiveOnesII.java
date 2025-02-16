package com.svetanis.algorithms.slidingwindow.array;

// 487. Max Consecutive Ones II

public final class MaximizeConsecutiveOnesII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sw(int[] a) {
		int max = 0;
		int left = 0;
		int zeros = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 0) {
				zeros++;
			}
			while (zeros > 1) {
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

	public static int subStrLen(int[] a) {
		int left = 0;
		int right = 0;
		int zerosAllowed = 1;
		while (right < a.length) {
			if (a[right++] == 0) {
				zerosAllowed--;
			}
			if (zerosAllowed < 0 && a[left++] == 0) {
				zerosAllowed++;
			}
		}
		return right - left;
	}

	public static void main(String args[]) {
		int[] a1 = { 1, 0, 1, 1, 0 };
		System.out.println(sw(a1)); // 4
		System.out.println(subStrLen(a1)); // 4
	}
}