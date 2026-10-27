package com.svetanis.algorithms.prefixsum;

// 1248. Count Number of Nice Subarrays
//
// Input: an array a and an integer k. A subarray is nice when it holds exactly k odd numbers.
// Returns the number of nice subarrays. On LeetCode: 1 <= a.length <= 50,000,
// 1 <= a[i] <= 10^5, 1 <= k <= a.length.
//
// The one idea: count runs from left to right as the number of odd values seen so far. The
// subarray from start + 1 to i holds (count at i) - (count at start) odd numbers, so it is nice
// exactly when the count at start was (count at i) - k. One cell prefix[c] holds how many prefixes
// seen so far -- the empty one included -- contain exactly c odd numbers. The count never
// exceeds n, so an array of n + 1 cells replaces a map. prefix[0] starts at 1 for the empty
// prefix, so a subarray starting at index 0 is counted the same way.
//
// Time: O(n) -- one pass, one array read and one write per number
// Space: O(n) -- the n + 1 cells of prefix
//
// The answer is at most n(n + 1) / 2, about 1.25 * 10^9 for n = 50,000, which fits in an int.

public final class CountNiceSubArrs {

	public static int countSubArrs(int[] a, int k) {
		int[] prefix = new int[a.length + 1]; // CELLS: prefix[c] = prefixes with c odd numbers
		prefix[0] = 1; // SEED: the empty prefix has 0 odd numbers
		int count = 0;
		int total = 0;
		for (int num : a) {
			count += num % 2 == 0 ? 0 : 1; // COUNT the odd numbers so far
			if (count - k >= 0) {
				total += prefix[count - k]; // RECORD: every start with k fewer odds
			}
			prefix[count]++; // ADD this prefix after the lookup
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 1, 1 };
		System.out.println(countSubArrs(a1, 3)); // 2

		int[] a2 = { 2, 4, 6 };
		System.out.println(countSubArrs(a2, 1)); // 0

		int[] a3 = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
		System.out.println(countSubArrs(a3, 2)); // 16
	}
}
