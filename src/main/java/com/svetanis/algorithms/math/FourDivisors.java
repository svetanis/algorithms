package com.svetanis.algorithms.math;

// 1390. Four Divisors

public final class FourDivisors {
	// Time Complexity: O(n * sqrt(max))
	// Space Complexity: O(1)

	public static int sumFourDivisors(int[] a) {
		int total = 0;
		for (int num : a) {
			total += sumIf4Divisors(num);
		}
		return total;
	}

	private static int sumIf4Divisors(int num) {
		int sum = num + 1;
		int count = 2;
		for (int i = 2; i <= num / i; i++) {
			if (num % i == 0) {
				count += 1;
				sum += i;
				if (i * i != num) {
					count += 1;
					sum += num / i;
				}
			}
		}
		return count == 4 ? sum : 0;
	}

	public static void main(String[] args) {
		int[] a1 = { 21, 4, 7 };
		System.out.println(sumFourDivisors(a1)); // 32

		int[] a2 = { 21, 21 };
		System.out.println(sumFourDivisors(a2)); // 64

		int[] a3 = { 1, 2, 3, 4, 5 };
		System.out.println(sumFourDivisors(a3)); // 0
	}
}