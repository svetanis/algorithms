package com.svetanis.algorithms.math.prime;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.Arrays.fill;

import com.google.common.base.Optional;

// compute the n-th prime number

public final class NthPrime {

	public static Optional<Integer> nthPrime(int n) {
		boolean[] primes = new boolean[100001];
		fill(primes, true);
		primes[0] = primes[1] = false;
		int count = 0;
		for (int i = 2; count <= 100000; i++) {
			if (primes[i]) {
				count++;
				if (count == n) {
					return of(i);
				}
				// mark the multiple of i as non-prime number
				for (int j = i + i; j <= 100000; j += i) {
					primes[j] = false;
				}
			}
		}
		return absent();
	}

	public static void main(String[] args) {
		System.out.println(nthPrime(3)); // 5
		System.out.println(nthPrime(5)); // 11
		System.out.println(nthPrime(100)); // 541
		System.out.println(nthPrime(135)); // 761
		System.out.println(nthPrime(2021)); // 17579
	}
}
