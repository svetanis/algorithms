package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import java.util.PriorityQueue;
import java.util.Queue;

// given m sorted arrays, find the k'th
// smallest number among all the arrays

// create a min heap with m heap nodes.
// every heap node has first element of an array
// id is index of array
// next is index of next element to be stored from array

public final class KSmallestInMSortedArrays {
	// Time Complexity: O(k log m)

	public static int kSmallest(int[][] a, int k) {
		Queue<Node> pq = init(a);
		return kSmallest(a, pq, k);
	}

	private static Queue<Node> init(int[][] a) {
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			Node node = new Node(a[i][0], i, 1);
			pq.offer(node);
		}
		return pq;
	}

	private static int kSmallest(int[][] a, Queue<Node> pq, int k) {
		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		int current = 0, count = 0;
		while (!pq.isEmpty() && count < k) {
			// get min element and store it in out
			Node node = pq.poll();
			current = node.val;
			count++;
			// find the next element that will
			// replace the current root of heap
			// the next element belongs to the
			// same array as the current root
			if (node.next < a[node.id].length) {
				int val = a[node.id][node.next];
				int next = node.next + 1;
				pq.offer(new Node(val, node.id, next));
			}
		}
		return current;
	}

	public static void main(String[] args) {
		int[][] a = { { 2, 6, 8 }, { 3, 6, 7 }, { 1, 3, 4} };
		System.out.println(kSmallest(a, 5));
	}
}