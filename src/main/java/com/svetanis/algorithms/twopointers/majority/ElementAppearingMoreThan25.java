package com.svetanis.algorithms.twopointers.majority;

// 1287. Element Appearing More Than 25% In Sorted Array

public final class ElementAppearingMoreThan25 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int majorityElement(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (a[i] == a[i + n / 4]) {
				return a[i];
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 6, 6, 6, 6, 7, 10 };
		System.out.println(majorityElement(a)); // 6
		int[] a1 = { 1, 1 };
		System.out.println(majorityElement(a1)); // 1
	}
}