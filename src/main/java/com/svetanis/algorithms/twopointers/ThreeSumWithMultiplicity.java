package com.svetanis.algorithms.twopointers;

// 923. 3Sum With Multiplicity
//
// Input: an array of integers from 0 to 100, and a target.
// Return: how many triples of positions i < j < k have a[i] + a[j] + a[k] == target,
// modulo 10^9 + 7. Positions, not values: equal values at different positions make
// different triples, so {2, 2, 2, 2} with target 6 counts 4.
//
// The one idea: FIX the middle position i and try every position j to its left; the
// third value is then forced, target - a[j] - a[i], and how many positions to the right
// of i hold it is read from a count table. The table starts as the count of every value
// in the whole array, and a[i] is taken out of it as i arrives, so it always counts
// exactly the positions to the right of i.
//
// Time: O(n^2) -- every pair j < i, each with an O(1) lookup.
// Space: O(1) -- the count table has 101 slots whatever n is.
// count + freq[third] stays below 10^9 + 7 + 3000, inside int.

public final class ThreeSumWithMultiplicity {

	private static final int MOD = 1000000007;

	public static int triplets(int[] a, int target) {
		int[] freq = frequencies(a);                     // START: counts of the whole array
		int count = 0;
		for (int i = 0; i < a.length; ++i) {             // FIX the middle position
			int second = a[i];
			freq[second]--;                              // DROP a[i]: freq now counts positions after i
			for (int j = 0; j < i; j++) {                // every first position before i
				int first = a[j];
				int third = target - first - second;     // the third value is forced
				if (third >= 0 && third <= 100) {        // SKIP a value outside 0..100: no slot for it
					count = (count + freq[third]) % MOD; // COUNT every position after i holding it
				}
			}
		}
		return count;
	}

	private static int[] frequencies(int[] a) {
		int[] count = new int[101];
		for (int num : a) {
			count[num]++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 };
		System.out.println(triplets(a, 8)); // 20

		int[] a1 = { 1, 1, 2, 2, 2, 2 };
		System.out.println(triplets(a1, 5)); // 12

		int[] a2 = { 2, 1, 3 };
		System.out.println(triplets(a2, 6)); // 1

		int[] a3 = { 2, 2, 2, 2 };
		System.out.println(triplets(a3, 6)); // 4
	}
}