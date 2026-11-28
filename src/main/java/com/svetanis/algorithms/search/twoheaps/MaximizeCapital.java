package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;

import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Queue;

// 502. IPO, under the name Maximize Capital
//
// Project i needs c[i] capital to start and adds p[i] to the capital when it finishes. Start
// with capital init and take at most k distinct projects, one after another. Returns the largest
// final capital. On LeetCode: n and k up to 10^5, capital up to 10^9, p[i] between 0 and 10^4,
// and the answer fits in an int.
//
// Profits are never negative, so the capital never falls and taking the most profitable
// affordable project is always safe. Two heaps of project indexes on two different keys: min by
// capital (not affordable yet), max by profit (affordable now). Each round UNLOCKs everything the
// capital covers, then TAKEs the top of max; a round with nothing affordable STOPs the loop.
//
// Siblings, same problem:
//   IPO  the same two heaps over Project objects with named fields, so the unlock test reads
//        .capital and cannot compare an index against money; parameters (k, w, profit, capital)

public final class MaximizeCapital {
	// Time complexity: O(n * log(n)), every index is pushed into min once, moved to max at most
	// once and taken at most once; a round that takes nothing ends the loop
	// Auxiliary Space: O(n), the two heaps hold each index at most once between them

	public static int maximize(int[] c, int[] p, int k, int init) {
		// both heaps hold project INDEXES; the comparators
		// are what make one of them ordered by capital and
		// the other by profit
		Queue<Integer> min = new PriorityQueue<>(comparingInt(i -> c[i]));
		Queue<Integer> max = new PriorityQueue<>(comparingInt((Integer i) -> p[i]).reversed());

		// WAITING: every project, smallest capital first
		for (int i = 0; i < c.length; i++) {
			min.add(i);
		}
		int total = init;
		for (int i = 0; i < k; i++) {
			OptionalInt profit = add(min, max, c, p, total);
			// STOP: nothing affordable now, and nothing ever will be, because total did not grow
			if (!profit.isPresent()) {
				break;
			}
			// TAKE: the most profitable affordable project
			total += profit.getAsInt();
		}
		return total;
	}

	private static OptionalInt add(Queue<Integer> min, Queue<Integer> max, int[] c, int[] p,
			int target) {
		// UNLOCK: every project the capital now covers -- c[min.peek()], because min.peek() is an
		// index; a while, not an if, because one profit can pass several capitals
		while (!min.isEmpty() && c[min.peek()] <= target) {
			max.add(min.poll());
		}
		// PICK: the largest profit among the affordable projects, or empty when there is none
		return max.isEmpty() ? OptionalInt.empty() : OptionalInt.of(p[max.poll()]);
	}

	public static void main(String[] args) {
		int[] c1 = { 0, 1, 2 };
		int[] p1 = { 1, 2, 3 };
		System.out.println(maximize(c1, p1, 2, 1)); // 6

		int[] c2 = { 0, 1, 2, 3 };
		int[] p2 = { 1, 2, 3, 5 };
		System.out.println(maximize(c2, p2, 3, 0)); // 8
	}
}
