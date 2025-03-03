package com.svetanis.algorithms.search.heap;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 506. Relative Ranks

public final class RelativeRanks {
	// Time Complexity: O(n)

	public static String[] ranks(int[] scores) {
		int n = scores.length;
		Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;
		}
		Arrays.sort(indices, (a, b) -> Integer.compare(scores[b], scores[a]));
		String[] ranks = new String[n];
		String[] medals = new String[] { "Gold Medal", "Silver Medal", "Bronze Medal" };
		for (int i = 0; i < n; i++) {
			if (i < 3) {
				ranks[indices[i]] = medals[i];
			} else {
				ranks[indices[i]] = String.valueOf(i + 1);
			}
		}
		return ranks;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 3, 2, 1 };
		Print.print(ranks(a1));
		int[] a2 = { 10, 3, 8, 9, 4 };
		Print.print(ranks(a2));
	}
}