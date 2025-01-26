package com.svetanis.algorithms.math;

// 7. Reverse Integer

public final class ReverseInteger {
	// Time Complexity: O(log(n))
	// Space Complexity: O(1)

	public static int reverse(int num) {
		int reverse = 0;
		int min = Integer.MIN_VALUE / 10;
		int max = Integer.MAX_VALUE / 10;
		while (num != 0) {
			if (reverse < min || reverse > max) {
				return 0;
			}
			int remainder = num % 10;
			reverse = reverse * 10 + remainder;
			num = num / 10;
		}
		return reverse;
	}

	public static void main(String[] args) {
		System.out.println(reverse(123)); // 321
		System.out.println(reverse(-123)); // -321
		System.out.println(reverse(120)); // 21
	}
}