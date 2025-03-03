package com.svetanis.algorithms.math;

// 168. Excel Sheet Column Title

public final class ExcelSheetColTitle {

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