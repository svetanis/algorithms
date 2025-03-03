package com.svetanis.algorithms.math;

// 171. Excel Sheet Column Number

public final class ExcelSheetColNumber {

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