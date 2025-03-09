package com.svetanis.algorithms.search.binary;

// 774. Minimize Max Distance to Gas Station

public final class MinimizeMaxDistToGasStation {

	public static double minMaxGasDist(int[] stations, int k) {
		double left = 0;
		double right = 1e8;
		while (right - left > 1e-6) {
			double mid = left + (right - left) / 2;
			if (check(stations, mid, k)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return left;
	}

	private static boolean check(int[] a, double mid, int k) {
		int total = 0;
		for (int i = 1; i < a.length; i++) {
			int local = (int) ((a[i] - a[i - 1]) / mid);
			total += local;
		}
		return total <= k;
	}

	public static void main(String[] args) {
		int[] stations = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(minMaxGasDist(stations, 9)); // 0.5
		int[] stations2 = { 1, 5, 10 };
		System.out.println(minMaxGasDist(stations2, 2)); // 2.5
	}
}
