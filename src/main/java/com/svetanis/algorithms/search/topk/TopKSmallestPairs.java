package com.svetanis.algorithms.search.topk;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// 373. Find K Pairs with Smallest Sums

public final class TopKSmallestPairs {
	// Time Complexity: O(k log k)

	public static ImmutableList<Pair<Integer, Integer>> topK(int k, List<Integer> list1, List<Integer> list2) {
		Queue<Node> pq = init(k, list2.get(0), list1);
		List<Pair<Integer, Integer>> list = new ArrayList<>();
		while (!pq.isEmpty() && k > 0) {
			Node node = pq.poll();
			int ind1 = node.index1;
			int ind2 = node.index2;
			int first = list1.get(ind1);
			list.add(Pair.build(first, list2.get(ind2)));
			k--;
			int next = ind2 + 1;
			if (next < list2.size()) {
				int sum = first + list2.get(next);
				pq.offer(new Node(sum, ind1, next));
			}
		}
		return newList(list);
	}

	private static Queue<Node> init(int k, int second, List<Integer> list) {
		Queue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.sum - n2.sum);
		for (int i = 0; i < min(list.size(), k); i++) {
			int sum = list.get(i) + second;
			pq.offer(new Node(sum, i, 0));
		}
		return pq;
	}

	public static void main(String[] args) {
		System.out.println(topK(3, asList(1, 7, 11), asList(2, 4, 6))); // [1,2], [1,4], [1,6]
		System.out.println(topK(2, asList(1, 1, 2), asList(1, 2, 3))); // [1,1], [1,1]
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