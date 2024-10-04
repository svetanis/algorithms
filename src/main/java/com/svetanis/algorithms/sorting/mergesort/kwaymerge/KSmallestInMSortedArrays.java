package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// given m sorted arrays, find the k'th
// smallest number among all the arrays

// create a min heap with m heap nodes.
// every heap node has first element of an array
// id is index of array
// next is index of next element to be stored from array

public final class KSmallestInMSortedArrays {
	// Time Complexity: O(min(k, n) + k * log n)
	// Space Complexity: O(n)

	public static int kSmallest(List<List<Integer>> lists, int k) {
		Queue<Node> pq = init(lists);
		return kSmallest(lists, pq, k);
	}

	private static Queue<Node> init(List<List<Integer>> lists) {
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < lists.size(); i++) {
			int val = lists.get(i).get(0);
			pq.offer(new Node(val, i, 1));
		}
		return pq;
	}

	private static int kSmallest(List<List<Integer>> lists, Queue<Node> pq, int k) {
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
			int size = lists.get(node.id).size();
			if (node.next < size) {
				int val = lists.get(node.id).get(node.next);
				int next = node.next + 1;
				pq.offer(new Node(val, node.id, next));
			}
		}
		return current;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = newArrayList();
		lists.add(asList(2, 6, 8));
		lists.add(asList(3, 6, 7));
		lists.add(asList(1, 3, 4));
		System.out.println(kSmallest(lists, 5));
	}
}