package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 2251. Number of Flowers in Full Bloom

public final class NumberOfFlowersInFullBloom {
	// Time Complexity: O(n log n + m log n)
	// Space Complexity: O(n)

	public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
		int n = flowers.length;
		int[] start = new int[n];
		int[] end = new int[n];
		for (int i = 0; i < n; i++) {
			start[i] = flowers[i][0];
			end[i] = flowers[i][1];
		}
		Arrays.sort(start);
		Arrays.sort(end);

		int m = people.length;
		int[] count = new int[m];
		for (int i = 0; i < m; i++) {
			int p = people[i];
			count[i] = binary(start, p + 1) - binary(end, p);
		}
		return count;
	}

	private static int binary(int[] a, int value) {
		int left = 0;
		int right = a.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] >= value) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] people1 = { 2, 3, 7, 11 };
		int[][] f1 = { { 1, 6 }, { 3, 7 }, { 9, 12 }, { 4, 13 } };
		Print.print(fullBloomFlowers(f1, people1)); // 1,2,2,2

		int[] people2 = { 3, 3, 2 };
		int[][] f2 = { { 1, 6 }, { 3, 7 }, { 9, 12 }, { 4, 13 } };
		Print.print(fullBloomFlowers(f2, people2)); // 2,2,1

		int[] people3 = { 6, 7, 21, 1, 13, 37, 5, 37, 46, 43 };
		int[][] f3 = { { 19, 37 }, { 19, 38 }, { 19, 35 } };
		Print.print(fullBloomFlowers(f3, people3)); // 2,2,1

		int[] people4 = { 1, 1000000000 };
		int[][] f4 = { { 1, 2 }, { 4, 300 } };
		Print.print(fullBloomFlowers(f4, people4)); // 2,2,1

	}
}
