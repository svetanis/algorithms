package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 826. Most Profit Assigning Work 

public final class MostProfitAssigningWork {
	// Time Complexity: O(max(n, m) log max(n,m))
	// Space Complexity: O(n)

	public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {
		List<int[]> jobs = jobs(difficulty, profit);
		Arrays.sort(workers);
		int total = 0;
		int maxProfit = 0;
		int index = 0;
		for (int worker : workers) {
			while (index < difficulty.length && jobs.get(index)[0] <= worker) {
				maxProfit = Math.max(maxProfit, jobs.get(index)[1]);
				index++;
			}
			total += maxProfit;
		}
		return total;
	}

	private static List<int[]> jobs(int[] difficulty, int[] profit) {
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < difficulty.length; i++) {
			list.add(new int[] { difficulty[i], profit[i] });
		}
		Collections.sort(list, (i, j) -> i[0] - j[0]);
		return list;
	}

	public static void main(String[] args) {
		int[] difficulty = { 2, 4, 6, 8, 10 };
		int[] profit = { 10, 20, 30, 40, 50 };
		int[] worker = { 4, 5, 6, 7 };
		System.out.println(maxProfitAssignment(difficulty, profit, worker)); // 100

		int[] difficulty1 = { 85, 47, 57 };
		int[] profit1 = { 24, 66, 99 };
		int[] worker1 = { 40, 25, 25 };
		System.out.println(maxProfitAssignment(difficulty1, profit1, worker1)); // 0

		int[] difficulty2 = { 68, 35, 52, 47, 86 };
		int[] profit2 = { 67, 17, 1, 81, 3 };
		int[] worker2 = { 92, 10, 85, 84, 82 };
		System.out.println(maxProfitAssignment(difficulty2, profit2, worker2)); // 324
	}
}
