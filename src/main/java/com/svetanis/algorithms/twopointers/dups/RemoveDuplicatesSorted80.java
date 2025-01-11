package com.svetanis.algorithms.twopointers.dups;

// 80. Remove Duplicates from Sorted Array

// Given a sorted array, remove the duplicates in place 
// such that each element can appear at most twice and return the new length.
// Do not allocate extra space for another array, 
// you must do this in place with constant memory.
// Note that even though we want you to return the new length, 
// make sure to change the original array as well in place

public final class RemoveDuplicatesSorted80 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int remove(int[] a) {
		int n = a.length;
		if (n <= 2) {
			return n;
		}
		int index = 0;
		for (int curr : a) {
			if (index < 2 || curr != a[index - 2]) {
				a[index] = curr;
				index++;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 1, 2, 2, 3 };
		System.out.println(remove(a1)); // 5

		int[] a2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
		System.out.println(remove(a2)); // 7
	}
}
