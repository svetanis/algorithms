package com.svetanis.algorithms.twopointers.triplet;

import java.util.HashMap;
import java.util.Map;

// 2964. Number of Divisible Triplet Sums
//
// Input: an array of non-negative integers and a divisor d >= 1. The remainder arithmetic
// below relies on no number being negative.
// Return: how many triples of positions i < j < k have (a[i] + a[j] + a[k]) % d == 0.
//
// The one idea: FIX two positions and count the third by its REMAINDER mod d. A third
// number fits when its remainder is (d - pairSum % d) % d -- the outer % d turns a needed
// remainder of d into 0. Counting remainders instead of values is what lets one lookup
// answer "how many numbers fit".
//
// Two methods, the same count:
//   countDivisible       -- FIX the last two positions (i, j); the map holds the remainder
//                           of every number before i. O(n) extra space.
//   countDivisibleSimple -- FIX the last position i; counts[] holds the remainder of every
//                           pair sum among the positions before i, topped up with the pairs
//                           ending at i - 1. An array of d counters: sensible only when d is
//                           small.
// A sum of two numbers stays within int while the numbers are at most 10^9.
//
// Time: O(n^2) for both -- every pair is visited once.
// Space: O(n) for countDivisible, O(d) for countDivisibleSimple.

public final class CountDivisibleTriplets {

	public static int countDivisibleSimple(int[] a, int d) {
		int count = 0;
		int n = a.length;
		int[] counts = new int[d];                       // remainder -> pairs before i that leave it
		for (int i = 2; i < n; i++) {                    // FIX the last position i
			int mod = (d - a[i] % d) % d;                // the remainder the pair must leave
			for (int j = i - 2; j >= 0; j--) {
				int sum = (a[i - 1] + a[j]) % d;
				counts[sum] += 1;                        // RECORD the new pairs (j, i - 1)
			}
			count += counts[mod];                        // COUNT every pair that completes a[i]
		}
		return count;
	}

	public static int countDivisible(int[] a, int d) {
		int count = 0;
		int n = a.length;
		Map<Integer, Integer> map = new HashMap<>();     // remainder -> numbers before i that leave it
		for (int i = 0; i < n; i++) {                    // FIX the middle position i
			for (int j = i + 1; j < n; j++) {            // FIX the last position j
				int mod = (d - (a[i] + a[j]) % d) % d;   // the remainder the first number must leave
				count += map.getOrDefault(mod, 0);       // COUNT every first number that fits
			}
			map.merge(a[i] % d, 1, Integer::sum);        // RECORD a[i] once its pairs are done
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 5, 7, 11 };
		System.out.println(countDivisible(a1, 5)); // 3
		System.out.println(countDivisibleSimple(a1, 5)); // 3

		int[] a2 = { 3, 3, 4, 7, 8 };
		System.out.println(countDivisible(a2, 5)); // 3
		System.out.println(countDivisibleSimple(a2, 5)); // 3

		int[] a3 = { 3, 3, 3, 3 };
		System.out.println(countDivisible(a3, 3)); // 4
		System.out.println(countDivisible(a3, 6)); // 0
	}
}
