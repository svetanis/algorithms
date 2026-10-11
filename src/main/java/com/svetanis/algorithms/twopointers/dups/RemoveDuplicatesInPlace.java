package com.svetanis.algorithms.twopointers.dups;

import static java.util.Arrays.asList;

import java.util.List;

// Remove Duplicates from Sorted Array (LC 26) -- the same loop on a List<Integer>
//
// Input: a list sorted smallest first, at least one number long.
// Return: k, the number of distinct values. The list is rewritten in place so its first
// k positions hold each value once, in order; no extra memory.
//
// The one idea: slow is the index of the last value KEPT; fast reads every number once.
// The list is sorted, so a number is new exactly when it differs from arr.get(slow).
// The comparison must be made between ints: != on two Integers compares the objects,
// which only agrees with the values inside the cache of -128..127.
//
// Siblings:
//   RemoveDuplicatesSorted   -- the int[] versions; its remove1 also keeps slow on the last index kept
//   RemoveDuplicatesSorted80 -- at most two copies: compare two slots back in the written part
//
// Time: O(n) -- every number is read once; get and set are O(1) on an ArrayList or asList.
// Space: O(1).

public final class RemoveDuplicatesInPlace {

	public static int removeDups(List<Integer> arr) {
		int slow = 0;                                   // START: index 0 is kept, the last value written
		for (int fast = 0; fast < arr.size(); fast++) { // READ every number once
			// unbox before comparing: != on two Integers compares
			// references, which only works inside the -128..127 cache
			int curr = arr.get(fast);
			if (curr != arr.get(slow)) {                // KEEP: new against the last value kept
				slow++;                                 // MOVE to the next free position first
				arr.set(slow, curr);                    // WRITE it there
			}
		}
		return slow + 1;                                // RETURN: slow is an INDEX, the count is one more
	}

	public static void main(String[] args) {
		System.out.println(removeDups(asList(0, 0, 1, 1, 1, 2, 2))); // 3
		System.out.println(removeDups(asList(1, 2, 3))); // 3
		System.out.println(removeDups(asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))); // 1
		System.out.println(removeDups(asList(200, 200, 200, 300, 300))); // 2
	}
}
