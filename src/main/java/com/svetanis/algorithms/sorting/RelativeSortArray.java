package com.svetanis.algorithms.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 1122. Relative Sort Array

public final class RelativeSortArray {
	// Time Complexity: O(n)

	public static int[] relativeSortArray(int[] a1, int[] a2) {
		int[] counts = counts(a1);
		int index = 0;
		for (int num : a2) {
			int freq = counts[num];
			for (int i = 0; i < freq; i++) {
				a1[index++] = num;
				counts[num]--;
			}
		}
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				int freq = counts[i];
				for (int j = 0; j < freq; j++) {
					a1[index++] = i;
					counts[i]--;
				}
			}
		}
		return a1;
	}

	private static int[] counts(int[] a) {
		int[] count = new int[1001];
		for (int num : a) {
			count[num]++;
		}
		return count;
	}

	// Time Complexity: O(m + n log n)
	public static int[] relativeSort(int[] a1, int[] a2) {
		int n = a2.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(a2[i], i);
		}
		int[][] pairs = new int[a1.length][0];
		for (int i = 0; i < pairs.length; i++) {
			pairs[i] = new int[] { a1[i], map.getOrDefault(a1[i], n + a1[i]) };
		}
		Arrays.sort(pairs, (p1, p2) -> p1[1] - p2[1]);
		for (int i = 0; i < pairs.length; i++) {
			a1[i] = pairs[i][0];
		}
		return a1;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
		int[] a2 = { 2, 1, 4, 3, 9, 6 };
		// Print.print(relativeSort(a1, a2));
		Print.print(relativeSortArray(a1, a2));

		int[] a3 = { 28, 6, 22, 8, 44, 17 };
		int[] a4 = { 22, 28, 8, 6 };
		// Print.print(relativeSort(a3, a4));
		Print.print(relativeSortArray(a3, a4));
	}
}
