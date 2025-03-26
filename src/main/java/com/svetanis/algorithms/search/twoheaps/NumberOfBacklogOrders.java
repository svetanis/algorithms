package com.svetanis.algorithms.search.twoheaps;

import java.util.PriorityQueue;

// 1801. Number of Orders in the Backlog

public final class NumberOfBacklogOrders {

	private static final int MOD = (int) 1e9 + 7;

	public static int backlogOrders(int[][] orders) {
		PriorityQueue<int[]> spq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		PriorityQueue<int[]> bpq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
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
		long count = 0;
		while (!bpq.isEmpty()) {
			count += bpq.poll()[1];
		}
		while (!spq.isEmpty()) {
			count += spq.poll()[1];
		}
		return (int) (count % MOD);
	}

	private static void processSellOrders(int[] sorder, PriorityQueue<int[]> spq, PriorityQueue<int[]> bpq) {
		int sprice = sorder[0];
		int samount = sorder[1];
		while (samount > 0 && !bpq.isEmpty() && bpq.peek()[0] >= sprice) {
			int[] border = bpq.poll();
			int bprice = border[0];
			int bamount = border[1];
			if (samount >= bamount) {
				samount -= bamount;
			} else {
				bpq.offer(new int[] { bprice, bamount - samount });
				samount = 0;
			}
		}
		if (samount > 0) {
			spq.offer(new int[] { sprice, samount });
		}
	}

	private static void processBuyOrders(int[] border, PriorityQueue<int[]> spq, PriorityQueue<int[]> bpq) {
		int bprice = border[0];
		int bamount = border[1];
		while (bamount > 0 && !spq.isEmpty() && spq.peek()[0] <= bprice) {
			int[] sorder = spq.poll();
			int sprice = sorder[0];
			int samount = sorder[1];
			if (bamount >= samount) {
				bamount -= samount;
			} else {
				spq.offer(new int[] { sprice, samount - bamount });
				bamount = 0;
			}
		}
		if (bamount > 0) {
			bpq.offer(new int[] { bprice, bamount });
		}
	}

	public static void main(String args[]) {
		int[][] orders1 = { { 10, 5, 0 }, { 15, 2, 1 }, { 25, 1, 1 }, { 30, 4, 0 } };
		System.out.println(backlogOrders(orders1)); // 6

		int[][] orders2 = { { 7, 1000000000, 1 }, { 15, 3, 0 }, { 5, 999999995, 0 }, { 5, 1, 1 } };
		System.out.println(backlogOrders(orders2)); // 999999984
	}
}
