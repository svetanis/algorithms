package com.svetanis.algorithms.prefixsum;

// 2237. Count Positions on Street With Required Brightness

public final class CountPositions {
	// Time complexity: O(n + m)

	public static int meetRequirement(int n, int[][] lights, int[] requirement) {
		int[] brightness = new int[100010];
		for (int[] light : lights) {
			int left = Math.max(0, light[0] - light[1]);
			int right = Math.min(n - 1, light[0] + light[1]);
			brightness[left]++;
			brightness[right + 1]--;
		}
		int count = 0;
		int currb = 0;
		for (int i = 0; i < n; i++) {
			currb += brightness[i];
			if (currb >= requirement[i]) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] lights = { { 1, 2 }, { 3, 1 } };
		int[] requirements = { 1, 2, 1, 1, 2 };
		System.out.println(meetRequirement(5, lights, requirements));
	}
}
