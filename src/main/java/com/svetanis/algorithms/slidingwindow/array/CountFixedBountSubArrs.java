package com.svetanis.algorithms.slidingwindow.array;

// 2444. Count Subarrays With Fixed Bounds

public final class CountFixedBountSubArrs {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static long countSubArrays(int[] a, int minK, int maxK) {
		long count = 0;
		int left = -1;
		int minIndex = -1;
		int maxIndex = -1;
		for (int right = 0; right < a.length; right++) {
			if (a[right] > maxK || a[right] < minK) {
				left = right;
			}
			if (a[right] == minK) {
				minIndex = right;
			}
			if (a[right] == maxK) {
				maxIndex = right;
			}
			int min = Math.min(minIndex, maxIndex);
			count += Math.max(0, min - left);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 5, 2, 7, 5 };
		System.out.println(countSubArrays(a1, 1, 5)); // 2

		int[] a2 = { 1, 1, 1, 1 };
		System.out.println(countSubArrays(a2, 1, 1)); // 10
	}
}
