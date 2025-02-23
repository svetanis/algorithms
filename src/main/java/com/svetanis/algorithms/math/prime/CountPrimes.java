package com.svetanis.algorithms.math.prime;

import static java.util.Arrays.fill;

// 204. Count Primes

public final class CountPrimes {

	public static int sieve(int n) {
		if(n == 0) {
			return 0;
		}
		boolean[] primes = new boolean[n + 1];
		fill(primes, true);
		primes[0] = primes[1] = false;
		int count = 0;
		for (int i = 2; i < n; ++i) {
			if (primes[i]) {
				count++;
				// mark the multiple of i as non-prime number
				for (int j = i + i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(sieve(10)); // 4
		System.out.println(sieve(0)); // 0
		System.out.println(sieve(1)); // 0
	}
}