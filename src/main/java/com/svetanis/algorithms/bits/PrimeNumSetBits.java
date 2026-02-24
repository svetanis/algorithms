package com.svetanis.algorithms.bits;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 762. Prime Number of Set Bits in Binary Representation

public final class PrimeNumSetBits {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	private static final Set<Integer> PRIMES = Set.of(2, 3, 5, 7, 11, 13, 17, 19);

	public static int countPrimeSetBits(int left, int right) {
		int count = 0;
		for (int num = left; num <= right; num++) {
			int bits = Integer.bitCount(num);
			if (PRIMES.contains(bits)) {
				count += 1;
			}
		}
		return count;
	}

	public static Set<Integer> primes(int n) {
		boolean[] primes = new boolean[n + 1];
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		Set<Integer> set = new HashSet<>();
		for (int i = 2; i < n; ++i) {
			if (primes[i]) {
				set.add(i);
				// mark the multiple of i as non-prime number
				for (int j = i; i * j <= n; ++j) {
					primes[i * j] = false;
				}
			}
		}
		return set;
	}

	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(countPrimeSetBits(6, 10)); // 4
		System.out.println(countPrimeSetBits(10, 15)); // 5
	}
}
