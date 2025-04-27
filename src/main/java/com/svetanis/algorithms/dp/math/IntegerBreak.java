package com.svetanis.algorithms.dp.math;

// 343. Integer Break

public final class IntegerBreak {
	// Time Complexity: O(1)

	public static int integerBreak(int n) {
		if (n < 4) {
			return n - 1;
		}
		int remainder = n % 3;
		if (remainder == 0) {
			return (int) Math.pow(3, n / 3);
		}
		if (remainder == 1) {
			return (int) Math.pow(3, (n / 3) - 1) * 4;
		}
		return (int) Math.pow(3, n / 3) * 2;
	}

	public static void main(String[] args) {
		System.out.println(integerBreak(2)); // 1
		System.out.println(integerBreak(10)); // 36
	}
}
