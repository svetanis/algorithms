package com.svetanis.algorithms.slidingwindow.fixed;

// 2134. Minimum Swaps to Group All 1's Together II

public final class MinSwapsToGroupOnesII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minSwaps(int[] a) {
		int n = a.length;
		int k = 0; // window size
		for (int num : a) {
			k += num;
		}
		if (k == 0 || k == n) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += a[i];
		}
		int maxOnes = sum;
		for (int right = k; right < n + k; right++) {
			sum += a[right % n] - a[(right - k + n) % n];
			maxOnes = Math.max(maxOnes, sum);
		}
		return k - maxOnes;
	}

	public static int minSwapsII(int[] a) {
		int n = a.length;
		int k = 0; // window size
		for (int num : a) {
			k += num;
		}
		if (k == 0 || k == n) {
			return 0;
		}
		int sum = 0;
		int minSwaps = Integer.MAX_VALUE;
		for (int right = 0; right < n + k; right++) {
			sum += a[right % n];
			if (right >= k) {
				sum -= a[(right - k + n) % n];
				minSwaps = Math.min(minSwaps, k - sum);
			}
		}
		return minSwaps;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 0, 1, 1, 0, 0 };
		System.out.println(minSwaps(a1)); // 1

		int[] a2 = { 0, 1, 1, 1, 0, 0, 1, 1, 0 };
		System.out.println(minSwaps(a2)); // 2

		int[] a3 = { 1, 1, 0, 0, 1 };
		System.out.println(minSwaps(a3)); // 0
	}
}
