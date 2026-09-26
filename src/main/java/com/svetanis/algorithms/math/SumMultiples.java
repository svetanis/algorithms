package com.svetanis.algorithms.math;

import java.util.stream.IntStream;

// 2652. Sum Multiples

public final class SumMultiples {

	// Add the multiples of 3, of 5 and of 7. A multiple of 15, 21 or 35 was
	// added twice, so take those off once. A multiple of 105 was added three
	// times and taken off three times, so add it back.
	// Time Complexity: O(1)
	// Space Complexity: O(1)
	public static int sumMultiples(int n) {
		int sum = sumOfMultiples(n, 3) + sumOfMultiples(n, 5) + sumOfMultiples(n, 7);
		int intersection = sumOfMultiples(n, 15) + sumOfMultiples(n, 21) + sumOfMultiples(n, 35);
		return sum - intersection + sumOfMultiples(n, 105);
	}

	// x + 2x + ... + mx = x * m(m + 1) / 2, with m = n / x of them
	private static int sumOfMultiples(int n, int x) {
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