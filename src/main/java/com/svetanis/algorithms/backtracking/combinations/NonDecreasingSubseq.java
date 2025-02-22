package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;

// 491. Non-decreasing Subsequences

public final class NonDecreasingSubseq {
	// Time Complexity: O(2^n)

	public static List<List<Integer>> subsequences(int[] a) {
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> subsequeces = new ArrayList<>();
		dfs(a, 0, Integer.MIN_VALUE, list, subsequeces);
		return subsequeces;
	}

	private static void dfs(int[] a, int index, int prev, List<Integer> list, List<List<Integer>> subsequences) {
		if (index == a.length) {
			if (list.size() > 1) {
				subsequences.add(new ArrayList<>(list));
			}
			return;
		}
		if (a[index] >= prev) {
			list.add(a[index]);
			dfs(a, index + 1, a[index], list, subsequences);
			list.remove(list.size() - 1);
		}
		if (a[index] != prev) {
			dfs(a, index + 1, prev, list, subsequences);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 6, 7, 7 };
		System.out.println(subsequences(a1));
		int[] a2 = { 4, 4, 3, 2, 1 };
		System.out.println(subsequences(a2));
	}
}