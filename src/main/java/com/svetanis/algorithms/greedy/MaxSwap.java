package com.svetanis.algorithms.greedy;

import static java.lang.Integer.parseInt;

// 670. Maximum Swap

public final class MaxSwap {
	// Time Complexity: O(n)

	public static int maxSwap(int num) {
		char[] digits = String.valueOf(num).toCharArray();
		int[] largest = largest(digits);
		for (int i = 0; i < digits.length; i++) {
			int index = largest[i];
			if (digits[i] < digits[index]) {
				swap(digits, i, index);
				break;
			}
		}
		return parseInt(new String(digits));
	}

	public static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	// index of the largest digit to the right of i
	private static int[] largest(char[] digits) {
		int n = digits.length;
		int[] a = new int[n];
		int max = n - 1;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] > digits[max]) {
				max = i;
			}
			a[i] = max;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(maxSwap(2736)); // 7236
		System.out.println(maxSwap(9973)); // 9973
	}
}
