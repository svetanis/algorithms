package com.svetanis.algorithms.math;

// 172. Factorial Trailing Zeroes

public final class TrailingZeros172 {
	// Time Complexity: O(log5(n))
	// Space Complexity: O(1)

	public static int trailingZeros(int n) {
		int count = 0;
		while (n > 0) {
			n /= 5;
			count += n;
		}
		return count;
	}

	public static int dfs(int n) {
		if (n == 0) {
			return 0;
		}
		return n / 5 + dfs(n / 5);
	}

	public static void main(String[] args) {
		System.out.println(trailingZeros(3)); // 0
		System.out.println(trailingZeros(5)); // 1
		System.out.println(trailingZeros(0)); // 0

		System.out.println(dfs(3)); // 0
		System.out.println(dfs(5)); // 1
		System.out.println(dfs(0)); // 0
	}
}