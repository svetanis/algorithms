package com.svetanis.algorithms.dp.fibvariants;

// 740. Delete and Earn

public final class DeleteAndEarn {
	// Time Complexity: O(n + m)
	// Space Complexity: O(m)

	public static int deleteAndEarn(int[] nums) {
		int max = Integer.MIN_VALUE;
		int[] sum = new int[10010];
		for (int num : nums) {
			sum[num] += num;
			max = Math.max(max, num);
		}
		int[] incl = new int[10010];
		int[] excl = new int[10010];
		for (int i = 1; i <= max; i++) {
			incl[i] = excl[i - 1] + sum[i];
			excl[i] = Math.max(incl[i - 1], excl[i - 1]);
		}
		return Math.max(incl[max], excl[max]);
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 4, 2 };
		System.out.println(deleteAndEarn(a1)); // 6
		int[] a2 = { 2, 2, 3, 3, 3, 4 };
		System.out.println(deleteAndEarn(a2)); // 9
	}
}
