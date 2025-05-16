package com.svetanis.algorithms.search.kthelement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows

public final class KthSmallestSum {
	// Time Complexity: O(m * k * log k)
	// Space Complexity: O(k)

	public static int kthSmallest(int[][] matrix, int k) {
		List<Integer> prev = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		prev.add(0);
		for (int[] row : matrix) {
			curr.clear();
			for (int sum : prev) {
				for (int val : row) {
					curr.add(sum + val);
				}
			}
			Collections.sort(curr);
			prev.clear();

			for (int i = 0; i < Math.min(k, curr.size()); i++) {
				prev.add(curr.get(i));
			}
		}
		return prev.get(k - 1);
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 3, 11 }, { 2, 4, 6 } };
		System.out.println(kthSmallest(m1, 5)); // 7

		int[][] m2 = { { 1, 3, 11 }, { 2, 4, 6 } };
		System.out.println(kthSmallest(m2, 9));// 17

		int[][] m3 = { { 1, 10, 10 }, { 1, 4, 5 }, { 2, 3, 6 } };
		System.out.println(kthSmallest(m3, 7)); // 9
	}
}