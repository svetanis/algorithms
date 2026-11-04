package com.svetanis.algorithms.bits.xor;

// 137. Single Number II
//
// Input: a non-empty int array in which every number appears three times except one, which appears
// once. Returns that one. On LeetCode: 1 <= nums.length <= 3 * 10^4, any int value.
//
// The one idea: ^ only remembers whether a slot was seen an odd or even number of times, and three
// copies are odd, so the triples would not cancel. Count instead, one slot at a time: how many
// numbers have a 1 in slot i. A number that appears three times adds 3 or 0 to that count, so
// count % 3 is the single's digit in slot i. Doing it for all 32 slots, sign slot included,
// rebuilds the single, negative or not.
//
// Time: O(n) -- 32 passes over the array, 32 * n steps.
// Space: O(1) -- two ints.
//
// Siblings: SingleNumber (136) has the others twice each, where ^ alone works.

public final class SingleNumberII {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int single = 0;
		for (int bit = 0; bit < 32; bit++) { // WALK all 32 slots: slot 31 rebuilds a negative single
			int bitCount = 0;
			for (int num : a) {
				bitCount += (num >> bit) & 1; // COUNT the 1s in this slot
			}
			bitCount %= 3; // DROP the triples
			single |= (bitCount << bit); // SET: switch the slot on when it is 1
		}
		return single;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 2, 3, 2 };
		System.out.println(single(a1)); // 3

		int[] a2 = { 0, 1, 0, 1, 0, 1, 99 };
		System.out.println(single(a2)); // 99
	}
}
