package com.svetanis.algorithms.greedy;

// 1007. Minimum Domino Rotations For Equal Row

public final class MinDominoRotation {
	// Time Complexity: O(n)

	public static int mdr(int[] tops, int[] bottoms) {
		int mfe = mostFrequent(tops, bottoms);
		int n = tops.length;
		int min = n + 1;
		int topSwaps = 0;
		int botSwaps = 0;
		for (int i = 0; i < n; i++) {
			if (tops[i] != mfe && bottoms[i] != mfe) {
				return -1;
			}
			if (tops[i] == mfe && bottoms[i] != mfe) {
				botSwaps++;
			}
			if (bottoms[i] == mfe && tops[i] != mfe) {
				topSwaps++;
			}
		}
		min = Math.min(min, Math.min(botSwaps, topSwaps));
		return min;
	}

	private static int mostFrequent(int[] tops, int[] bottoms) {
		int[] counts = new int[7];
		for (int i = 0; i < tops.length; i++) {
			counts[tops[i]]++;
			counts[bottoms[i]]++;
		}
		int max = 0;
		int mostFrequent = 0;
		for (int i = 0; i < 7; i++) {
			if (counts[i] > max) {
				max = counts[i];
				mostFrequent = i;
			}
		}
		return mostFrequent;
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
