package com.svetanis.algorithms.search.kthelement;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// find kth largest element in a stream of numbers

public final class KthLargestStream {
	// Time complexity: O(log k)
	// Space complexity: O(k)

	private final int k;
	private Queue<Integer> pq;

	public KthLargestStream(List<Integer> list, int k) {
		this.k = k;
		this.pq = new PriorityQueue<>((x, y) -> x - y);
		buildPQ(list);
	}

	private void buildPQ(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
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
		KthLargestStream kls = new KthLargestStream(asList(3, 1, 5, 12, 2, 11), 4);
		System.out.println(kls.add(6));
		System.out.println(kls.add(13));
		System.out.println(kls.add(4));
	}
}