package com.svetanis.algorithms.math.pow;

public final class IsPowerOfTwo {

	public static boolean isPowerOf2(int n) {
		return n != 0 && (n & (n - 1)) == 0;
	}

	public static boolean isPowerOfTwo(int n) {
		if (n == 0) {
			return false;
		}
		while (n != 1) {
			if (n % 2 == 1) {
				return false;
			}
			n = n / 2;
		}
		return true;
	}

	public static boolean isPowerOfTwoCountBits(int n) {
		int setBits = count(n);
		return setBits == 1 ? true : false;
	}

	private static int count(int n) {
		int count = 0;
		while (n != 0) {
			count += (n & 1);
			n = n >> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOf2(32)); // true
		System.out.println(isPowerOfTwo(32)); // true
		System.out.println(isPowerOfTwoCountBits(32)); // true
		System.out.println(isPowerOf2(7)); // false
		System.out.println(isPowerOfTwo(7)); // false
		System.out.println(isPowerOfTwoCountBits(7)); // false
	}
}