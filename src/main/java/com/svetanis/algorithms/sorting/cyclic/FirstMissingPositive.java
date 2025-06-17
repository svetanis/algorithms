package com.svetanis.algorithms.sorting.cyclic;

// 41. First Missing Positive

public final class FirstMissingPositive {
	// Time Complexity: O(n)

	public static int firstMissingPositive(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int index = a[i] - 1;
			while (a[i] > 0 && a[i] < n && a[i] != a[index]) {
				swap(a, i, index);
			}
		}

		for (int i = 0; i < n; i++) {
			if (a[i] != i + 1) {
				return i + 1;
			}
		}
		return n + 1;
	}

	private static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 0 };
		System.out.println(firstMissingPositive(a1)); // 3
		int[] a2 = { 3, 4, -1, 1 };
		System.out.println(firstMissingPositive(a2)); // 2
		int[] a3 = { 7, 8, 9, 11, 12 };
		System.out.println(firstMissingPositive(a3)); // 1
	}
}