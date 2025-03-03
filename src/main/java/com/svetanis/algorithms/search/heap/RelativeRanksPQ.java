package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

import com.svetanis.java.base.utils.Print;

// 506. Relative Ranks

public final class RelativeRanksPQ {

	public static String[] ranks(int[] scores) {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n2.score - n1.score);
		for (int i = 0; i < scores.length; i++) {
			pq.offer(new Node(scores[i], i));
		}
		int rank = 0;
		String[] ranks = new String[scores.length];
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int index = node.index;
			ranks[index] = rank(rank++);
		}
		return ranks;
	}

	private static String rank(int rank) {
		if (rank == 0) {
			return "Gold Medal";
		} else if (rank == 1) {
			return "Silver Medal";
		} else if (rank == 2) {
			return "Bronze Medal";
		} else {
			return String.valueOf(rank + 1);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 3, 2, 1 };
		Print.print(ranks(a1));
		int[] a2 = { 10, 3, 8, 9, 4 };
		Print.print(ranks(a2));
	}

	private static class Node {
		private int score;
		private int index;

		public Node(int score, int index) {
			this.score = score;
			this.index = index;
		}
	}
}