package com.svetanis.algorithms.math.prime;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.fill;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Sieve of Eratosthenes: every prime <= n.
// Cross out every multiple of each prime. A number still standing
// when its turn comes has no smaller divisor, so it is prime.

public final class PrimeSieve {
	// Time complexity: O(n log log n)
	// Space complexity: O(n)

	// every walk starts at i + i
	public static ImmutableList<Integer> sieve(int n) {
		if (n < 2) { // no prime is <= 0 or 1
			return ImmutableList.of();
		}
		boolean[] primes = new boolean[n + 1];
		fill(primes, true);
		primes[0] = primes[1] = false;

		List<Integer> list = newArrayList();
		// i <= n: the array is sized n + 1 and marking runs to n, so n
		// itself is meant to be a candidate. contrast CountPrimes (LC 204),
		// which asks for primes strictly BELOW n and is right to use <
		for (int i = 2; i <= n; ++i) {
			if (primes[i]) {
				list.add(i);
				// mark the multiple of i as non-prime number
				for (int j = i + i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		return newList(list);
	}

	// every walk starts at i * i: i * 2 .. i * (i - 1) each hold a
	// smaller prime that crossed them out on an earlier walk
	public static ImmutableList<Integer> sieveFromSquare(int n) {
		if (n < 2) { // no prime is <= 0 or 1
			return ImmutableList.of();
		}
		boolean[] primes = new boolean[n + 1];
		fill(primes, true);
		primes[0] = primes[1] = false;
		List<Integer> list = newArrayList();
		for (int i = 2; i <= n; ++i) {
			if (primes[i]) {
				list.add(i);
				// j <= n / i rather than i * j <= n: for i above 46340,
				// i * i does not fit in an int and wraps negative
				for (int j = i; j <= n / i; ++j) {
					primes[i * j] = false;
				}
			}
		}
		return newList(list);
	}

	public static void main(String[] args) {
		System.out.println(sieve(20)); // [2, 3, 5, 7, 11, 13, 17, 19]
		System.out.println(sieve(19)); // [2, 3, 5, 7, 11, 13, 17, 19]
		System.out.println(sieve(1)); // []
		System.out.println(sieveFromSquare(20)); // [2, 3, 5, 7, 11, 13, 17, 19]
		System.out.println(sieveFromSquare(100000).size()); // 9592
	}
}
