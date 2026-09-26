package com.svetanis.algorithms.math;

// 1250. Check If It Is a Good Array

// Some multiples of the numbers add up to 1 exactly when the numbers have
// no common divisor other than 1 (Bezout): gcd of the whole array is 1.

public final class GoodArray {
	// Time Complexity: O(n * log(max))
	// Space Complexity: O(log(max)) for the gcd recursion

	public static boolean isGoodArray(int[] a) {
		int gcd = 0;
		for (int num : a) {
			gcd = gcd(num, gcd);
			if (gcd == 1) {
				return true;
			}
		}
		return gcd == 1;
	}

	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 12, 5, 7, 23 };
		System.out.println(isGoodArray(a1)); // true
		int[] a2 = { 29, 6, 10 };
		System.out.println(isGoodArray(a2)); // true
		int[] a3 = { 3, 6 };
		System.out.println(isGoodArray(a3)); // false
	}
}