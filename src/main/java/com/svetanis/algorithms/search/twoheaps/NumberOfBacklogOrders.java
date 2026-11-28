package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;

import java.util.PriorityQueue;

// 1801. Number of Orders in the Backlog
//
// orders[i] = [price, amount, orderType], processed in the given order: amount orders at price,
// 0 = buy and 1 = sell. A buy is matched against the cheapest sell in the backlog, while that
// sell's price <= the buy's price; a sell is matched against the most expensive buy, while that
// buy's price >= the sell's price. Matched orders cancel one for one; what an order cannot match
// stays in the backlog. Returns the number of orders left in the backlog, modulo 10^9 + 7. Up to
// 10^5 rows, price and amount up to 10^9, so the total can reach about 10^14 -- a long.
//
// Two heaps that cancel against each other: sells with the lowest price on top, buys with the
// highest price on top. An incoming order EATs the other side's top while the prices cross,
// taking a whole entry or SPLITting the last one, and whatever is left of it is PARKed on its own
// side. A heap entry is one [price, amount] row, not amount separate orders -- amounts reach 10^9.
//
// Siblings, same problem: none in the repo.

public final class NumberOfBacklogOrders {
	// Time complexity: O(n * log(n)), every row pushes at most one entry when it is PARKed and at
	// most one when it SPLITs; every other poll removes an entry for good, so at most 2n polls
	// Auxiliary Space: O(n), the two heaps

	private static final int MOD = 1_000_000_007;

	public static int backlogOrders(int[][] orders) {
		// SELLS: lowest price on top
		PriorityQueue<int[]> spq = new PriorityQueue<>(comparingInt(a -> a[0]));
		// BUYS: highest price on top
		PriorityQueue<int[]> bpq = new PriorityQueue<>(comparingInt((int[] a) -> a[0]).reversed());
		for (int[] order : orders) {
			int orderType = order[2];
			if (orderType == 0) {
				processBuyOrders(order, spq, bpq);
			} else {
				processSellOrders(order, spq, bpq);
			}
		}
		return backlogCount(spq, bpq);
	}

	private static int backlogCount(PriorityQueue<int[]> spq, PriorityQueue<int[]> bpq) {
		// COUNT: in a long, the amounts add up past Integer.MAX_VALUE
		long count = 0;
		for (int[] border : bpq) {
			count += border[1];
		}
		for (int[] sorder : spq) {
			count += sorder[1];
		}
		return (int) (count % MOD);
	}

	private static void processSellOrders(int[] sorder, PriorityQueue<int[]> spq,
			PriorityQueue<int[]> bpq) {
		int sprice = sorder[0];
		int samount = sorder[1];
		// EAT: the highest buy, while it pays at least the sell price
		while (samount > 0 && !bpq.isEmpty() && bpq.peek()[0] >= sprice) {
			int[] border = bpq.poll();
			int bprice = border[0];
			int bamount = border[1];
			if (samount >= bamount) {
				samount -= bamount;
			} else {
				// SPLIT: the sell runs out inside this buy; the rest of the buy goes back
				bpq.offer(new int[] { bprice, bamount - samount });
				samount = 0;
			}
		}
		// PARK: what the buys could not take waits as one sell entry
		if (samount > 0) {
			spq.offer(new int[] { sprice, samount });
		}
	}

	private static void processBuyOrders(int[] border, PriorityQueue<int[]> spq,
			PriorityQueue<int[]> bpq) {
		int bprice = border[0];
		int bamount = border[1];
		// EAT: the lowest sell, while it asks at most the buy price
		while (bamount > 0 && !spq.isEmpty() && spq.peek()[0] <= bprice) {
			int[] sorder = spq.poll();
			int sprice = sorder[0];
			int samount = sorder[1];
			if (bamount >= samount) {
				bamount -= samount;
			} else {
				// SPLIT: the buy runs out inside this sell; the rest of the sell goes back
				spq.offer(new int[] { sprice, samount - bamount });
				bamount = 0;
			}
		}
		// PARK: what the sells could not fill waits as one buy entry
		if (bamount > 0) {
			bpq.offer(new int[] { bprice, bamount });
		}
	}

	public static void main(String[] args) {
		int[][] orders1 = { { 10, 5, 0 }, { 15, 2, 1 }, { 25, 1, 1 }, { 30, 4, 0 } };
		System.out.println(backlogOrders(orders1)); // 6

		int[][] orders2 = { { 7, 1000000000, 1 }, { 15, 3, 0 }, { 5, 999999995, 0 }, { 5, 1, 1 } };
		System.out.println(backlogOrders(orders2)); // 999999984
	}
}
