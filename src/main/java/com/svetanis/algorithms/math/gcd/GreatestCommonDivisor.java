package com.svetanis.algorithms.math.gcd;

// CSES: Common Divisors

public final class GreatestCommonDivisor {
	// Time Complexity: O(n log n)
	
	private static final int N = 1000001;

	public static long gcd(int[] a) {
		long[] counts = counts(a);
		for (int i = N - 1; i >= 1; i--) {
			long multiples = 0;
			for (int j = i; j < N; j += i) {
				multiples += counts[j];
			}
			if (multiples >= 2) {
				return i;
			}
		}
		return -1;
	}

	private static long[] counts(int[] a) {
		int n = a.length;
		long[] count = new long[N];
		for (int i = 0; i < n; i++) {
			count[a[i]]++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 3, 14, 15, 7, 9 };
		System.out.println(gcd(a)); // 7

		int[] a2 = { 1, 12, 15, 3, 8 };
		System.out.println(gcd(a2)); // 4
	}
}