package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 2491. Divide Players Into Teams of Equal Skill

public final class DividePlayers {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static long dividePlayers(int[] skills) {
		Arrays.sort(skills);
		int n = skills.length;
		int left = 0;
		int right = n - 1;
		long total = 0;
		long target = skills[0] + skills[n - 1];
		while (left < right) {
			if (skills[left] + skills[right] != target) {
				return -1;
			}
			total += skills[left] * skills[right];
			left++;
			right--;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 5, 1, 3, 4 };
		System.out.println(dividePlayers(a1)); // 22

		int[] a2 = { 3, 4 };
		System.out.println(dividePlayers(a2)); // 12

		int[] a3 = { 1, 1, 2, 3 };
		System.out.println(dividePlayers(a3)); // -1
	}
}
