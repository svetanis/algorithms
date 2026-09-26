package com.svetanis.algorithms.math;

// 171. Excel Sheet Column Number

// Base 26 with digits A = 1 .. Z = 26 and no zero: read left to right,
// multiplying by 26 before each new letter, as with decimal digits.

public final class ExcelSheetColNumber {
	// Time Complexity: O(n), n the letters
	// Space Complexity: O(1)

	public static int escNumber(String col) {
		int result = 0;
		for (char c : col.toCharArray()) {
			int val = c - 'A' + 1;
			result = result * 26 + val;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(escNumber("A")); // 1
		System.out.println(escNumber("AB")); // 28
		System.out.println(escNumber("ZY")); // 701
	}
}