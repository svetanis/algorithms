package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 373. Find K Pairs with Smallest Sums
//
// Takes two arrays sorted ascending and k. Returns k pairs [u, v], u from the first array and v
// from the second, with the smallest sums u + v, smallest sum first, ties in any order.
//
// Picture every sum in a grid: row i holds a1[i] + a2[j] for every j, and each row is
// ascending because a2 is. The k smallest sums are the first k out of a k-way merge of the
// rows. The heap holds one entry per row, that row's next column, seeded at column 0. Only the
// first min(n1, k) rows are seeded: their column-0 sums are already k sums no larger than
// anything in a later row. Each poll is the next smallest sum; the entry one column to its
// right, in the same row, takes its place.
//
// Siblings -- two copies of this solution in this package:
//   TopKSmallestPairs        lists in, a List of Pair records out
//   TopKSmallestPairsSubmit  the LeetCode signature: int[] in, List<List<Integer>> out (this file)

public final class TopKSmallestPairsSubmit {
	// Time Complexity: O(k log k) -- k polls and at most k pushes, on a heap that never holds
	// more than min(n1, k) entries, one per row
	// Space Complexity: O(k), the heap plus the k pairs returned

	public static List<List<Integer>> topK(int[] a1, int[] a2, int k) {
		Queue<Node> pq = init(k, a2[0], a1); // SEED column 0 of the first min(n1, k) rows
		List<List<Integer>> list = new ArrayList<>();
		while (!pq.isEmpty() && k > 0) {
			Node node = pq.poll(); // POLL the smallest sum not yet taken
			int ind1 = node.index1;
			int ind2 = node.index2;
			int first = a1[ind1];
			list.add(Arrays.asList(first, a2[ind2])); // REPORT the pair behind that sum
			k--; // COUNT: one pair fewer to go
			int next = ind2 + 1;
			if (next < a2.length) {
				int sum = first + a2[next];
				pq.offer(new Node(sum, ind1, next)); // REFILL from the same row, one column right
			}
		}
		return list;
	}

	private static Queue<Node> init(int k, int second, int[] a) {
		Queue<Node> pq = new PriorityQueue<>(comparingInt(n -> n.sum)); // ORDER: smallest sum on top
		for (int i = 0; i < Math.min(a.length, k); i++) { // CAP: a row past the kth is never needed
			int sum = a[i] + second;
			pq.offer(new Node(sum, i, 0)); // PUSH row i at column 0
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
		private int sum; // the sum, cached so the comparator does not recompute it
		private int index1; // ROW: which number of the first array
		private int index2; // COLUMN: the cursor along the second array

		public Node(int sum, int index1, int index2) {
			this.sum = sum;
			this.index1 = index1;
			this.index2 = index2;
		}
	}
}