package com.svetanis.algorithms.dp.countways;

// 1664. Ways to Make a Fair Array

public final class FairArray {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int countWays(int[] a) {
		int n = a.length;
		int odd = 0;
		int even = 0;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				even += a[i];
			} else {
				odd += a[i];
			}
		}
		return ways(a, odd, even);
	}

	private static int ways(int[] a, int odd, int even) {
		int count = 0;
		int currOdd = 0;
		int currEven = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int curr = a[i];
			if (i % 2 == 0) {
				if (currOdd + even - currEven - curr == currEven + odd - currOdd) {
					count++;
				}
				currEven += curr;
			} else {
				if (currOdd + even - currEven == currEven + odd - currOdd - curr) {
					count++;
				}
				currOdd += curr;
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
