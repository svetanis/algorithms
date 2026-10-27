package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 2364. Count Number of Bad Pairs
//
// Input: an array a. A pair of indices i < j is bad when j - i != a[j] - a[i]. Returns the number
// of bad pairs, as a long. On LeetCode: 1 <= a.length <= 10^5, 1 <= a[i] <= 10^9.
//
// The one idea: j - i == a[j] - a[i] rearranges to j - a[j] == i - a[i], so a pair is good exactly
// when its two indices have the same value of (index - value). Counting the good pairs is easier
// than counting the bad ones: one map entry is one value of (index - value) and how many earlier
// indices had it. Index i has i earlier indices in all; the ones with the same key make good pairs
// with it and the rest make bad pairs, so i - (count of the same key) bad pairs end at i.
//
// Time: O(n) -- one pass, one map lookup and one update per index, O(1) on average
// Space: O(n) -- at most n distinct keys
//
// The total can reach n(n - 1) / 2, about 5 * 10^9, so count is a long.

public final class CountBadPairs {

	public static long sum(int[] a) {
		Map<Integer, Integer> map = new HashMap<>(); // MAP: index - value -> earlier count
		long count = 0;
		for (int i = 0; i < a.length; i++) {
			int diff = i - a[i]; // KEY: equal keys make a good pair
			count += i - map.getOrDefault(diff, 0); // COUNT: earlier indices minus the good ones
			map.merge(diff, 1, Integer::sum); // ADD after the count, so i never pairs with itself
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 1, 3, 3 };
		System.out.println(sum(a1)); // 5

		int[] a2 = { 1, 2, 3, 4, 5 };
		System.out.println(sum(a2)); // 0
	}
}
