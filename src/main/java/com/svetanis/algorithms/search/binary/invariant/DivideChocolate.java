package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 1231. Divide Chocolate

public final class DivideChocolate {
	// Time Complexity: O(m * log n)

	public static int maximizeSweetness(int[] sweetness, int k) {
		int left = 1;
		int right = Arrays.stream(sweetness).sum();
		while (left < right) {
			int mid = (left + right + 1) >>> 1;
			if (canSplit(sweetness, k, mid)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	private static boolean canSplit(int[] sweetness, int k, int min) {
		int sum = 0;
		int count = 0;
		for (int sweet : sweetness) {
			sum += sweet;
			if (sum >= min) {
				sum = 0;
				count++;
			}
		}
		return count >= k + 1;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(maximizeSweetness(a1, 2));
	}
}
