package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1196. How Many Apples Can You Put into the Basket
//
// Input: weight -- the most the basket can carry (5000 on LeetCode, a parameter here); a[i] -- the
// weight of apple i. Returns the most apples whose weights add up to at most the basket's limit.
// On LeetCode: 1 <= a.length <= 10^3, 1 <= a[i] <= 10^3, so the running total stays far inside an
// int.
//
// The one idea: every apple counts as one, whatever it weighs, so only how many fit matters. Take
// the lightest apple, then the next lightest, and stop at the first one that does not fit. This is
// safe because any k apples weigh at least as much as the k lightest apples: if some k apples fit,
// the k lightest fit too. And once the next lightest apple does not fit, every apple left is at
// least as heavy, so none of them fits either and there is nothing to come back for. Sorting puts
// the apples in that order; the running total is the weight already in the basket.
//
// Time: O(n log n) -- the sort; the scan after it is one pass.
// Space: O(log n) -- the sort works inside the caller's array (and reorders it); its recursion
// keeps O(log n) frames.

public final class MaxNumOfApples {

	public static int countApples(int weight, int[] a) {
		Arrays.sort(a); // SORT: lightest first
		int sum = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			sum += a[i]; // TAKE the next lightest apple
			if (sum > weight) {
				return i; // RETURN: i apples fitted; this one did not
			}
		}
		return n; // RETURN: every apple fitted
	}

	public static void main(String[] args) {
		int[] a = { 600, 1000, 950, 200, 400 };
		System.out.println(countApples(5000, a)); // 5
	}
}
