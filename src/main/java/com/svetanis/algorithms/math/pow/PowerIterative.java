package com.svetanis.algorithms.math.pow;

// 50. Pow(x, n)

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

public final class PowerIterative {
	// Time complexity: O(log n)
	// Space complexity: O(1)

	public static double pow(double x, int n) {
		if (n >= 0) {
			return power(x, n);
		} else {
			long exponent = -(long) n;
			return 1 / power(x, exponent);
		}
	}

	private static double power(double x, long n) {
		double result = 1;
		while (n > 0) {
			if (n % 2 != 0) { // n is odd
				result = x * result;
			}
			n /= 2;
			x = x * x;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(pow(2.0, 10)); // 1024
		System.out.println(pow(2.1, 3)); // 9.261
		System.out.println(pow(2.0, -2)); // 0.25
		System.out.println(pow(5.0, -3)); // 0.008
	}
}