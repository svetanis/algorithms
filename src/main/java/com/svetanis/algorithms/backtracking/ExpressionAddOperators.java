package com.svetanis.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

// 282. Expression Add Operators

public final class ExpressionAddOperators {
	// Time Complexity: O(3^n)
	// Space Complexity: O(n)

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
		if (index == num.length()) {
			if (total == target) {
				list.add(s);
			}
			return;
		}
		for (int i = index; i < num.length(); i++) {
			if (i != index && num.charAt(index) == '0') {
				break;
			}
			long next = Long.parseLong(num.substring(index, i + 1));
			if (index == 0) {
				dfs(i + 1, next, next, s + next);
			} else {
				// add '+' operator
				dfs(i + 1, next, total + next, s + "+" + next);
				// add '-' operator
				dfs(i + 1, -next, total - next, s + "-" + next);
				// add '*' operator
				dfs(i + 1, prev * next, total - prev + prev * next, s + "*" + next);
			}
		}
	}

	public static void main(String[] args) {
		ExpressionAddOperators eao = new ExpressionAddOperators();
		System.out.println(eao.addOperators("123", 6));
		System.out.println(eao.addOperators("232", 8));
		System.out.println(eao.addOperators("3456237490", 9191));
	}
}