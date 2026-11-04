package com.svetanis.algorithms.bits.popcount;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 762. Prime Number of Set Bits in Binary Representation
//
// Input: two ints left and right. Returns how many numbers from left to right, both included, have
// a prime number of 1s in binary. On LeetCode: 1 <= left <= right <= 10^6, 0 <= right - left <= 10^4.
//
// The one idea: right <= 10^6 < 2^20, so every number in the range has at most 20 binary digits and
// so at most 20 ones. The primes a count of 1s can be are therefore fixed in advance -- 2, 3, 5, 7,
// 11, 13, 17, 19 -- and kept in a set. Walk the range, count each number's 1s with Integer.bitCount,
// and look the count up in the set.
//
// primes(n) and isPrime(num) are two ways to build such a set for a wider range; countPrimeSetBits
// calls neither. primes(n) is the sieve of Eratosthenes: start with every number marked prime, and
// for each number still marked, cross out its multiples from its square up -- a smaller multiple
// i * k with k < i was already crossed out as a multiple of k's smallest factor. isPrime tries every
// divisor up to the square root: if num = d * e with d <= e, then d * d <= num. Both loop tests
// divide (j <= n / i, i <= num / i), so no product is formed that could pass 2^31 - 1.
//
// Time: countPrimeSetBits O(right - left) -- one bitCount and one set lookup per number;
// primes O(n log log n) -- each prime p crosses out at most n / p numbers; isPrime O(sqrt(num)).
// Space: countPrimeSetBits O(1) -- the set holds eight numbers; primes O(n) -- the marks and the set.

public final class PrimeNumSetBits {
	// Time Complexity: O(right - left)
	// Space Complexity: O(1)

	// right <= 10^6 < 2^20, so a count of 1s is at most 20: these are all the primes it can be
	private static final Set<Integer> PRIMES = Set.of(2, 3, 5, 7, 11, 13, 17, 19);

	public static int countPrimeSetBits(int left, int right) {
		int count = 0;
		for (int num = left; num <= right; num++) { // WALK the range
			int bits = Integer.bitCount(num); // COUNT the 1s
			if (PRIMES.contains(bits)) { // TEST: a prime count
				count += 1;
			}
		}
		return count;
	}

	public static Set<Integer> primes(int n) {
		boolean[] primes = new boolean[n + 1]; // MARK: primes[v] -- v is not yet crossed out
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false; // BASE: 0 and 1 are not prime
		Set<Integer> set = new HashSet<>();
		for (int i = 2; i <= n; ++i) { // WALK: every number up to n, n included
			if (primes[i]) { // PRIME: no smaller prime crossed it out
				set.add(i);
				// mark the multiples of i from i * i: smaller ones have a smaller factor
				for (int j = i; j <= n / i; ++j) { // CROSS OUT i * j; j <= n / i keeps i * j <= n without overflow
					primes[i * j] = false;
				}
			}
		}
		return set;
	}

	public static boolean isPrime(int num) {
		if (num <= 1) { // BASE: 0, 1 and negatives are not prime
			return false;
		}
		for (int i = 2; i <= num / i; i++) { // TRY divisors while i * i <= num, written without overflow
			if (num % i == 0) { // FOUND a divisor
				return false;
			}
		}
		return true; // RETURN: no divisor up to the square root
	}

	public static void main(String[] args) {
		System.out.println(countPrimeSetBits(6, 10)); // 4
		System.out.println(countPrimeSetBits(10, 15)); // 5
	}
}
