package com.svetanis.algorithms.math.operations;

// 43. Multiply Strings

public final class MultiplyStrings {
	// Time Complexity: O(m * n)
	// Space Complexity: O(m + n)

	public static String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		int[] product = product(num1, num2);
		for (int i = product.length - 1; i > 0; i--) {
			product[i - 1] += product[i] / 10; // carry
			product[i] %= 10;
		}
		return concat(product);
	}

	private static String concat(int[] product) {
		int start = product[0] == 0 ? 1 : 0;
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < product.length; i++) {
			sb.append(product[i]);
		}
		return sb.toString();
	}

	private static int[] product(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		int[] product = new int[len1 + len2];
		for (int i = len1 - 1; i >= 0; i--) {
			int d1 = s1.charAt(i) - '0';
			for (int j = len2 - 1; j >= 0; j--) {
				int d2 = s2.charAt(j) - '0';
				product[i + j + 1] += d1 * d2;
			}
		}
		return product;
	}

	public static void main(String[] args) {
		System.out.println(multiply("2", "3")); // 6
		System.out.println(multiply("123", "456")); // 56088
	}
}