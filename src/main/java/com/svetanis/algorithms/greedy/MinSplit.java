package com.svetanis.algorithms.greedy;

// 2436. Minimum Split Into Subarrays With GCD Greater Than One

public final class MinSplit {
	// Time Complexity: O(n log m)
	// Space Complexity: O(1)

	public static int minSplits(int[] a) {
		int count = 1;
		int gcd = 0;
		for (int num : a) {
			gcd = gcd(gcd, num);
			if (gcd == 1) {
				count += 1;
				gcd = num;
			}
		}
		return count;
	}

	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		int[] a = { 12, 6, 3, 14, 8 };
		System.out.println(minSplits(a)); // 2
	}
}
