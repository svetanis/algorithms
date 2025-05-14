package com.svetanis.algorithms.search.binary.invariant;

// 878. Nth Magical Number

public final class NthMagicNum {

	private static final int MOD = (int) 1e9 + 7;

	public static int magic(int n, int a, int b) {
		long lower = 0;
		long upper = (long) (a + b) * n;
		int lcm = (a * b) / gcd(a, b);
		while (lower <= upper) {
			long mid = (lower + upper) >>> 1;
			long magic = mid / a + mid / b - mid / lcm;
			if (magic >= n) {
				upper = mid - 1;
			} else {
				lower = mid + 1;
			}
		}
		return (int) (lower % MOD);
	}

	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(magic(1, 2, 3)); // 2
		System.out.println(magic(4, 2, 3)); // 6
	}
}
