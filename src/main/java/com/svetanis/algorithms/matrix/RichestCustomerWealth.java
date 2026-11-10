package com.svetanis.algorithms.matrix;

// 1672. Richest Customer Wealth
//
// Input: accounts[i][j] -- the money customer i holds in bank j. A customer's wealth is the sum of
// their row. Returns the largest wealth. On LeetCode: 1 <= accounts.length, accounts[i].length <= 50,
// 1 <= accounts[i][j] <= 100.
//
// The one idea: one row is one customer. Add up each row and keep the largest sum. The running
// maximum starts at -1, below any wealth, since every amount is at least 1; the largest wealth is
// 50 * 100 = 5000, so an int holds it.
//
// Time: O(m * n) -- every amount is added once.
// Space: O(1) -- one row sum and the maximum.

public final class RichestCustomerWealth {

	public int maxWealth(int[][] accounts) {
		int max = -1; // BEST: below any wealth
		for (int[] customer : accounts) { // WALK: one customer per row
			int sum = 0;
			for (int amount : customer) { // SUM: the row
				sum += amount;
			}
			max = Math.max(max, sum); // KEEP: the richest so far
		}
		return max;
	}

	public static void main(String[] agrs) {
		RichestCustomerWealth ism = new RichestCustomerWealth();
		int[][] g1 = { { 1, 2, 3 }, { 3, 2, 1 } };
		System.out.println(ism.maxWealth(g1)); // 6

		int[][] g2 = { { 1, 5 }, { 7, 3 }, { 3, 5 } };
		System.out.println(ism.maxWealth(g2)); // 10

		int[][] g3 = { { 2, 8, 7 }, { 7, 1, 3 }, { 1, 9, 5 } };
		System.out.println(ism.maxWealth(g3)); // 17
	}
}
