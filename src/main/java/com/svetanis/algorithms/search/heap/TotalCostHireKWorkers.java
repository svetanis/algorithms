package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;
import java.util.Queue;

// 2462. Total Cost To Hire K Workers
//
// costs[i] is the cost of hiring worker i. Hire k workers, one per session: each session hires the
// cheapest worker among the first `candidates` and the last `candidates` workers still unhired,
// the smaller index on a tie; if fewer than that remain, any of them. Returns the total cost.
//
// One min-heap holds both windows, ordered by (cost, index) -- the index tie-break is the problem's
// own rule. left is the last index of the front window, right the first index of the back window.
// Hiring from the front pulls index left + 1 in, hiring from the back pulls right - 1 in, and
// neither pulls once the windows meet, so no worker enters the heap twice. When the two windows
// overlap from the start, every worker is in the heap and nothing is ever pulled.
//
// Time: O((candidates + k) log candidates) -- the two windows go in, then k polls, each pulling at
//   most one worker
// Space: O(candidates) for the heap

public final class TotalCostHireKWorkers {

	public static long totalCost(int[] costs, int k, int candidates) {
		long total = 0;                                    // SUM: a long, k costs of up to 10^5
		int left = candidates - 1;                         // FRONT: the last index of the front window
		int right = costs.length - candidates;             // BACK: the first index of the back window
		Queue<Node> pq = priorityQueue(costs, candidates);
		while (k > 0) {
			Node curr = pq.poll();                         // POLL: the cheapest candidate, smaller index on a tie
			int index = curr.index;
			total += curr.cost;                            // HIRE
			// if element from left side, move right
			if (index <= left) {
				left++;
				if (left < right) {
					pq.offer(new Node(costs[left], left)); // PULL: the next worker into the front window
				}
			}
			// if element from right side, move left
			if (index >= right) {
				right--;
				if (right > left) {
					pq.offer(new Node(costs[right], right)); // PULL: the next worker into the back window
				}
			}
			k--;
		}
		return total;                                      // REPORT
	}

	private static Queue<Node> priorityQueue(int[] costs, int candidates) {
		int n = costs.length;
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < candidates; i++) {
			pq.offer(new Node(costs[i], i));               // PUSH: the front window
		}
		int left = candidates - 1;
		int start = n - candidates;
		for (int i = start; i < n; i++) {
			if (i > left) {
				pq.offer(new Node(costs[i], i));           // PUSH: the back window, skipping any index the front already holds
			}
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] a1 = { 17, 12, 10, 2, 7, 2, 11, 20, 8 };
		System.out.println(totalCost(a1, 3, 4)); // 11

		int[] a2 = { 1, 2, 4, 1 };
		System.out.println(totalCost(a2, 3, 3)); // 4
	}

	private static class Node implements Comparable<Node> {
		private int cost;
		private int index;

		public Node(int cost, int index) {
			this.cost = cost;
			this.index = index;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost == other.cost) {
				return Integer.compare(this.index, other.index); // TIE: the smaller index is hired first
			}
			return Integer.compare(this.cost, other.cost);       // ORDER: the cheaper worker first
		}
	}
}