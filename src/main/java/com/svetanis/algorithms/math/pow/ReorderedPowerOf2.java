package com.svetanis.algorithms.math.pow;

// 869. Reordered Power of 2

public final class ReorderedPowerOf2 {

	public static boolean reorderedPowerOf2(int n) {
		String count = frequency(n);
		for (int i = 1; i <= 1e9; i <<= 1) {
			String f = frequency(i);
			if (count.equals(f)) {
				return true;
			}
		}
		return false;
	}

	private static String frequency(int n) {
		char[] a = new char[10];
		while (n > 0) {
			a[n % 10]++;
			n /= 10;
		}
		return new String(a);
	}

	public static void main(String[] args) {
		System.out.println(reorderedPowerOf2(1)); // true
		System.out.println(reorderedPowerOf2(10)); // false
	}
}