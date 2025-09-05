package com.svetanis.algorithms.math.gcd;

// 2413. Smallest Even Multiple

public final class SmallestEvenMultiple {

	public static int sem(int n) {
		return n % 2 == 0 ? n : n * 2;
	}

	public static void main(String[] args) {
		System.out.println(sem(5)); // 10
		System.out.println(sem(6)); // 6
	}
}