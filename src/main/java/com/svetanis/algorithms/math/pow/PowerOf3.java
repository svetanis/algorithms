package com.svetanis.algorithms.math.pow;

// 326. Power of Three

public final class PowerOf3 {

	public static boolean isPowerOfThree(int n) {
		if (n <= 0) {
			return false;
		}
		while (n % 3 == 0) {
			n /= 3;
		}
		return n == 1;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOfThree(27)); // true
		System.out.println(isPowerOfThree(0)); // false
		System.out.println(isPowerOfThree(-1)); // false
	}
}