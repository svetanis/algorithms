package com.svetanis.algorithms.math;

// 1492. The kth Factor of n

public final class KthFactorOfN {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	// walk 1..n in order and count the factors; the k-th one met is the answer
	public static int kthFactor(int n, int k) {
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				k--;
				if (k == 0) {
					return i;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(kthFactor(12, 3)); // 3
		System.out.println(kthFactor(7, 2)); // 7
		System.out.println(kthFactor(4, 4)); // -1
	}
}