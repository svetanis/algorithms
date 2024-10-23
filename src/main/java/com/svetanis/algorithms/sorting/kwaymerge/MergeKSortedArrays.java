package com.svetanis.algorithms.sorting.kwaymerge;

import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Integer.MAX_VALUE;

import java.util.PriorityQueue;
import java.util.Queue;

// given k sorted arrays of size n each,
// merge them and return the sorted array

// create a min heap with k heap nodes.
// every heap node has first element of an array
// id is index of array
// next is index of next element to be stored from array

public final class MergeKSortedArrays {
	// Time Complexity: O(n log k)
	// Space Complexity: O(k)

	public static int[] merge(int[][] a) {
		Queue<Node> pq = init(a);
		return merge(a, pq);
	}

	private static Queue<Node> init(int[][] a) {
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			Node node = new Node(a[i][0], i, 1);
			pq.offer(node);
		}
		return pq;
	}

	private static int[] merge(int[][] a, Queue<Node> pq) {
		int k = a.length;
		int n = a[0].length;

		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		int[] merged = new int[n * k];
		for (int i = 0; i < merged.length; i++) {
			// get min element and store it in out
			Node node = pq.poll();
			merged[i] = node.val;

			// find the next element that will
			// replace the current root of heap
			// the next element belongs to the
			// same array as the current root
			if (node.next < n) {
				node.val = a[node.id][node.next];
				node.next += 1;
			} else { // root was the last element of its array
				node.val = MAX_VALUE;
			}
			pq.offer(node);
		}
		return merged;
	}

	public static void main(String[] args) {
		int[][] a = { { 2, 6, 12, 34 }, { 1, 9, 20, 1000 }, { 23, 34, 90, 2000 } };
		print(merge(a));
	}
}