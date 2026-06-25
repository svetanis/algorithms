package com.svetanis.algorithms.math;

import java.util.stream.IntStream;

// 2652. Sum Multiples

public final class SumMultiples {

	public static int sumMultiples(int n) {
		int sum = f(n, 3) + f(n, 5) + f(n, 7);
		int intersection = f(n, 15) + f(n, 21) + f(n, 35);
		return sum - intersection + f(n, 105);
	}

	private static int f(int n, int x) {
		int m = n / x;
		return (1 + m) * x * m / 2;
	}

	public static int sumMultiplesBruteForce(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
				sum += i;
			}
		}
		return (int) sum;
	}

	public static int sumMultiplesStream(int n) {
		return IntStream
				.rangeClosed(1, n)
				.filter(i -> i % 3 == 0 || i % 5 == 0 || i % 7 == 0)
				.sum();
	}

	public static void main(String[] args) {
		System.out.println(sumMultiples(7)); // 21
		System.out.println(sumMultiples(10)); // 40
		System.out.println(sumMultiples(9)); // 30
	}
}