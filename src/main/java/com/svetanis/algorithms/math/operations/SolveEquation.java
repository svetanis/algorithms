package com.svetanis.algorithms.math.operations;

import static java.lang.Integer.parseInt;

// 640. Solve the Equation

public final class SolveEquation {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static String solve(String equation) {
		String[] parts = equation.split("=");
		int[] left = parse(parts[0]);
		int[] right = parse(parts[1]);
		int lxc = left[0];
		int lc = left[1];
		int rxc = right[0];
		int rc = right[1];
		if (lxc == rxc) {
			return lc == rc ? "Infinite solutions" : "No solution";
		}
		int numerator = rc - lc;
		int denominator = lxc - rxc;
		return "x=" + numerator / denominator;
	}

	private static int[] parse(String s) {
		int cff = 0;
		int cnt = 0;
		if (s.charAt(0) != '-') {
			s = "+" + s;
		}
		int i = 0;
		int len = s.length();
		while (i < len) {
			int sign = s.charAt(i) == '+' ? 1 : -1;
			i++;
			int start = i;
			while (i < len && s.charAt(i) != '+' && s.charAt(i) != '-') {
				i++;
			}
			String val = s.substring(start, i);
			if (s.charAt(i - 1) == 'x') {
				int n = val.length();
				cff += sign * (n > 1 ? parseInt(val.substring(0, n - 1)) : 1);
			} else {
				cnt += sign * parseInt(val);
			}
		}
		return new int[] { cff, cnt };
	}

	public static void main(String[] args) {
		System.out.println(solve("x+5-3+x=6+x-2")); // x = 2
		System.out.println(solve("x=x")); // infinite
		System.out.println(solve("2x=x")); // x = 0
	}
}