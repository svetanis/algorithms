package com.svetanis.algorithms.matrix;

// 1672. Richest Customer Wealth

public final class RichestCustomerWealth {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	public int maxWealth(int[][] accounts) {
		int max = -1;
		for (int[] customer : accounts) {
			int sum = 0;
			for (int amount : customer) {
				sum += amount;
			}
			max = Math.max(max, sum);
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
