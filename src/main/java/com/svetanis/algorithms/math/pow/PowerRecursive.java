package com.svetanis.algorithms.math.pow;

// 50. Pow(x, n)

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

public final class PowerRecursive {
	// Time complexity: O(log n)
	// Space complexity: O(1)

	public static double pow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double temp = pow(x, n / 2);
		if (n % 2 == 0) { // n is even
			return temp * temp;
		} else {
			if (n > 0) {
				return x * temp * temp;
			} else {
				return (temp * temp) / x;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(pow(2.0, 10)); // 1024
		System.out.println(pow(2.1, 3)); // 9.261
		System.out.println(pow(2.0, -2)); // 0.25
		System.out.println(pow(5.0, -3)); // 0.008
	}
}