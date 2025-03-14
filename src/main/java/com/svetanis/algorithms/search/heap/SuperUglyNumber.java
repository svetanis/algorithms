package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 313. Super Ugly Number

public final class SuperUglyNumber {
	// Time Complexity: O(n * k * log n)

	public static int sun(int n, int[] primes) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(1);
		int ugly = 0;
		while (n > 0) {
			ugly = pq.poll();
			for (int prime : primes) {
				if (prime <= Integer.MAX_VALUE / ugly) {
					pq.offer(prime * ugly);
				}
				if (ugly % prime == 0) {
					break;
				}
			}
			n--;
		}
		return ugly;
	}

	public static void main(String[] args) {
		int[] primes = { 2, 7, 13, 19 };
		System.out.println(sun(12, primes)); // 32
		int[] primes2 = { 2, 3, 5 };
		System.out.println(sun(1, primes2)); // 1
		int[] primes3 = { 7, 19, 29, 37, 41, 47, 53, 59, 61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139, 157, 167, 179,
				181, 199, 211, 229, 233, 239, 241, 251 };
		System.out.println(sun(100000, primes3)); // 1092889481
	}
}