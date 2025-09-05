package com.svetanis.algorithms.math.gcd;

// CSES: Counting Divisors

public final class CountDivisors {

	private static final int N = 1000005;

	private long[] divisors;

	public CountDivisors() {
		this.divisors = divisors();
	}

	public long countDivisors(int n) {
		return divisors[n];
	}

	private long[] divisors() {
		this.divisors = new long[N];
		for (int i = 1; i < N; i++) {
			for (int j = i; j < N; j += i) {
				divisors[j]++;
			}
		}
		return divisors;
	}

	public static void main(String[] args) {
		CountDivisors cd = new CountDivisors();
		System.out.println(cd.countDivisors(5)); // 2
		System.out.println(cd.countDivisors(6)); // 4
		System.out.println(cd.countDivisors(7)); // 2

		System.out.println(cd.countDivisors(16)); // 5
		System.out.println(cd.countDivisors(17)); // 2
		System.out.println(cd.countDivisors(18)); // 6
	}
}