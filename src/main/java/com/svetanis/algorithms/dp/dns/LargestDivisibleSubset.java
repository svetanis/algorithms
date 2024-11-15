package com.svetanis.algorithms.dp.dns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 368. Largest Divisible Subset

public final class LargestDivisibleSubset {

	public static List<Integer> lds(int[] a) {
		Arrays.sort(a);
		int n = a.length;
		int[] dp = new int[n];
		int maxIndex = lds(a, dp);
		return reconstruct(maxIndex, a, dp);
	}

	private static List<Integer> reconstruct(int maxIndex, int[] a, int[] dp) {
		int size = dp[maxIndex];
		List<Integer> list = new ArrayList<>();
		for (int i = maxIndex; size > 0; i--) {
			if (a[maxIndex] % a[i] == 0 && dp[i] == size) {
				list.add(a[i]);
				maxIndex = i;
				size--;
			}
		}
		Collections.reverse(list);
		return list;
	}

	private static int lds(int[] a, int[] dp) {
		Arrays.fill(dp, 1);
		int maxIndex = 0;
		for (int i = 0; i < a.length; i++) {
			int curr = a[i];
			for (int j = 0; j < i; j++) {
				int prev = a[j];
				if (curr % prev == 0) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			if (dp[maxIndex] < dp[i]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(lds(a1)); // [1,2]

		int[] a2 = { 1, 2, 4, 8 };
		System.out.println(lds(a2)); // [1,2,4,8]
	}
}
