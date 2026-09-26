package com.svetanis.algorithms.math.gcd;

// Euclid's algorithm: the greatest common divisor of two integers,
// the largest number that divides both of them with nothing left over.
//
// Why it works: a number that divides both a and b also divides a - b.
// 3 divides 18 (six 3s) and 12 (four 3s), so it divides 18 - 12 (two 3s).
// So (12, 18) and (12, 6) have exactly the same common divisors: 1, 2, 3, 6.
// Subtracting the smaller number shrinks the pair and keeps the answer.
//
// a % b is that subtraction repeated as many times as it fits:
// 100 -> 88 -> 76 -> ... -> 4 is 100 % 12 = 4, so gcd(12, 100) = gcd(12, 4).
// Repeat until one number is 0; every number divides 0, so gcd(a, 0) = a.
//
//   gcd(12, 100) -> gcd(100, 12) -> gcd(12, 4) -> gcd(4, 0) = 4

public final class EuclidGcd {
	// Time Complexity: O(log(min(a, b)))
	// Space Complexity: O(log(min(a, b))) for the recursion

	public static int gcd(int a, int b) {
		// -4 has the same divisors as 4, but Java's % keeps the sign
		// of the left-hand number, so gcd(4, -6) would come out -2.
		// The flips sit above the stop test so that gcd(-5, 0) is 5.
		// Integer.MIN_VALUE has no positive int: gcd(MIN_VALUE, 0) is 2^31,
		// which int cannot hold, and it comes back negative.
		if (a < 0) {
			a = -a;
		}
		if (b < 0) {
			b = -b;
		}
		if (b == 0) {
			return a;
		}
		// no need to put the bigger number first: when a < b,
		// a % b is a itself, so gcd(12, 100) calls gcd(100, 12)
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(gcd(12, 18)); // 6
		System.out.println(gcd(12, 100)); // 4
		System.out.println(gcd(100, 12)); // 4
		System.out.println(gcd(7, 0)); // 7
		System.out.println(gcd(4, -6)); // 2
		System.out.println(gcd(-5, 0)); // 5
	}
}
