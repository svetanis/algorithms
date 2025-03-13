package com.svetanis.algorithms.string;

// 246. Strobogrammatic Number

public final class StrobogrammaticNumber {
	// Time Complexity: O(n)

	private static int[] digits = { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };

	public static boolean isStrobogrammatic(String num) {
		int start = 0;
		int end = num.length() - 1;
		while (start <= end) {
			int left = num.charAt(start) - '0';
			int right = num.charAt(end) - '0';
			if (digits[left] != right) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isStrobogrammatic("69")); // true
	}
}
