package com.svetanis.algorithms.greedy;

// 330. Patching Array

public final class PatchingArray {
	// Time Complexity: O(n)

	public static int minPatches(int[] a, int n) {
		long x = 1;
		int i = 0;
		int patches = 0;
		while (x <= n) {
			if (i < a.length && a[i] <= x) {
				x += a[i++];
			} else {
				x *= 2;
				patches++;
			}
		}
		return patches;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3 };
		System.out.println(minPatches(a1, 6)); // 1

		int[] a2 = { 1, 5, 10 };
		System.out.println(minPatches(a2, 20)); // 2

		int[] a3 = { 1, 2, 2 };
		System.out.println(minPatches(a3, 5)); // 0
	}
}
