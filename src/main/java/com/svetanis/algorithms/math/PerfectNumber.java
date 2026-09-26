package com.svetanis.algorithms.math;

// 507. Perfect Number

// Divisors come in pairs, i and n / i, with the smaller one at most
// sqrt(n): 28 = 2 * 14 = 4 * 7. So look only up to sqrt(n) and add both of
// each pair -- once when they are the same, as 6 * 6 = 36. 1 is counted
// from the start, and n itself never is.

public final class PerfectNumber {
	// Time Complexity: O(sqrt(n))
	// Space Complexity: O(1)

	public static boolean perfectNumber(int n) {
		if (n == 1) {
			return false;
		}
		int sum = 1;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				sum += i;
				if (i != n / i) {
					sum += n / i;
				}
			}
		}
		return sum == n;
	}

	public static void main(String[] args) {
		System.out.println(perfectNumber(28)); // true
		System.out.println(perfectNumber(6)); // true
		System.out.println(perfectNumber(7)); // false
		System.out.println(perfectNumber(2016)); // false
	}
}