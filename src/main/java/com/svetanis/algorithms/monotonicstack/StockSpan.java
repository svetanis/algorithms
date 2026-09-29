package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// Stock span: for each day, the number of consecutive days ending that day whose price is
// less than or equal to that day's price.
//
// The span ends just after the nearest earlier day with a HIGHER price, so this is the
// previous-greater search read at push time. Equal prices are inside the span, which is why
// the pop condition is '<=': an earlier day at the same price must be popped too.
// OnlineStockSpan901 is the same idea for prices arriving one at a time.

public final class StockSpan {
	// Time Complexity: O(n), every day is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] span(int[] prices) {
		int n = prices.length;
		int[] span = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // days, prices strictly falling to the top
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
				stack.pop(); // inside today's span
			}
			// the day left on top is the nearest higher price; with none, the span reaches day 0
			span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
			stack.push(i);
		}
		return span;
	}

	public static void main(String[] args) {
		int[] prices = { 10, 4, 5, 90, 120, 80 };
		Print.print(span(prices)); // 1 1 2 4 5 1

		int[] flat = { 1, 1, 1 };
		Print.print(span(flat)); // 1 2 3, equal prices are inside the span

		int[] empty = {};
		Print.print(span(empty)); // nothing
	}
}
