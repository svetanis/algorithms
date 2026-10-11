package com.svetanis.algorithms.twopointers.dups;

import java.util.Arrays;

// 80. Remove Duplicates from Sorted Array II
//
// Input: an array sorted smallest first, 1 <= length <= 3 * 10^4.
// Return: k, the new length. The array is rewritten in place so its first k slots hold
// each value at most twice, in order; whatever sits after them does not matter.
// Constant extra memory.
//
// The one idea: LC 26's read/write loop, with the keep test looking two slots back in
// the WRITTEN part: curr != a[slow - 2]. The array is sorted and [0, slow) already obeys
// the rule, so if a[slow - 2] equals curr then a[slow - 1] does too -- two copies are
// written and this is a third. Looking back in the READ part, a[fast - 2], is wrong:
// slots behind the reader may already hold a number moved there.
// At most k copies is the same loop with 2 changed to k.
//
// Sibling: RemoveDuplicatesSorted -- one copy each: compare one slot back, a[slow - 1].
//
// Time: O(n) -- every number is read once.
// Space: O(1).

public final class RemoveDuplicatesSorted80 {

	public static int remove(int[] a) {
		int n = a.length;
		if (n <= 2) {
			return n;                                 // RETURN: two or fewer numbers are all kept
		}
		// slow = the WRITE cursor: everything in [0, slow) is finished
		// fast = the READ cursor: visits every element exactly once
		int slow = 0;                                 // START: nothing written yet
		for (int fast = 0; fast < a.length; fast++) { // READ every number once
			int curr = a[fast];
			if (slow < 2 || curr != a[slow - 2]) {    // KEEP: 26 with -1 changed to -2, in the WRITTEN part
				a[slow] = curr;                       // WRITE it into the next free slot
				slow++;                               // MOVE the writer
			}
		}
		return slow;                                  // RETURN how many are kept
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 1, 2, 2, 3 };
		int k1 = remove(a1);
		System.out.println(k1); // 5
		System.out.println(Arrays.toString(Arrays.copyOf(a1, k1))); // [1, 1, 2, 2, 3]

		int[] a2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
		int k2 = remove(a2);
		System.out.println(k2); // 7
		System.out.println(Arrays.toString(Arrays.copyOf(a2, k2))); // [0, 0, 1, 1, 2, 3, 3]
	}
}
