package com.svetanis.algorithms.math.operations;

import static java.lang.Integer.parseInt;

// 640. Solve the Equation

// Each side collapses to (number of x's, constant): "x+5-3+x" is 2x + 2.
// Then leftX * x + leftConstant = rightX * x + rightConstant, so
// x = (rightConstant - leftConstant) / (leftX - rightX).

public final class SolveEquation {
	// Time Complexity: O(n)
	// Space Complexity: O(n) for the split strings

	public static String solve(String equation) {
		String[] parts = equation.split("=");
		int[] left = parse(parts[0]);
		int[] right = parse(parts[1]);
		int leftX = left[0];
		int leftConstant = left[1];
		int rightX = right[0];
		int rightConstant = right[1];
		// the x's cancel: either every x works, or none does
		if (leftX == rightX) {
			return leftConstant == rightConstant ? "Infinite solutions" : "No solution";
		}
		int numerator = rightConstant - leftConstant;
		int denominator = leftX - rightX;
		// exact: the problem promises a whole-number answer when there is one
		return "x=" + numerator / denominator;
	}

	// { number of x's, constant } for one side
	private static int[] parse(String s) {
		int xCount = 0;
		int constant = 0;
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
			String term = s.substring(start, i);
			if (s.charAt(i - 1) == 'x') {
				int n = term.length();
				// "x" alone means 1x; "3x" and "0x" carry their number
				xCount += sign * (n > 1 ? parseInt(term.substring(0, n - 1)) : 1);
			} else {
				constant += sign * parseInt(term);
			}
		}
		return new int[] { xCount, constant };
	}

	public static void main(String[] args) {
		System.out.println(solve("x+5-3+x=6+x-2")); // x=2
		System.out.println(solve("x=x")); // Infinite solutions
		System.out.println(solve("2x=x")); // x=0
		System.out.println(solve("x=x+2")); // No solution
	}
}
