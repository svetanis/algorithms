package com.svetanis.algorithms.search.topk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 373. Find K Pairs with Smallest Sums

public final class TopKSmallestPairsSubmit {
	// Time Complexity: O(k log k)

	public static List<List<Integer>> topK(int[] a1, int[] a2, int k) {
		Queue<Node> pq = init(k, a2[0], a1);
		List<List<Integer>> list = new ArrayList<>();
		while (!pq.isEmpty() && k > 0) {
			Node node = pq.poll();
			int ind1 = node.index1;
			int ind2 = node.index2;
			int first = a1[ind1];
			list.add(Arrays.asList(first, a2[ind2]));
			k--;
			int next = ind2 + 1;
			if (next < a2.length) {
				int sum = first + a2[next];
				pq.offer(new Node(sum, ind1, next));
			}
		}
		return list;
	}

	private static Queue<Node> init(int k, int second, int[] a) {
		Queue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.sum - n2.sum);
		for (int i = 0; i < Math.min(a.length, k); i++) {
			int sum = a[i] + second;
			pq.offer(new Node(sum, i, 0));
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 7, 11 };
		int[] a2 = { 2, 4, 6 };

		System.out.println(topK(a1, a2, 3)); // [1,2], [1,4], [1,6]
		int[] a3 = { 1, 1, 2 };
		int[] a4 = { 1, 2, 3 };
		System.out.println(topK(a3, a4, 2)); // [1,1], [1,1]
	}

	private static class Node {
		private int sum;
		private int index1;
		private int index2;

		public Node(int sum, int index1, int index2) {
			this.sum = sum;
			this.index1 = index1;
			this.index2 = index2;
		}
	}
}