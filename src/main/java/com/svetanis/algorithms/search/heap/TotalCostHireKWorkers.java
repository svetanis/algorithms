package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;
import java.util.Queue;

// 2462. Total Cost To Hire K Workers

public final class TotalCostHireKWorkers {
	// Time Complexity: O(k * log candidates)
	// Space Complexity: O(candidates)

	public static long totalCost(int[] costs, int k, int candidates) {
		long total = 0;
		int left = candidates - 1;
		int right = costs.length - candidates;
		Queue<Node> pq = priorityQueue(costs, candidates);
		while (k > 0) {
			Node curr = pq.poll();
			int index = curr.index;
			total += curr.cost;
			// if element from left side, move right
			if (index <= left) {
				left++;
				if (left < right) {
					pq.offer(new Node(costs[left], left));
				}
			}
			// if element from right side, move left
			if (index >= right) {
				right--;
				if (right > left) {
					pq.offer(new Node(costs[right], right));
				}
			}
			k--;
		}
		return total;
	}

	private static Queue<Node> priorityQueue(int[] costs, int candidates) {
		int n = costs.length;
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < candidates; i++) {
			pq.offer(new Node(costs[i], i));
		}
		int left = candidates - 1;
		int start = n - candidates;
		for (int i = start; i < n; i++) {
			if (i > left) {
				pq.offer(new Node(costs[i], i));
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
				return this.index - other.index;
			}
			return this.cost - other.cost;
		}
	}
}