package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// given k sorted arrays of different size,
// merge them and return the sorted array

// create a min heap with k heap nodes.
// every heap node has first element of an array
// id is index of array
// next is index of next element to be stored from array

public final class MergeKSorted {
	// Time Complexity: O(n*k log k)

	public static ImmutableList<Integer> merge(int[][] a) {
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

	private static ImmutableList<Integer> merge(int[][] a, Queue<Node> pq) {
		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		List<Integer> list = newArrayList();
		while (!pq.isEmpty()) {
			// get min element and store it in out
			Node node = pq.poll();
			list.add(node.val);

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
		return newList(list);
	}

	public static void main(String[] args) {
		int[][] a = { { 2, 6, 12 }, { 1, 9 }, { 23, 34, 90, 2000 } };
		print(merge(a));
	}
}