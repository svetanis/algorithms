package com.svetanis.algorithms.twopointers.pairs;

// 2006. Count Number of Pairs With Absolute Difference K
//
// Input: an array of integers from 1 to 100, and k from 1 to 99.
// Return: how many pairs of positions (i, j), i < j, have |a[i] - a[j]| == k.
//
// The one idea: walk left to right and pair each number only with the numbers BEFORE
// it, so every pair is counted once, from its later end. The partners of x are x + k
// and x - k, so a table of how many times each value has been seen so far answers
// "how many earlier partners" in two lookups. The values are at most 100, so 101
// counters in an array do the job of a map.
//
// Siblings:
//   CountPairsGivenDiffHashing, CountPairsGivenDiffBinary (LC 532) -- count different
//     pairs of VALUES with difference k, not pairs of positions
//
// Time: O(n) -- two lookups per number.
// Space: O(1) -- 101 counters, whatever n is.

public final class CountPairsGivenAbsDiffHashing {

	public static int count(int[] a, int k) {
		int pairs = 0;
		int[] counts = new int[101];      // counts[v]: how many times v has been seen so far
		for (int num : a) {
			if (num + k <= 100) {
				pairs += counts[num + k]; // COUNT the earlier partners above num
			}
			if (num >= k) {
				pairs += counts[num - k]; // COUNT the earlier partners below num
			}
			counts[num]++;                // SEE num for the numbers after it
		}
		return pairs;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 1 };
		System.out.println(count(a1, 1)); // 4

		int[] a2 = { 1, 3 };
		System.out.println(count(a2, 3)); // 0

		int[] a3 = { 3, 2, 1, 5, 4 };
		System.out.println(count(a3, 2)); // 3
	}
}
