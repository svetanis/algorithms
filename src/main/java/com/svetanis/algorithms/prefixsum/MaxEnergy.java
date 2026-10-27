package com.svetanis.algorithms.prefixsum;

// 3147. Taking Maximum Energy From the Mystic Dungeon
//
// Input: an array energy of n magicians' energies (some negative) and a jump k. Starting at any
// magician i, you absorb energy[i], then jump to i + k, absorb that, and so on until i + k is past
// the end. Returns the largest total energy over all starting magicians. On LeetCode:
// 1 <= n <= 10^5, -1000 <= energy[i] <= 1000, 1 <= k <= n - 1. k = 0 is outside that range and
// returns Integer.MIN_VALUE: the outer loop is empty, so no walk is scored.
//
// The one idea: every walk ends at one of the last k magicians, n - k .. n - 1, because from any
// of them the next jump is past the end. A walk that starts at j collects energy[j],
// energy[j + k], ... down to that end: a suffix sum along the chain j, j + k, j + 2k, ... So
// start from each of the last k magicians and walk backwards by k; sum then holds the total of the
// walk that starts at j. Every start j lies on exactly one chain, so every walk is scored once.
//
// Time: O(n) -- the k backward walks together visit each index once
// Space: O(1) -- two variables besides the input

public final class MaxEnergy {

	public static int maxEnergy(int[] energy, int k) {
		int max = Integer.MIN_VALUE;
		int n = energy.length;
		for (int i = n - k; i < n; i++) { // END: each of the last k magicians ends one chain
			for (int j = i, sum = 0; j >= 0; j -= k) { // BACK: walk the chain toward its start
				sum += energy[j]; // SUFFIX: the total of the walk that starts at j
				max = Math.max(max, sum); // RECORD: start the walk at j
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 2, -10, -5, 1 };
		System.out.println(maxEnergy(a1, 3)); // 3
		int[] a2 = { -2, -3, -1 };
		System.out.println(maxEnergy(a2, 2)); // -1
	}
}
