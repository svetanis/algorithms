package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// 703. Kth Largest in a Stream

public final class KthLargestStreamSubmit {
	// Time complexity: O(log k)
	// Space complexity: O(k)

	private final int k;
	private Queue<Integer> pq;

	public KthLargestStreamSubmit(int k, int[] a) {
		this.k = k;
		this.pq = new PriorityQueue<>((x, y) -> x - y);
		buildPQ(a);
	}

	private void buildPQ(int[] a) {
		for (int element : a) {
			add(element);
		}
	}

	private int add(int num) {
		// add the new number in the min heap
		pq.add(num);
		// if heap has more than k numbers,
		// remove one number
		while (pq.size() > k) {
			pq.poll();
		}
		// return the kth largest number
		return pq.peek();
	}

	public static void main(String[] args) {
		int[] a = { 4, 5, 8, 2 };
		KthLargestStreamSubmit kls = new KthLargestStreamSubmit(3, a);
		System.out.println(kls.add(3)); // 4
		System.out.println(kls.add(5)); // 5
		System.out.println(kls.add(10)); // 5
		System.out.println(kls.add(9)); // 8
		System.out.println(kls.add(4)); // 8

		int[] a1 = { 7, 7, 7, 7, 8, 3 };
		KthLargestStreamSubmit kls2 = new KthLargestStreamSubmit(4, a1);
		System.out.println(kls2.add(2)); // 7
		System.out.println(kls2.add(10)); // 7
		System.out.println(kls2.add(9)); // 7
		System.out.println(kls2.add(9)); // 8
	}
}