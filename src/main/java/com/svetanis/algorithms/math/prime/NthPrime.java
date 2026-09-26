package com.svetanis.algorithms.math.prime;

import java.util.Optional;

import com.google.common.collect.ImmutableList;

// compute the n-th prime number, for primes up to LIMIT

public final class NthPrime {
	// Time complexity: O(1) per call, after one sieve of LIMIT
	// Space complexity: O(LIMIT / log LIMIT) for the primes kept

	private static final int LIMIT = 100000;

	// 2, 3, 5, 7, ... 99991: the 9592 primes up to LIMIT, sieved once
	private static final ImmutableList<Integer> PRIMES = PrimeSieve.sieve(LIMIT);

	// empty when there is no answer to give: n < 1 (there is no 0th
	// prime), or n > 9592 (the n-th prime is past LIMIT)
	public static Optional<Integer> nthPrime(int n) {
		if (n < 1 || n > PRIMES.size()) {
			return Optional.empty();
		}
		return Optional.of(PRIMES.get(n - 1));
	}

	public static void main(String[] args) {
		System.out.println(nthPrime(3)); // Optional[5]
		System.out.println(nthPrime(5)); // Optional[11]
		System.out.println(nthPrime(100)); // Optional[541]
		System.out.println(nthPrime(135)); // Optional[761]
		System.out.println(nthPrime(2021)); // Optional[17579]
		System.out.println(nthPrime(9592)); // Optional[99991]
		System.out.println(nthPrime(9593)); // Optional.empty
		System.out.println(nthPrime(0)); // Optional.empty
	}
}
