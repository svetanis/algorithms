package com.svetanis.algorithms.greedy;

// 1007. Minimum Domino Rotations For Equal Row

public final class MinDominoRotationEfficient {
	// Time Complexity: O(n)

	public static int mdr(int[] tops, int[] bottoms) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 6; i++) {
			int swaps = rotations(tops, bottoms, i);
			if (swaps != -1) {
				min = Math.min(min, swaps);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private static int rotations(int[] tops, int[] bottoms, int value) {
		int topSwaps = 0;
		int botSwaps = 0;
		int n = tops.length;
		for (int i = 0; i < n; i++) {
			if (tops[i] != value && bottoms[i] != value) {
				return -1;
			} else if (bottoms[i] != value) {
				botSwaps++;
			} else if (tops[i] != value) {
				topSwaps++;
			}
		}
		return Math.min(botSwaps, topSwaps);
	}

	public static void main(String[] args) {
		int[] t1 = { 2, 1, 2, 4, 2, 2 };
		int[] b1 = { 5, 2, 6, 2, 3, 2 };
		System.out.println(mdr(t1, b1)); // 2

		int[] t2 = { 3, 5, 1, 2, 3 };
		int[] b2 = { 3, 6, 3, 3, 4 };
		System.out.println(mdr(t2, b2)); // -1

		int[] t3 = { 2, 2, 2, 4, 4, 4 };
		int[] b3 = { 4, 3, 4, 2, 2, 2 };
		System.out.println(mdr(t3, b3)); // 3

	}
}
