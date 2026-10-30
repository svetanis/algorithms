package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.PriorityQueue;

// 506. Relative Ranks
//
// score[i] is athlete i's score, all scores different. Returns answer[i], athlete i's place:
// "Gold Medal", "Silver Medal" and "Bronze Medal" for the top three, the place number as a string
// for everyone else.
//
// Push every athlete as (score, index) into a max-heap by score. The polls come out in place
// order, so a counter is the place, and the index says whose answer it is.
//
// Time: O(n log n) -- n pushes and n polls
// Space: O(n) for the heap and the answer
//
// Siblings, same problem:
//   RelativeRanks -- sorts the indices by score instead; the same O(n log n), no pair class

public final class RelativeRanksPQ {

	public static String[] ranks(int[] scores) {
		PriorityQueue<Node> pq = new PriorityQueue<>(comparingInt((Node n) -> n.score).reversed()); // MAX-HEAP: the highest score on top
		for (int i = 0; i < scores.length; i++) {
			pq.offer(new Node(scores[i], i));      // PUSH: every athlete with his index
		}
		int rank = 0;
		String[] ranks = new String[scores.length];
		while (!pq.isEmpty()) {
			Node node = pq.poll();                 // POLL: the next place in order
			int index = node.index;
			ranks[index] = rank(rank++);           // PLACE: the counter is the place, counted from 0
		}
		return ranks;                              // REPORT
	}

	private static String rank(int rank) {
		if (rank == 0) {
			return "Gold Medal";                   // MEDAL: place 1
		} else if (rank == 1) {
			return "Silver Medal";                 // MEDAL: place 2
		} else if (rank == 2) {
			return "Bronze Medal";                 // MEDAL: place 3
		} else {
			return String.valueOf(rank + 1);       // PLACE: counted from 0, printed from 1
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(ranks(a1))); // [Gold Medal, Silver Medal, Bronze Medal, 4, 5]
		int[] a2 = { 10, 3, 8, 9, 4 };
		System.out.println(Arrays.toString(ranks(a2))); // [Gold Medal, 5, Bronze Medal, Silver Medal, 4]
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