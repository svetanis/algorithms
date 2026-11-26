package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// 703. Kth Largest Element in a Stream

// Built from k and the starting numbers nums; then add(val) is called
// again and again, and each call returns the kth largest of every number
// seen so far. Duplicates count as separate positions.

// Keep only the k largest seen so far, in a min-heap: its top is the
// smallest of them, which is exactly the kth largest. A new number goes
// in, and if the heap now holds k + 1 the smallest leaves. The heap is a
// field, so it survives between calls -- that is all a stream adds.

// Time: O(log k) per add -- one offer and at most one poll on a heap of
//   k + 1; the constructor is n adds, O(n log k)
// Space: O(k) -- the heap never keeps more than k after an add returns

// Siblings:
//   KthLargestMinHeapPQ (215) -- the same heap over a whole array at once.
//   KthLargestStreamScanner -- the same heap fed from a Scanner.

public final class KthLargestStream {

	private final int k;
	private final Queue<Integer> pq;

	public KthLargestStream(int k, int[] nums) {
		this.k = k;
		this.pq = new PriorityQueue<>(Integer::compare); // HEAP: min-heap, the top is the kth largest
		for (int num : nums) {
			add(num); // SEED: the starting numbers go in one add at a time
		}
	}

	// public: LC 703 calls add() from outside the class
	public int add(int num) {
		pq.add(num); // ADD the new number
		while (pq.size() > k) { // TRIM back to k
			pq.poll(); // EVICT the smallest -- it is not among the k largest
		}
		return pq.peek(); // REPORT: the smallest of the k largest
	}

	public static void main(String[] args) {
		int[] a = { 4, 5, 8, 2 };
		KthLargestStream kls = new KthLargestStream(3, a);
		System.out.println(kls.add(3)); // 4
		System.out.println(kls.add(5)); // 5
		System.out.println(kls.add(10)); // 5
		System.out.println(kls.add(9)); // 8
		System.out.println(kls.add(4)); // 8

		int[] a1 = { 7, 7, 7, 7, 8, 3 };
		KthLargestStream kls2 = new KthLargestStream(4, a1);
		System.out.println(kls2.add(2)); // 7
		System.out.println(kls2.add(10)); // 7
		System.out.println(kls2.add(9)); // 7
		System.out.println(kls2.add(9)); // 8

		int[] a2 = { 3, 1, 5, 12, 2, 11 };
		KthLargestStream kls3 = new KthLargestStream(4, a2);
		System.out.println(kls3.add(6)); // 5
		System.out.println(kls3.add(13)); // 6
		System.out.println(kls3.add(4)); // 6
	}
}
