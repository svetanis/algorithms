package com.svetanis.algorithms.dp.math;

// 553. Optimal Division

public final class OptimalDivision {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String optimalDivision(int[] a) {
		int n = a.length;
		if (n == 1) {
			return String.valueOf(a[0]);
		}
		if (n == 2) {
			return a[0] + "/" + a[1];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(a[0]).append("/(");
		for (int i = 1; i < n - 1; i++) {
			sb.append(a[i]).append("/");
		}
		sb.append(a[n - 1]).append(")");
		return sb.toString();
	}

	public static void main(String[] args) {
		int[] a1 = { 1000, 100, 10, 2 };
		System.out.println(optimalDivision(a1)); // 1000/(100/10/2)
		int[] a2 = { 2, 3, 4 };
		System.out.println(optimalDivision(a2)); // 2/(3/4)
	}
}
