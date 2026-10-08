package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashMap;
import java.util.Map;

// Count Pairs With a Given Sum From Two Arrays -- hashing
//
// Input: two arrays of integers, in any order, and a target k.
// Return: how many pairs (x from a1, y from a2) add up to k, each number used in at
// most one pair.
// Convention the code follows: with no repeated value inside either array this is the
// number of pairs of positions (i, j) with a1[i] + a2[j] == k. With repeats, each copy
// is used up by its pair: a1 = {5, 5}, a2 = {5}, k = 10 gives 1, not 2. The source
// statement would settle which count is wanted.
//
// The one idea: count the values of a1 in a map, then walk a2. The only partner of y
// is k - y; if a1 still has a copy of it, pair them and use that copy up.
//
// Siblings -- the same count when both arrays are sorted:
//   CountPairsGivenSum2SortedTwoPointers -- one pointer per array; uses numbers up the
//     same way
//   CountPairsGivenSum2SortedBinary -- binary-searches a2 for each number of a1; with
//     repeats it counts the numbers of a1 that have a partner instead
//
// Time: O(n + m) -- one pass to count a1, one pass over a2.
// Space: O(n) -- the map over a1.

public final class CountPairsGivenSum2ArraysHashing {

	public static int count(int[] a1, int[] a2, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int x : a1) {
			map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value of a1
		}
		int count = 0;
		for (int i = 0; i < a2.length; i++) {
			int val = k - a2[i];                    // the only partner a2[i] can have
			int freq = map.getOrDefault(val, 0);    // SEE how many copies are left
			if (freq > 0) {
				count++;                            // FOUND a pair
				map.put(val, freq - 1);             // DROP: that copy is used up
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 3, 4, 5, 6, 6 };
		int[] a2 = { 1, 4, 4, 5, 7 };
		System.out.println(count(a1, a2, 10)); // 4

		int[] a3 = { 1, 10, 13, 15 };
		int[] a4 = { 3, 3, 12, 4 };
		System.out.println(count(a3, a4, 13)); // 2
	}
}
