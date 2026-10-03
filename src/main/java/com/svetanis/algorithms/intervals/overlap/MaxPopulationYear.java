package com.svetanis.algorithms.intervals.overlap;

// 1854. Maximum Population Year
//
// logs[i] = { birth, death } of person i, with 1950 <= birth < death <= 2050. A person counts
// in the years birth .. death - 1, not in the year of death. Returns the year with the most
// people alive; on a tie, the earliest such year. All of this is LC's statement, with at least
// one person and at most 100.
//
// One slot a[y] per year, y counted from 1950. Each person puts +1 in the birth year and -1
// in the death year. Adding the slots up from 1950 onward, the running total at year y is the
// number of people alive in y: everyone born by y, minus everyone who died by y. The year
// where the running total is largest is the answer.

public final class MaxPopulationYear {
	// Time Complexity: O(n + Y), Y = 101 years; one pass over the people, one over the years
	// Space Complexity: O(Y) for the year slots

	private static final int OFFSET = 1950; // slot 0 is the year 1950

	public static int maxPopulation(int[][] logs) {
		int[] population = population(logs);
		int max = 0;
		int year = 0;
		int current = 0;
		for (int i = 0; i < population.length; i++) {
			current += population[i]; // people alive in the year 1950 + i
			if (current > max) { // strictly more, so a tie keeps the earlier year
				max = current;
				year = i;
			}
		}
		return year + OFFSET;
	}

	private static int[] population(int[][] logs) {
		int[] a = new int[101]; // the years 1950 .. 2050
		for (int[] log : logs) {
			int bi = log[0] - OFFSET;
			int di = log[1] - OFFSET;
			a[bi]++;
			a[di]--; // not alive in the year of death
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