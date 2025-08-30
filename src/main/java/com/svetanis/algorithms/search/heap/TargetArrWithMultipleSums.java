package com.svetanis.algorithms.search.heap;

import java.util.Collections;
import java.util.PriorityQueue;

// 1354. Construct Target Array With Multiple Sums

public final class TargetArrWithMultipleSums {
	// Time Complexity: O(m log n)

	public static boolean canConstruct(int[] a) {
		int n = a.length;
		if (n == 1) {
			return a[0] == 1;
		}
		long totalSum = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int num : a) {
			totalSum += num;
			pq.offer((long) num);
		}
		while (pq.peek() > 1) {
			long max = pq.poll();
			long remainingSum = totalSum - max;
			if (remainingSum == 1) {
				return true;
			}
			long value = max % remainingSum;
			if (value == 0 || value == max) {
				return false;
			}
			pq.offer(value);
			totalSum = totalSum - max + value;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 3, 5 };
		System.out.println(canConstruct(a1)); // true
		int[] a2 = { 1, 1, 1, 2 };
		System.out.println(canConstruct(a2)); // false
		int[] a3 = { 8, 5 };
		System.out.println(canConstruct(a3)); // true
	}
}
