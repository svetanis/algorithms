package com.svetanis.algorithms.twopointers.dups;

import java.util.ArrayList;
import java.util.List;

// 442. Find All Duplicates in an Array

public final class AllDuplicates {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static List<Integer> dups(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			while (a[i] != a[a[i] - 1]) {
				swap(a, i, a[i] - 1);
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (a[i] != i + 1) {
				list.add(a[i]);
			}
		}
		return list;
	}

	private static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 3, 2, 7, 8, 2, 3, 1 }; // 2, 3
		System.out.println(dups(a1));
		int[] a2 = { 1, 1, 2 }; // 1
		System.out.println(dups(a2));
		int[] a3 = { 1 }; // []
		System.out.println(dups(a3));
	}
}
