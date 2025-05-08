package com.svetanis.algorithms.dp.sum.max.subarr;

// 978. Longest Turbulent Subarray

public final class LongestTurbulentSubArr {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxTurbulenceSize(int[] a) {
		int max = 1;
		int inc = 1;
		int dec = 1;
		for (int i = 1; i < a.length; i++) {
			int nextInc = a[i - 1] < a[i] ? dec + 1 : 1;
			int nextDec = a[i - 1] > a[i] ? inc + 1 : 1;
			inc = nextInc;
			dec = nextDec;
			max = Math.max(max, Math.max(inc, dec));
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 4, 2, 10, 7, 8, 8, 1, 9 };
		System.out.println(maxTurbulenceSize(a1)); // 5

		int[] a2 = { 4, 8, 12, 16 };
		System.out.println(maxTurbulenceSize(a2)); // 2

		int[] a3 = { 100 };
		System.out.println(maxTurbulenceSize(a3)); // 1
	}
}
