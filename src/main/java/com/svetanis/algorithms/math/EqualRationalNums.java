package com.svetanis.algorithms.math;

// 972. Equal Rational Numbers

// Turn each string into a double, writing the repeating part out 20 times,
// and call them equal when they are within 1e-9.
//
// WRONG on some inputs, and kept as it is: two different values can be
// closer than any tolerance a double can check. "0.3804(5645)" and
// "0.380(456)" are 8.1e-10 apart and this says they are equal. The exact
// version, comparing them as fractions, is EqualRationalNumsExact.

public final class EqualRationalNums {
	// Time Complexity: O(1) -- every part is at most 4 digits
	// Space Complexity: O(1)

	private static final double DIFF = 1E-9;

	public static boolean isRationalEqual(String s, String t) {
		return Math.abs(value(s) - value(t)) < DIFF;
	}

	private static double value(String s) {
		int n = s.length();
		int index = s.indexOf('(');
		if (index > 0) {
			String base = s.substring(0, index);
			String repeted = s.substring(index + 1, n - 1);
			for (int i = 0; i < 20; i++) {
				base += repeted;
			}
			return Double.valueOf(base);
		}
		return Double.valueOf(s);
	}

	public static void main(String[] args) {
		System.out.println(isRationalEqual("0.(52)", "0.5(25)")); // true
		System.out.println(isRationalEqual("0.1666(6)", "0.166(66)")); // true
		System.out.println(isRationalEqual("0.9(9)", "1.")); // true
	}
}