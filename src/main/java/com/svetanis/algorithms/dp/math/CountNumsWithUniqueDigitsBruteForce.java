package com.svetanis.algorithms.dp.math;

// 357. Count Numbers with Unique Digits

public final class CountNumsWithUniqueDigitsBruteForce {
	// Time Complexity: O(10^n*d)

	public static int count(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 10;
		}
		int count = 1;
		for (int i = 1; i <= Math.pow(10, n) - 1; i++) {
			if (hasUniqueDigits(i)) {
				count++;
			}
		}
		return count;
	}

	private static boolean hasUniqueDigits(int n) {
		boolean[] visited = new boolean[10];
		while (n > 0) {
			int digit = n % 10;
			if (visited[digit]) {
				return false;
			}
			visited[digit] = true;
			n /= 10;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 91
		System.out.println(count(0)); // 1
	}
}
