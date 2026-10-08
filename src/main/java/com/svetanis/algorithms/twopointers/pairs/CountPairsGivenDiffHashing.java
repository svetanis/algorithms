package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashSet;
import java.util.Set;

// 532. K-diff Pairs in an Array
//
// Input: an array of integers, values may repeat, and k >= 0.
// Return: how many different pairs of VALUES (x, x + k) occur at two different
// positions. Copies of the same pair count once.
//
// The one idea: walk left to right with a set of the values seen so far. The current
// value c closes a pair when c - k was seen (the pair c - k, c) or c + k was seen (the
// pair c, c + k). Each pair is recorded by its SMALLER value in a second set, so the
// copies of one pair collapse into one entry. Looking up before adding c keeps c from
// pairing with itself; with k == 0 the second copy of c finds the first.
//
// Siblings:
//   CountPairsGivenDiffBinary -- sorts, then binary-searches x + k to the right of x
//   CountPairsGivenAbsDiffHashing (LC 2006) -- counts pairs of POSITIONS, values 1..100
//
// Time: O(n) -- two lookups per number.
// Space: O(n) -- the two sets.

public final class CountPairsGivenDiffHashing {

	public static int count(int[] a, int k) {
		Set<Integer> pairs = new HashSet<>();   // the SMALLER value of each pair found
		Set<Integer> visited = new HashSet<>(); // the values seen so far
		for (int i = 0; i < a.length; i++) {
			int curr = a[i];
			if (visited.contains(curr - k)) {
				pairs.add(curr - k);            // FOUND the pair (curr - k, curr)
			}
			if (visited.contains(curr + k)) {
				pairs.add(curr);                // FOUND the pair (curr, curr + k)
			}
			visited.add(curr);                  // SEE curr for the numbers after it
		}
		return pairs.size();                    // RETURN: one entry per pair of values
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 3, 4, 2 };
		System.out.println(count(a1, 3)); // 2

		int[] a2 = { 8, 12, 16, 4, 0, 20 };
		System.out.println(count(a2, 4)); // 5

		int[] a3 = { 3, 1, 4, 1, 5 };
		System.out.println(count(a3, 2)); // 2

		int[] a4 = { 1, 2, 3, 4, 5 };
		System.out.println(count(a4, 1)); // 4

		int[] a5 = { 1, 3, 1, 5, 4 };
		System.out.println(count(a5, 0)); // 1
	}
}
