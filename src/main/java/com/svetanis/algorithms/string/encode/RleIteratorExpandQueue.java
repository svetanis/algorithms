package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Queue;


// 900. RLE Iterator
//
// encoding is a run-length encoded sequence: for every even i, the value encoding[i + 1] is
// repeated encoding[i] times, and a count may be 0. next(n) uses up the next n elements of the
// sequence and returns the last one used up. If the sequence runs out before n elements are
// used, it returns -1, and whatever was left is used up all the same.
//
// This version expands the encoding into a queue that holds every element of the sequence, and
// next(n) polls n of them. When fewer than n are left, the queue is emptied before -1 is
// returned, so a later call also sees nothing left.
//
// It cannot pass LeetCode: a count can be 10^9 and there can be 500 runs, so the queue would
// need billions of entries. The sibling RleIteratorOnTheFly keeps a position inside the
// encoding instead and is the version to carry.

public final class RleIteratorExpandQueue {
	// Time Complexity: O(total of the counts) to build, O(n) per call
	// Space Complexity: O(total of the counts), the whole sequence

	private final Queue<Integer> queue;

	public RleIteratorExpandQueue(int[] encoding) {
		this.queue = expand(encoding);
	}

	private static Queue<Integer> expand(int[] encoding) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < encoding.length; i += 2) {
			int count = encoding[i];
			int value = encoding[i + 1];
			for (int c = 0; c < count; c++) {
				queue.offer(value); // one entry per element: the cost that breaks at 10^9
			}
		}
		return queue;
	}

	public int next(int n) {
		// STOP: fewer than n left, so use up everything and report -1
		if (n > queue.size()) {
			queue.clear();
			return -1;
		}
		int value = -1;
		for (int i = 0; i < n; i++) {
			value = queue.poll();
		}
		return value; // RETURN: the last element used up
	}

	public static void main(String[] args) {
		RleIteratorExpandQueue rle = new RleIteratorExpandQueue(new int[] { 3, 8, 0, 9, 2, 5 });
		System.out.println(rle.next(2)); // 8
		System.out.println(rle.next(1)); // 8
		System.out.println(rle.next(1)); // 5
		System.out.println(rle.next(2)); // -1

		RleIteratorExpandQueue past = new RleIteratorExpandQueue(new int[] { 2, 8 });
		System.out.println(past.next(3)); // -1, and both 8s are used up
		System.out.println(past.next(1)); // -1
	}
}
