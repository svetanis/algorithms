package com.svetanis.algorithms.math;

// 168. Excel Sheet Column Title

// Base 26 with no zero digit: Z is 26, and 27 is AA, not "A0". Taking 1
// off before each % 26 turns the digits 1..26 into 0..25, which % 26 and
// / 26 then handle like any base.

public final class ExcelSheetColTitle {
	// Time Complexity: O(log n), one letter per pass
	// Space Complexity: O(log n) for the title

	public static String escTitle(int col) {
		StringBuilder sb = new StringBuilder();
		while (col > 0) {
			col--;
			char c = (char) ('A' + col % 26);
			sb.append(c);
			col /= 26;
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(escTitle(1)); // A
		System.out.println(escTitle(28)); // AB
		System.out.println(escTitle(701)); // ZY
	}
}