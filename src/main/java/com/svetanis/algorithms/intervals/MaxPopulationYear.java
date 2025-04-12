package com.svetanis.algorithms.intervals;

// 1854. Maximum Population Year

public final class MaxPopulationYear {
	// Time Complexity: O(n)

	private static final int OFFSET = 1950;

	public static int maxPopulation(int[][] logs) {
		int[] population = population(logs);
		int max = 0;
		int year = 0;
		int current = 0;
		for (int i = 0; i < population.length; i++) {
			current += population[i];
			if (current > max) {
				max = current;
				year = i;
			}
		}
		return year + OFFSET;
	}

	private static int[] population(int[][] logs) {
		int[] a = new int[101];
		for (int[] log : logs) {
			int bi = log[0] - OFFSET;
			int di = log[1] - OFFSET;
			a[bi]++;
			a[di]--;
		}
		return a;
	}

	public static void main(String[] args) {
		int[][] logs1 = { { 1993, 1999 }, { 2000, 2010 } };
		System.out.println(maxPopulation(logs1)); // 1993

		int[][] logs2 = { { 1950, 1961 }, { 1960, 1971 }, { 1970, 1981 } };
		System.out.println(maxPopulation(logs2)); // 1960
	}
}