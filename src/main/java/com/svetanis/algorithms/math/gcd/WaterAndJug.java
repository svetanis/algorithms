package com.svetanis.algorithms.math.gcd;

// 365. Water and Jug Problem

public final class WaterAndJug {
	// Time Complexity: O(log(min(x,y))
	// Space Complexity: O(1)

	public static boolean canMeasure(int x, int y, int target) {
		if (x + y < target) {
			return false;
		}
		if (x == 0 || y == 0) {
			return target == 0 || target == x + y;
		}
		int gcd = gcd(x, y);
		return target % gcd == 0;
	}

	public static int gcd(int a, int b) {
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
