package com.svetanis.algorithms.dp.math.fib;

// 1137. N-th Tribonacci Number

public final class TribonacciTopDown {

	public static int trib(int n) {
		Integer[] dp = new Integer[n + 1];
		return trib(n, dp);
	}

	private static int trib(int n, Integer[] dp) {
		if (n == 0) {
			return 0;
		} else if (n == 1 || n == 2) {
			return 1;
		}
		if (dp[n] != null) {
			return dp[n];
		}
		int first = trib(n - 1, dp);
		int second = trib(n - 2, dp);
		int third = trib(n - 3, dp);
		dp[n] = first + second + third;
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(trib(4)); // 4
		System.out.println(trib(25)); // 1389537
	}
}