package com.svetanis.algorithms.math.pow;

// 50. Pow(x, n)

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

// Negative n is never flipped to positive, so n = Integer.MIN_VALUE
// cannot overflow. It works because Java's / and % round toward zero:
// -5 / 2 = -2 and -5 % 2 = -1, so a negative n halves exactly like a
// positive one, and on an odd step the extra factor is 1/x instead of x.
//   x^-5 = (x^-2)^2 / x

public final class PowerRecursive {
	// Time complexity: O(log n)
	// Space complexity: O(log n) for the recursion

	public static double pow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		// computed once: calling pow(x, n / 2) twice makes O(n) calls
		double temp = pow(x, n / 2);
		if (n % 2 == 0) { // n is even
			return temp * temp;
		} else {
			if (n > 0) {
				return x * temp * temp;
			} else { // n % 2 is -1 here, not 1
				return (temp * temp) / x;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(pow(2.0, 10)); // 1024
		System.out.println(pow(2.1, 3)); // 9.261
		System.out.println(pow(2.0, -2)); // 0.25
		System.out.println(pow(5.0, -3)); // 0.008
		System.out.println(pow(2.0, Integer.MIN_VALUE)); // 0.0
	}
}
