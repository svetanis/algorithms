package com.svetanis.algorithms.search.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// 1675. Minimize Deviation in Array

public final class MinimizeDeviation {
	// Time Complexity: O(n log n)

	public static int minDeviation(int[] a) {
		int min = Integer.MAX_VALUE;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for (int num : a) {
			num = num % 2 == 1 ? num * 2 : num;
			pq.offer(num);
			min = Math.min(min, num);
		}
		int deviation = pq.peek() - min;
		while (!pq.isEmpty() && pq.peek() % 2 == 0) {
			int top = pq.poll() / 2;
			pq.offer(top);
			min = Math.min(min, top);
			deviation = Math.min(deviation, pq.peek() - min);
		}
		return deviation;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(minDeviation(a1)); // 1
		int[] a2 = { 4, 1, 5, 20, 3 };
		System.out.println(minDeviation(a2)); // 3
		int[] a3 = { 2, 10, 8 };
		System.out.println(minDeviation(a3)); // 3
	}
}
