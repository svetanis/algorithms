package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1792. Maximum Average Pass Ratio

public final class MaxAveragePassRatio {

	public static double maxAverageRatio(int[][] classes, int extraStudents) {
		int n = classes.length;
		double sum = 0;
		PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> -Double.compare(b, a));

		for (int[] c : classes) {
			double[] g = gain(c[0], c[1]);
			sum += g[0];
			pq.offer(g[1]);
		}
		double apr = sum / n;
		double diff = extraStudents/100.0 - apr;
		int count = 0;
		while(diff > 0) {
			double top = pq.poll();
			
		}
		return 0;
	}

	private static double[] gain(int passed, int total) {
		double average1 = (double) passed / total;
		double average2 = (double) (passed + 1) / (total + 1);
		return new double[] {average1, average2 - average1};
	}

	public static void main(String[] args) {
		int[][] classes1 = { { 1, 2 }, { 3, 5 }, { 2, 2 } };
		System.out.println(maxAverageRatio(classes1, 2)); // 0.78333

		int[][] classes2 = { { 2, 4 }, { 3, 9 }, { 4, 5 }, { 2, 10 } };
		System.out.println(maxAverageRatio(classes2, 4)); // 0.53485
	}
}
