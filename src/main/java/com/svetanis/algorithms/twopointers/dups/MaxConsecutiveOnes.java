package com.svetanis.algorithms.twopointers.dups;

// 485. Max Consecutive Ones

public final class MaxConsecutiveOnes {
	// Time Complexity: O(n)
	// Aux Space: O(1)

	public static int count(int[] a) {
		int count = 0;
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1) {
				count++;
			} else {
				max = Math.max(max, count);
				count = 0;
			}
		}
		return Math.max(max, count);
	}

	public static int count2(int[] a) {
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1) {
				int count = 0;
				int j = i;
				while(j < a.length && a[j] == 1) {
					count += 1;
					j += 1;
				}
				max = Math.max(max, count);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 0, 1, 1, 1 };
		System.out.println(count(a)); // 3
		int[] a1 = { 1, 0, 1, 1, 0, 1 };
		System.out.println(count(a1)); // 2
	}
}
