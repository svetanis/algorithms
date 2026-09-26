package com.svetanis.algorithms.math.pow;

// 50. Pow(x, n)

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

// The loop reads n one binary digit at a time, lowest first, while x
// is squared each step: x, x^2, x^4, x^8, ... Every 1 digit of n
// multiplies its power of x into the result.
//   13 = 1101 in binary, so x^13 = x^8 * x^4 * x^1

public final class PowerIterative {
	// Time complexity: O(log n)
	// Space complexity: O(1)

	public static double pow(double x, int n) {
		if (n >= 0) {
			return power(x, n);
		} else {
			// the cast comes first: -n in int arithmetic overflows
			// for n = Integer.MIN_VALUE, because 2^31 does not fit
			long exponent = -(long) n;
			return 1 / power(x, exponent);
		}
	}

	private static double power(double x, long n) {
		double result = 1;
		while (n > 0) {
			if (n % 2 != 0) { // lowest binary digit of n is 1
				result = x * result;
			}
			n /= 2; // drop that digit
			x = x * x; // x^1 -> x^2 -> x^4 -> ...
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(pow(2.0, 10)); // 1024
		System.out.println(pow(2.1, 3)); // 9.261
		System.out.println(pow(2.0, -2)); // 0.25
		System.out.println(pow(5.0, -3)); // 0.008
		System.out.println(pow(2.0, Integer.MIN_VALUE)); // 0.0
	}
}
