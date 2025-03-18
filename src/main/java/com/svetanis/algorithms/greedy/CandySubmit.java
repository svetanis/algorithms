package com.svetanis.algorithms.greedy;

// 135. Candy

public final class CandySubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int candy(int[] ratings) {
		int increasing = 0;
		int decreasing = 0;
		int peak = 0;
		int total = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i]) {
				increasing++;
				peak = increasing + 1;
				decreasing = 0;
				total += peak;
			} else if (ratings[i] == ratings[i - 1]) {
				peak = 0;
				increasing = 0;
				decreasing = 0;
				total++;
			} else {
				decreasing++;
				increasing = 0;
				total += decreasing + (peak > decreasing ? 0 : 1);
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 2 };
		int[] a2 = { 1, 2, 2 };

		System.out.println(candy(a1)); // 5
		System.out.println(candy(a2)); // 4
	}
}
