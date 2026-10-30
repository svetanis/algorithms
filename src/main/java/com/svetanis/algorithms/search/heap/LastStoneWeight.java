package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;

// 1046. Last Stone Weight
//
// stones[i] is the weight of a stone. Each turn smash the two heaviest together: equal weights
// destroy both, otherwise the heavier survives with the difference as its new weight. Returns the
// weight of the last stone left, or 0 when none is left.
//
// The rule names the two heaviest every turn, and the survivor's new weight can land anywhere in
// the order -- so a max-heap: two out from the top, at most one back.
//
// Time: O(n log n) -- every turn removes at least one stone, so at most n - 1 turns
// Space: O(n) for the heap
//
// Siblings, same loop (two out, at most one back):
//   MinCostConnectSticks (1167) -- the min end, and the answer is a running total
//   dp/sum/given/subseq/LastStoneWeight* (1049, Last Stone Weight II) -- a different problem:
//     any two stones, smallest last stone; a subset-sum table, not a heap

public final class LastStoneWeight {

	public static int lsw(int[] stones) {
		Queue<Integer> pq = new PriorityQueue<>(reverseOrder()); // MAX-HEAP: the heaviest stone on top
		for (int stone : stones) {
			pq.offer(stone);                       // PUSH: every stone
		}
		while (pq.size() > 1) {
			int first = pq.poll();                 // POLL: the heaviest
			int second = pq.poll();                // POLL: the next heaviest
			if (first != second) {
				pq.offer(first - second);          // PUSH: the survivor goes back with the difference
			}
		}
		return pq.isEmpty() ? 0 : pq.poll();       // REPORT: the last stone, or 0 if every stone cancelled
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println(lsw(a1)); // 1
		int[] a2 = { 1 };
		System.out.println(lsw(a2)); // 1
	}
}
