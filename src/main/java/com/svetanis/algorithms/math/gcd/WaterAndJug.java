package com.svetanis.algorithms.math.gcd;

// 365. Water and Jug Problem

// Every fill, empty or pour changes the total water by x or by y, up or
// down, so any total you can reach is a sum of x's and y's -- and every
// such sum is a multiple of gcd(x, y). The reverse also holds (Bezout):
// every multiple of gcd(x, y) that fits in both jugs together can be
// reached. So the answer is two checks: target <= x + y, and
// target % gcd(x, y) == 0.
//
// jugs 3 and 5: gcd 1, so every target from 0 to 8 works
// jugs 2 and 6: gcd 2, so 5 never works

public final class WaterAndJug {
	// Time Complexity: O(log(min(x, y)))
	// Space Complexity: O(log(min(x, y))) for the gcd recursion

	public static boolean canMeasure(int x, int y, int target) {
		// more than both jugs hold together
		if (x + y < target) {
			return false;
		}
		// a jug of size 0 was allowed in an older version of the problem.
		// One empty jug is fine for the gcd line (gcd(0, 5) is 5), but both
		// at 0 make the gcd 0, and target % 0 throws
		if (x == 0 || y == 0) {
			return target == 0 || target == x + y;
		}
		int gcd = gcd(x, y);
		return target % gcd == 0;
	}

	private static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

	public static void main(String[] args) {
		System.out.println(canMeasure(3, 5, 4)); // true
		System.out.println(canMeasure(2, 6, 5)); // false
		System.out.println(canMeasure(1, 2, 3)); // true
	}
}
