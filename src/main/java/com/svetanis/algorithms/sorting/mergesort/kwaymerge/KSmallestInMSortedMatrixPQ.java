package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import java.util.PriorityQueue;
import java.util.Queue;

// given an n x n matrix where each row
// and col is sorted in ascending order
// find the k-th smallest element in the matrix

public final class KSmallestInMSortedMatrixPQ {
	// Time Complexity: O(min(k, n) + k * log n)
	// Space Complexity: O(n)

	public static int kSmallest(int[][] matrix, int k) {
		Queue<Node> pq = init(matrix, k);
		return kSmallest(matrix, pq, k);
	}

	private static Queue<Node> init(int[][] matrix, int k) {
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < matrix.length && i < k; i++) {
			int val = matrix[i][0];
			pq.offer(new Node(val, i, 1));
		}
		return pq;
	}

	private static int kSmallest(int[][] matrix, Queue<Node> pq, int k) {
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
			int size = matrix[node.id].length;
			if (node.next < size) {
				int val = matrix[node.id][node.next];
				int next = node.next + 1;
				pq.offer(new Node(val, node.id, next));
			}
		}
		return current;
	}

	public static void main(String[] args) {
		int[][] matrix = {{2, 6, 8}, {3, 7, 10}, {5, 8, 1}};
		System.out.println(kSmallest(matrix, 5));
	}
}