package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1167. Minimum Cost to Connect Sticks
//
// sticks[i] is a stick's length. Joining two sticks of lengths x and y costs x + y and leaves one
// stick of that length. Returns the least total cost of joining them all into one stick.
//
// A stick's length is paid again by every join it takes part in, so the shortest sticks should be
// joined first and the long ones last: always join the two shortest sticks left -- the same rule
// that builds a Huffman code. The joined stick goes back, because it may be among the two shortest
// on a later turn; a min-heap gives the two shortest every time.
//
// Time: O(n log n) -- n - 1 joins, each two polls and one push
// Space: O(n) for the heap
//
// Siblings:
//   data-structures heap/ConnectNRopes -- the same problem
//   LastStoneWeight (1046)             -- the same loop at the max end, reporting the survivor

public final class MinCostConnectSticks {

	public static int connectSticks(int[] a) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // MIN-HEAP: the shortest stick on top
		for (int num : a) {
			pq.offer(num);                 // PUSH: every stick
		}
		int totalCost = 0;
		while (pq.size() > 1) {
			int first = pq.poll();         // POLL: the shortest
			int second = pq.poll();        // POLL: the next shortest
			int cost = first + second;
			totalCost += cost;             // PAY: this join's cost
			pq.offer(cost);                // PUSH: the joined stick goes back
		}
		return totalCost;                  // REPORT: the running total, not the last stick
	}

	public static void main(String[] args) {
		int[] a = { 1, 8, 3, 5 };
		System.out.println(connectSticks(a)); // 30
	}
}
