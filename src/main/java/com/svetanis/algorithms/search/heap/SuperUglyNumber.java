package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 313. Super Ugly Number
//
// A super ugly number is a positive integer whose prime factors all come from the array primes
// (1 counts, it has none). primes is sorted in increasing order. Returns the n-th super ugly
// number; LeetCode guarantees it fits in an int.
//
// Every super ugly number other than 1 is a smaller one times a prime from the list, so a min-heap
// of candidates, polled n times, hands them out in increasing order. To push each number only
// once: from a polled number u, push u * p only for the primes up to and including u's smallest
// prime factor -- the loop stops at the first prime that divides u. Then x is reached only from
// x / (x's smallest prime factor), so the heap never holds a duplicate. A product past
// Integer.MAX_VALUE is skipped: it is larger than the answer, so it could never be polled before it.
//
// Time: O(n k log(n k)), k = primes.length -- n polls, each pushing at most k candidates
// Space: O(n k) for the heap
//
// Siblings, the ugly numbers:
//   IsUglyNumber (263) -- is one number ugly (primes 2, 3, 5)
//   UglyNumber (264)   -- the n-th ugly number for primes 2, 3, 5: three pointers, or a heap with a
//                         set to drop the duplicates this file never makes

public final class SuperUglyNumber {

	public static int sun(int n, int[] primes) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // MIN-HEAP: the candidates, the smallest on top
		pq.offer(1);                                       // START: 1 is the first super ugly number
		int ugly = 0;
		while (n > 0) {
			ugly = pq.poll();                              // POLL: the next super ugly number in order
			for (int prime : primes) {
				if (prime <= Integer.MAX_VALUE / ugly) {
					pq.offer(prime * ugly);                // PUSH: a larger candidate, when it fits in an int
				}
				if (ugly % prime == 0) {
					break;                                 // STOP: prime is ugly's smallest prime factor; larger primes would make duplicates
				}
			}
			n--;
		}
		return ugly;                                       // REPORT: the n-th poll
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