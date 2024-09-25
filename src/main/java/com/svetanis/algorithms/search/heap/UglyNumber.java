package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// find the n-th ugly number
// ugly numbers are positive numbers
// whose prime factors only include
// 2, 3, 5

public final class UglyNumber {

	public static int nthUgly(int n) {
		// Time Complexity: O(n log n)

		Set<Integer> set = new HashSet<>(1);
		List<Integer> factors = asList(2, 3, 5);
		Queue<Integer> pq = new PriorityQueue<>();
		pq.add(1);
		for (int i = 0; i < n - 1; i++) {
			int curr = pq.poll();
			for (int factor : factors) {
				int next = curr * factor;
				if (set.add(next)) {
					pq.add(next);
				}
			}
		}
		return pq.poll();
	}

	public static int ugly(int n) {
		// Time Complexity: O(n log n)

		Set<Integer> set = new HashSet<>(1);
		List<Integer> factors = asList(2, 3, 5);
		Queue<Integer> pq = new PriorityQueue<>();
		pq.add(1);
		int count = 0;
		while (!pq.isEmpty()) {
			int curr = pq.poll();
			count += 1;
			if (count == n) {
				return curr;
			}
			for (int factor : factors) {
				int next = curr * factor;
				if (set.add(next)) {
					pq.add(next);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(ugly(10)); // 12
		System.out.println(nthUgly(10)); // 12
	}
}