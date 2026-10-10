package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate 0s and 1s, by counting
//
// Input: an array that holds only the values 0 and 1.
// Return: nothing -- the array is rewritten in place with every 0 before every 1.
//
// The one idea: the answer is fixed by one number, how many 0s there are. Count them,
// then write that many 0s and fill the rest with 1s. It overwrites rather than moves,
// which is fine because two 0s cannot be told apart.
//
// Sibling: twopointers.segregate.SegregateZerosAndOnesSingleTraversal -- one pass with
//   two converging cursors and swaps, instead of a count and a rewrite.
//
// Time: O(n) -- two passes: one to count, one to write.
// Space: O(1).

public final class SegregateZerosAndOnesCount {

	public static void segregate(int[] a) {
		int count = countZeros(a);           // COUNT: the 0s will fill [0, count)
		for (int i = 0; i < a.length; ++i) {
			if (i < count) {
				a[i] = 0;                    // WRITE a 0 in the first count slots
			} else {
				a[i] = 1;                    // WRITE a 1 everywhere after them
			}
		}
	}

	private static int countZeros(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == 0) {
				count++;                     // COUNT the 0s
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 0, 1, 0, 1, 1, 1 };
		segregate(a);
		System.out.println(Arrays.toString(a)); // [0, 0, 1, 1, 1, 1]
	}
}