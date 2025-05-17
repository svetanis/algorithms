package com.svetanis.algorithms.search.binary.invariant;

// 668. Kth Smallest Number in Multiplication Table

public final class KthSmallestInMultiplicationMatrix {
	// Time Complexity: O(m * log (m * n))
	// Space Complexity: O(1)

	public static int kthSmallest(int m, int n, int k) {
		int left = 1;
		int right = m * n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (count(m, n, mid) >= k) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int count(int m, int n, int mid) {
		int count = 0;
		for (int i = 1; i <= m; i++) {
			count += Math.min(mid / i, n);
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(kthSmallest(3, 3, 5)); // 3
		System.out.println(kthSmallest(2, 3, 6)); // 6
	}
}