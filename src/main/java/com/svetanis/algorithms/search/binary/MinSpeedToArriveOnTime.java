package com.svetanis.algorithms.search.binary;

// 1870. Minimum Speed to Arrive on Time

public final class MinSpeedToArriveOnTime {

	public static int minSpeed(int[] dist, double hour) {
		int low = 1;
		int high = (int) 1e7;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (canArrive(dist, mid, hour)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return canArrive(dist, low, hour) ? (int) low : -1;
	}

	private static boolean canArrive(int[] a, int speed, double hour) {
		int n = a.length;
		double total = 0.0;
		for (int i = 0; i < n; i++) {
			double time = (double) a[i] / speed;
			total += (i == n - 1) ? time : Math.ceil(time);
		}
		return total <= hour;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 2 };
		System.out.println(minSpeed(a1, 6)); // 1
		int[] a2 = { 1, 3, 2 };
		System.out.println(minSpeed(a2, 2.7)); // 3
		int[] a3 = { 1, 3, 2 };
		System.out.println(minSpeed(a3, 1.9)); // -1
	}
}