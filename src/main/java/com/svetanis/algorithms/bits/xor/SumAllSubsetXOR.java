package com.svetanis.algorithms.bits.xor;

// 1863. Sum of All Subset XOR Totals
//
// Input: an int array a. The XOR total of a subset is the ^ of its numbers, and 0 for the empty
// subset. Returns the sum of the XOR totals of every subset. On LeetCode: 1 <= nums.length <= 12,
// 1 <= nums[i] <= 20.
//
// The one idea: visit every subset once. One call dfs(index, xor) stands for one subset -- the
// numbers chosen so far, all from before index -- and xor is their ^. The call adds its own xor to
// the total, then tries each later number a[i], i >= index, as the next one chosen. Choosing only
// later numbers builds each subset in one order only, so none is counted twice. ^ undoes itself, so
// the same ^ a[i] unchooses a[i] before the next try.
//
// Time: O(2^n) -- one call per subset, and each loop step makes exactly one call.
// Space: O(n) -- the recursion is at most n + 1 calls deep.

public final class SumAllSubsetXOR {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n), the recursion

	private int total;

	public int subsetXorSum(int[] a) {
		total = 0; // RESET: the total belongs to this call only
		dfs(a, 0, 0); // START: the empty subset
		return total;
	}

	private void dfs(int[] a, int index, int xor) {
		total += xor; // RECORD: every call is one subset
		for(int i = index; i < a.length; i++) { // TRY each later number
			xor ^= a[i]; // CHOOSE a[i]
			dfs(a, i + 1, xor);
			xor ^= a[i]; // UNCHOOSE: ^ undoes itself
		}
	}

	public static void main(String[] args) {
		SumAllSubsetXOR sas = new SumAllSubsetXOR();
		int[] a1 = { 1, 3 };
		System.out.println(sas.subsetXorSum(a1)); // 6
		int[] a2 = { 5, 1, 6 };
		System.out.println(sas.subsetXorSum(a2)); // 28
		int[] a3 = { 3, 4, 5, 6, 7, 8 };
		System.out.println(sas.subsetXorSum(a3)); // 480
	}
}
