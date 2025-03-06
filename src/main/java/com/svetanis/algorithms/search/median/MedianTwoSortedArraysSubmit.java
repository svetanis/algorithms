package com.svetanis.algorithms.search.median;

// 4. Median of Two Sorted Arrays

public final class MedianTwoSortedArraysSubmit {
	// Time Complexity: O(log (n + m))

	public static double median(int[] a1, int[] a2) {
		int n = a1.length;
		int m = a2.length;
		int halfTotal = (n + m) / 2;
		int left = 0;
		int right = n - 1;

		while (true) {
			int mid1 = (left + right) / 2;
			int mid2 = halfTotal - (mid1 + 1) - 1;
			int L1 = mid1 < 0 ? Integer.MIN_VALUE : a1[mid1];
			int R1 = mid1 >= n - 1 ? Integer.MAX_VALUE : a1[mid1+ 1];
			int L2 = mid2 < 0 ? Integer.MIN_VALUE : a2[mid2];
			int R2 = mid2 >= m - 1 ? Integer.MAX_VALUE : a2[mid2 + 1];
			if (L1 > R2) {
				right = mid1 - 1;
			} else if (L2 > R2) {
				left = mid1 + 1;
			} else {
				if ((m + n) % 2 == 0) {
					int max = Math.max(L1, L2);
					int min = Math.min(R1, R2);
					return (max + min) / 2.0;
				} else {
					return Math.min(R1, R2);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] s1 = { 1, 3 };
		int[] p1 = { 2 };
		System.out.println(median(s1, p1)); // 2.0

		int[] s2 = { 1, 2 };
		int[] p2 = { 3, 4 };
		System.out.println(median(s2, p2)); // 2.5
		
		int[] s3 = { 1, 3, 7 };
		int[] p3 = { 0, 2, 5, 6, 8 };
		System.out.println(median(s3, p3)); // 4.0

		int[] s4 = { 1, 3, 7, 9 };
		int[] p4 = { 0, 2, 5, 6, 8 };
		System.out.println(median(s4, p4)); // 5.0

	}
}