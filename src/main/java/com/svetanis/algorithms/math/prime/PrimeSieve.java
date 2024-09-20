package com.svetanis.algorithms.math.prime;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.fill;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PrimeSieve {

	public static ImmutableList<Integer> sieve(int n) {
		boolean[] primes = new boolean[n + 1];
		fill(primes, true);
		primes[0] = primes[1] = false;

		List<Integer> list = newArrayList();
		for (int i = 2; i < n; ++i) {
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

	public static ImmutableList<Integer> primes(int n) {
		boolean[] primes = new boolean[n + 1];
		fill(primes, true);
		primes[0] = primes[1] = false;
		List<Integer> list = newArrayList();
		for (int i = 2; i < n; ++i) {
			if (primes[i]) {
				list.add(i);
				// mark the multiple of i as non-prime number
				for (int j = i; i * j <= n; ++j) {
					primes[i * j] = false;
				}
			}
		}
		return newList(list);
	}

	public static void main(String[] args) {
		System.out.println(sieve(20));
	}
}
// 2, 3, 5, 7, 11, 13, 17, 19