package com.svetanis.algorithms.math;

// 537. Complex Number Multiplication

// Apply the formula for multiplication of two complex numbers:
// (a + bi) * (c + di) = ac + adi + bci - bd = (ac - bd) + (ad + bc)i

public final class ComplexNumMultiplication {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	private static final String CNF = "%d+%di";

	public static String cnm(String s1, String s2) {
		String[] a = s1.split("\\+|i");
		String[] b = s2.split("\\+|i");
		int real1 = Integer.parseInt(a[0]);
		int imag1 = Integer.parseInt(a[1]);
		int real2 = Integer.parseInt(b[0]);
		int imag2 = Integer.parseInt(b[1]);
		int real = real1 * real2 - imag1 * imag2;
		int imag = real1 * imag2 + imag1 * real2;
		return String.format(CNF, real, imag);
	}

	public static void main(String[] args) {
		System.out.println(cnm("1+1i", "1+1i")); // 0 + 2i
		System.out.println(cnm("1+-1i", "1+-1i")); // 0 +-2i
	}
}