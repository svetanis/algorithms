package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Stock span: for each day, the number of consecutive days ending that day whose price is
// less than or equal to that day's price.
//
// Input: an array of prices, one per day. Returns: an array of the same length whose cell i is
// day i's span, at least 1 because the day counts itself.
//
// The span ends just after the nearest earlier day with a HIGHER price, so this is the
// previous-greater search read at push time. Equal prices are inside the span, which is why
// the pop condition is '<=': an earlier day at the same price must be popped too.
// OnlineStockSpan901 is the same idea for prices arriving one at a time.
// OnlineStockSpan901PrevGreater is this exact subtraction on a stream, where each stack entry
// carries its own price and day number because there is no array to look them up in.

public final class StockSpan {
	// Time Complexity: O(n), every day is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] span(int[] prices) {
		int n = prices.length;
		int[] span = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // STACK: days, prices strictly falling to the top
		for (int i = 0; i < n; i++) { // WALK: day i arrives
			while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) { // POP: equal prices count too
				stack.pop(); // DISCARD: inside today's span
			}
			// the day left on top is the nearest higher price; with none, the span reaches day 0
			span[i] = stack.isEmpty() ? i + 1 : i - stack.peek(); // ANSWER: back to the higher day, or day 0
			stack.push(i); // PUSH: it might stop a later day's count
		}
		return span; // RETURN
	}

	public static void main(String[] args) {
		int[] prices = { 10, 4, 5, 90, 120, 80 };
		System.out.println(Arrays.toString(span(prices))); // 1 1 2 4 5 1

		int[] flat = { 1, 1, 1 };
		System.out.println(Arrays.toString(span(flat))); // 1 2 3, equal prices are inside the span

		int[] empty = {};
		System.out.println(Arrays.toString(span(empty))); // [], nothing
	}
}
