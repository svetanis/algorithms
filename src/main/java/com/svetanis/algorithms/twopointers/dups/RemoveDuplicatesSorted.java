package com.svetanis.algorithms.twopointers.dups;

import java.util.Arrays;

// 26. Remove Duplicates from Sorted Array
//
// Input: an array sorted smallest first, 1 <= length <= 3 * 10^4.
// Return: k, the number of distinct values. The array is rewritten in place so its first
// k slots hold each value once, in order; whatever sits after them does not matter.
//
// The one idea: one pointer (fast) READS every number once; the other (slow) is the next
// free slot of the answer, and everything behind it is final. The array is sorted, so a
// number is new exactly when it differs from the last one WRITTEN, a[slow - 1].
//
// Three methods, the same loop -- they differ only in what slow means:
//   remove(int[])  -- slow is the next free slot; fast starts at 0, and index 0 is kept
//                     by the slow == 0 test; returns slow
//   remove1(int[]) -- slow is the LAST WRITTEN index; fast starts at 1; returns slow + 1
//   remove2(int[]) -- slow is the next free slot, seeded at 1 because slot 0 is always
//                     kept; returns slow
// remove1 and remove2 open with a guard for the empty array: without it, slow + 1 and
// the seed of 1 would both report a length of 1. LC 26 never passes an empty array.
//
// Siblings:
//   RemoveDuplicatesSorted80 -- at most two copies: compare two slots back in the written part
//   RemoveDuplicatesInPlace  -- remove1's loop on a List<Integer>, where != must compare ints
//
// Time: O(n) -- every number is read once.
// Space: O(1).

public final class RemoveDuplicatesSorted {

	public static int remove(int[] a) {
		int slow = 0;                                 // START: nothing written yet
		for (int fast = 0; fast < a.length; fast++) { // READ every number once
			int curr = a[fast];
			if (slow == 0 || curr != a[slow - 1]) {   // KEEP: compare against what was WRITTEN
				a[slow] = curr;                       // WRITE it into the next free slot
				slow++;                               // MOVE the writer
			}
		}
		return slow;                                  // RETURN: slow is a COUNT here
	}

	public static int remove1(int[] a) {
		if (a.length == 0) {
			return 0;                                 // RETURN: slow + 1 below would report 1
		}
		int slow = 0;                                 // START: index 0 is kept, the last written
		for (int fast = 1; fast < a.length; fast++) { // READ from index 1
			if (a[fast] != a[slow]) {                 // KEEP: new against the last kept
				slow++;                               // MOVE to the next free slot first
				a[slow] = a[fast];                    // WRITE it there
			}
		}
		return slow + 1;                              // RETURN: slow is an INDEX, the count is one more
	}

	public static int remove2(int[] a) {
		if (a.length == 0) {
			return 0;                                 // RETURN: the seed of 1 below would report 1
		}
		int slow = 1;                                 // START: slot 0 is kept; slow is the next free slot
		for (int fast = 1; fast < a.length; fast++) { // READ from index 1
			if (a[fast] != a[slow - 1]) {             // KEEP: new against the last kept
				a[slow] = a[fast];                    // WRITE it into the next free slot
				slow++;                               // MOVE the writer
			}
		}
		return slow;                                  // RETURN: slow is a COUNT here
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2 };
		int[] a2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

		System.out.println(remove(a.clone())); // 2
		System.out.println(remove(a2.clone())); // 5
		System.out.println(remove1(a.clone())); // 2
		System.out.println(remove1(a2.clone())); // 5
		System.out.println(remove2(a.clone())); // 2
		System.out.println(remove2(a2.clone())); // 5

		int[] b = a2.clone();
		int k = remove(b);
		System.out.println(Arrays.toString(Arrays.copyOf(b, k))); // [0, 1, 2, 3, 4]
	}
}