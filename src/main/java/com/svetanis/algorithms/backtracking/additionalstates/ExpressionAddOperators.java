package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 282. Expression Add Operators
//
// Input: num -- a string of digits, target -- an integer. Put "+", "-", "*" or nothing between
// each pair of neighbouring digits (nothing joins them into one number). Returns every resulting
// expression whose value is target, with "*" binding tighter than "+" and "-"; a number may not
// start with 0 unless it is 0 itself. On LeetCode: 1 <= num.length <= 10, num holds digits only,
// -2^31 <= target <= 2^31 - 1.
//
// The one idea: one call decides the next number -- the digits from index to i -- and the
// operator in front of it. total is the value of everything chosen so far and prev is the last
// term of it, with its sign (the number itself, or a product of numbers). After "+" or "-" the
// new total is total plus or minus the number, and the last term is the number, positive or
// negative. After "*" the last term prev must be multiplied, not added to: take it out of total
// and put prev * next back, total - prev + prev * next, and the last term becomes prev * next.
// That one number prev is all the state "*" needs. The first number has no operator in front.
// total and prev are longs because a 10-digit number does not fit an int.
//
// Time: O(4^n * n) -- every gap between digits is either no operator or one of three, so
// 4^(n - 1) expressions in all (counted: 1, 4, 16, ... 16384 for n = 1..8) before the leading-0
// cut, each a string of up to 2n characters built on the way.
// Space: O(n^2) -- the recursion is n deep and each level holds its own copy of the string, up to
// 2n characters, besides the answers.
//
// Siblings: AllExpressionsGivenTarget has no "-", allows a leading 0 in a number and throws past
// 9 digits.

public final class ExpressionAddOperators {

	private int target;
	private String num;
	private List<String> list;

	public List<String> addOperators(String num, int target) {
		this.list = new ArrayList<>();
		this.num = num;
		this.target = target;
		dfs(0, 0, 0, "");
		return list;
	}

	private void dfs(int index, long prev, long total, String s) {
		if (index == num.length()) { // BASE: every digit is used
			if (total == target) { // RECORD: only an expression that reaches the target
				list.add(s);
			}
			return;
		}
		for (int i = index; i < num.length(); i++) { // TAKE: the next number is the digits index..i
			if (i != index && num.charAt(index) == '0') { // CUT: no number longer than 1 digit starts with 0
				break;
			}
			long next = Long.parseLong(num.substring(index, i + 1));
			if (index == 0) { // FIRST number: no operator in front of it
				dfs(i + 1, next, next, s + next);
			} else {
				// add '+' operator
				dfs(i + 1, next, total + next, s + "+" + next); // ADD: a new term
				// add '-' operator
				dfs(i + 1, -next, total - next, s + "-" + next); // SUBTRACT: a new negative term
				// add '*' operator
				dfs(i + 1, prev * next, total - prev + prev * next, s + "*" + next); // MULTIPLY: grow the last term
			}
		}
	}

	public static void main(String[] args) {
		ExpressionAddOperators eao = new ExpressionAddOperators();
		System.out.println(eao.addOperators("123", 6)); // [1+2+3, 1*2*3]
		System.out.println(eao.addOperators("232", 8)); // [2+3*2, 2*3+2]
		System.out.println(eao.addOperators("3456237490", 9191)); // []
		System.out.println(eao.addOperators("105", 5)); // [1*0+5, 10-5]
		System.out.println(eao.addOperators("00", 0)); // [0+0, 0-0, 0*0]
	}
}