package com.svetanis.algorithms.math.prime;

import static java.util.Arrays.fill;

// 204. Count Primes

// Sieve of Eratosthenes: instead of testing each number, cross out
// every multiple of each prime. A number still standing when its turn
// comes has no smaller divisor, so it is prime.

public final class CountPrimes {
	// Time complexity: O(n log log n)
	// Space complexity: O(n)

	// primes[k] true = k is prime. Every walk starts at i + i and the
	// outer loop visits every i, counting each prime as it is reached.
	public static int sieve(int n) {
		if (n < 3) { // no prime is below 0, 1 or 2
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

	// crossed[k] true = some smaller prime divides k. Walks start at
	// i * i and stop once i * i >= n; the count is a second pass.
	public static int sieveFromSquare(int n) {
		if (n < 3) { // no prime is below 0, 1 or 2
			return 0;
		}
		boolean[] crossed = new boolean[n];
		// past sqrt(n) a prime's first multiple left to cross, i * i,
		// is already past n: every number still standing is prime
		for (int i = 2; i * i < n; i++) {
			if (crossed[i]) { // not prime: its multiples are crossed already
				continue;
			}
			// i * 2 .. i * (i - 1) each hold a smaller prime that
			// crossed them out on an earlier walk
			for (int j = i * i; j < n; j += i) {
				crossed[j] = true;
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) { // from 2: 0 and 1 are never crossed
			if (!crossed[i]) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(sieve(10)); // 4
		System.out.println(sieve(0)); // 0
		System.out.println(sieve(1)); // 0

		System.out.println(sieveFromSquare(10)); // 4
		System.out.println(sieveFromSquare(0)); // 0
		System.out.println(sieveFromSquare(1)); // 0
		System.out.println(sieveFromSquare(5000000)); // 348513
	}
}
