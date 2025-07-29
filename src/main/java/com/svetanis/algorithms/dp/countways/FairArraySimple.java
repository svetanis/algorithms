package com.svetanis.algorithms.dp.countways;

// 1664. Ways to Make a Fair Array

public final class FairArraySimple {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int countWays(int[] a) {
		int rightOdd = 0;
		int rightEven = 0;
		for (int i = 0; i < a.length; i++) {
			if (i % 2 == 0) {
				rightEven += a[i];
			} else {
				rightOdd += a[i];
			}
		}
		return ways(a, rightOdd, rightEven);
	}

	private static int ways(int[] a, int rightOdd, int rightEven) {
		int count = 0;
		int leftOdd = 0;
		int leftEven = 0;
		for (int i = 0; i < a.length; i++) {
			int curr = a[i];
			if (i % 2 == 0) {
				rightEven -= curr;
				if (leftEven + rightOdd == leftOdd + rightEven) {
					count++;
				}
				leftEven += curr;
			} else {
				rightOdd -= curr;
				if (leftEven + rightOdd == leftOdd + rightEven) {
					count++;
				}
				leftOdd += curr;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 6, 4 };
		System.out.println(countWays(a1)); // 1

		int[] a2 = { 1, 1, 1 };
		System.out.println(countWays(a2)); // 3

		int[] a3 = { 1, 2, 3 };
		System.out.println(countWays(a3)); // 0
	}
}
