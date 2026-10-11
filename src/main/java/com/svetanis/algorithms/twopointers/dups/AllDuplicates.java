package com.svetanis.algorithms.twopointers.dups;

import java.util.ArrayList;
import java.util.List;

// 442. Find All Duplicates in an Array
//
// Input: an array of n integers, each in [1, n]; every value appears once or twice.
// Return: every value that appears twice, in any order. O(n) time, and no extra space
// besides the output.
//
// The one idea: the values are the positions 1..n, so every value has a home -- value v
// belongs at index v - 1. Swap the number at i to its home until slot i holds a number
// whose home already holds that value. Afterwards every value present sits at home once,
// so a slot that does not hold its own value, i + 1, holds the second copy of something:
// that something is a duplicate.
//
// Siblings -- the same "a value is a position" idea, with one repeated value:
//   DuplicateInPlaceMarking -- flips the sign at a[v] instead of moving v home
//   DuplicateInPlaceFloyd   -- reads i -> a[i] as a linked list; the cycle's entrance is the repeat
//
// Time: O(n) -- each swap puts one value home for good, so at most n swaps, plus one scan.
// Space: O(1) besides the output; the array is rearranged in place.

public final class AllDuplicates {

	public static List<Integer> dups(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			while (a[i] != a[a[i] - 1]) { // STOP once the home of a[i] already holds that value
				swap(a, i, a[i] - 1);     // SWAP: a[i] goes home, to index a[i] - 1, for good
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (a[i] != i + 1) {          // COMPARE: slot i does not hold its own value
				list.add(a[i]);           // RECORD: so it holds the second copy of a[i]
			}
		}
		return list;
	}

	private static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 3, 2, 7, 8, 2, 3, 1 };
		System.out.println(dups(a1)); // [3, 2]
		int[] a2 = { 1, 1, 2 };
		System.out.println(dups(a2)); // [1]
		int[] a3 = { 1 };
		System.out.println(dups(a3)); // []
	}
}
