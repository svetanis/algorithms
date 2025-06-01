package com.svetanis.algorithms.math.operations;

// 592. Fraction Addition and Subtraction

public final class FractionAdditionSubtraction {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static String fractionAddition(String s) {
		if (Character.isDigit(s.charAt(0))) {
			s = "+" + s;
		}
		int commonDenominator = 6 * 7 * 8 * 9 * 10;
		int total = add(s, commonDenominator);
		int gcd = gcd(Math.abs(total), commonDenominator);
		total /= gcd;
		commonDenominator /= gcd;
		return total + "/" + commonDenominator;
	}

	private static int add(String s, int commonDenominator) {
		int n = s.length();
		int i = 0;
		int total = 0;
		while (i < n) {
			int sign = s.charAt(i) == '-' ? -1 : 1;
			i++;
			int j = i;
			while (j < n && s.charAt(j) != '+' && s.charAt(j) != '-') {
				j++;
			}
			String fraction = s.substring(i, j);
			String[] fragments = fraction.split("/");
			int numerator = Integer.parseInt(fragments[0]);
			int denominator = Integer.parseInt(fragments[1]);
			total += sign * numerator * commonDenominator / denominator;
			i = j;
		}
		return total;
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(fractionAddition("-1/2+1/2")); // 0/1
		System.out.println(fractionAddition("-1/2+1/2+1/3")); // 1/3
		System.out.println(fractionAddition("1/3-1/2")); // -1/6
	}
}