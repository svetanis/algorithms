package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

public final class StockSpan {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int[] span(int[] price) {
		int n = price.length;
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(0);
		int[] span = new int[n];
		span[0] = 1;
		for (int i = 1; i < n; ++i) {
			while (!stack.isEmpty() && price[stack.peek()] < price[i]) {
				stack.pop();
			}
			// if stack becomes empty, then price[i] is
			// greater than all elements on left of it
			// i.e., price[0], price[1], ..., price[i - 1].
			// else price[i] is greater than elements after top of stack
			if (stack.isEmpty()) {
				span[i] = i + 1;
			} else {
				span[i] = i - stack.peek();
			}
			// push this element to stack
			stack.push(i);
		}
		return span;
	}

	public static void main(String[] args) {
		int[] price = { 10, 4, 5, 90, 120, 80 };
		Print.print(span(price)); // 1 1 2 4 5 1
	}
}