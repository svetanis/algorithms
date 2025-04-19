package com.svetanis.algorithms.math;

// 1250. Check If It Is a Good Array

public final class GoodArray {

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