package com.svetanis.algorithms.dp.math.fib;

// 509. Fibonacci Number

// F(0) = 0, F(1) = 1
// F(n) = F(n - 1) + F(n - 2), for n > 1.

public final class FibonacciRecursive {

	public static int fib(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1 || n == 2) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(fib(8));
	}
}