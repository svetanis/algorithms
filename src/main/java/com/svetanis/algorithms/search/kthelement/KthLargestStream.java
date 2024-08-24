package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// find kth largest element in a stream of numbers

public final class KthLargestStream {
	// Time complexity: O(log k)
	// Space complexity: O(k)

	private final int k;
	private Queue<Integer> pq;

	public KthLargestStream(int[] a, int k) {
		this.k = k;
		this.pq = new PriorityQueue<>((x, y) -> x - y);
		buildPQ(a);
	}

	private void buildPQ(int[] a) {
		for (int i = 0; i < a.length; i++) {
			add(a[i]);
		}
	}

	private int add(int num) {
		// add the new number in the min heap
		pq.add(num);
		// if heap has more than k numbers,
		// remove one number
		if (pq.size() > k) {
			pq.poll();
		}
		// return the kth largest number
		return pq.peek();
	}

	public static void main(String[] args) {
		int[] a = { 3, 1, 5, 12, 2, 11 };
		KthLargestStream kls = new KthLargestStream(a, 4);
		System.out.println(kls.add(6));
		System.out.println(kls.add(13));
		System.out.println(kls.add(4));
	}
}