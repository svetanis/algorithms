package com.svetanis.algorithms.dp.math;

// 357. Count Numbers with Unique Digits

public final class CountNumsWithUniqueDigits {
	// Time Complexity: O(n)

	public static int count(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 10;
		}
		int count = 10;
		int unique = 9;
		for (int i = 0; i < n - 1; i++) {
			unique *= (9 - i);
			count += unique;
		}
		return count;
	}

	public static int count2(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 10;
		}
		int count = 10;
		int unique = 9;
		int available = 9;
		while (n-- > 1 && available > 0) {
			unique *= available;
			count += unique;
			available--;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 91
		System.out.println(count(0)); // 1

		System.out.println(count2(2)); // 91
		System.out.println(count2(0)); // 1
	}
}
