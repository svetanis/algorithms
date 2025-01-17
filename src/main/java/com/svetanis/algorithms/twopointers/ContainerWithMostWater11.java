package com.svetanis.algorithms.twopointers;

// 11. Container With Most Water

public final class ContainerWithMostWater11 {
	// Time Complexity: O(n)

	public static int maxArea(int[] a) {
		int left = 0;
		int right = a.length - 1;
		int max = Integer.MIN_VALUE;
		while (left < right) {
			int min = Math.min(a[left], a[right]);
			int area = min * (right - left);
			max = Math.max(max, area);
			if (a[left] < a[right]) {
				++left;
			} else {
				--right;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 2, 5, 8, 1, 3, 7 };
		System.out.println(maxArea(a)); // 21

		int[] a1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(maxArea(a1)); // 49

		int[] a2 = { 1, 1 };
		System.out.println(maxArea(a2)); // 1
	}
}