package com.svetanis.algorithms.math;

// 507. Perfect Number

public final class PerfectNumber {
	// Time Complexity: O(n)

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