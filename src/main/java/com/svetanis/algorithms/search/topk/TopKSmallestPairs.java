package com.svetanis.algorithms.search.topk;

import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 373. Find K Pairs with Smallest Sums
//
// Takes k and two lists sorted ascending. Returns k pairs (u, v), u from the first list and v
// from the second, with the smallest sums u + v, smallest sum first, ties in any order.
//
// Picture every sum in a grid: row i holds list1[i] + list2[j] for every j, and each row is
// ascending because list2 is. The k smallest sums are the first k out of a k-way merge of the
// rows. The heap holds one entry per row, that row's next column, seeded at column 0. Only the
// first min(n1, k) rows are seeded: their column-0 sums are already k sums no larger than
// anything in a later row. Each poll is the next smallest sum; the entry one column to its
// right, in the same row, takes its place.
//
// Siblings -- two copies of this solution in this package:
//   TopKSmallestPairs        lists in, a List of Pair records out                   (this file)
//   TopKSmallestPairsSubmit  the LeetCode signature: int[] in, List<List<Integer>> out

public final class TopKSmallestPairs {
	// Time Complexity: O(k log k) -- k polls and at most k pushes, on a heap that never holds
	// more than min(n1, k) entries, one per row
	// Space Complexity: O(k), the heap plus the k pairs returned

	public static List<Pair> topK(int k, List<Integer> list1, List<Integer> list2) {
		Queue<Node> pq = init(k, list2.get(0), list1); // SEED column 0 of the first min(n1, k) rows
		List<Pair> list = new ArrayList<>();
		while (!pq.isEmpty() && k > 0) {
			Node node = pq.poll(); // POLL the smallest sum not yet taken
			int ind1 = node.index1;
			int ind2 = node.index2;
			int first = list1.get(ind1);
			list.add(new Pair(first, list2.get(ind2))); // REPORT the pair behind that sum
			k--; // COUNT: one pair fewer to go
			int next = ind2 + 1;
			if (next < list2.size()) {
				int sum = first + list2.get(next);
				pq.offer(new Node(sum, ind1, next)); // REFILL from the same row, one column right
			}
		}
		return list;
	}

	private static Queue<Node> init(int k, int second, List<Integer> list) {
		Queue<Node> pq = new PriorityQueue<>(comparingInt(n -> n.sum)); // ORDER: smallest sum on top
		for (int i = 0; i < min(list.size(), k); i++) { // CAP: a row past the kth is never needed
			int sum = list.get(i) + second;
			pq.offer(new Node(sum, i, 0)); // PUSH row i at column 0
		}
		return pq;
	}

	public static void main(String[] args) {
		System.out.println(topK(3, asList(1, 7, 11), asList(2, 4, 6))); // [1,2], [1,4], [1,6]
		System.out.println(topK(2, asList(1, 1, 2), asList(1, 2, 3))); // [1,1], [1,1]
	}

	// one answer: a number from the first list and one from the second
	public record Pair(int left, int right) {
	}

	private static class Node {
		private int sum; // the sum, cached so the comparator does not recompute it
		private int index1; // ROW: which number of the first list
		private int index2; // COLUMN: the cursor along the second list

		public Node(int sum, int index1, int index2) {
			this.sum = sum;
			this.index1 = index1;
			this.index2 = index2;
		}
	}
}