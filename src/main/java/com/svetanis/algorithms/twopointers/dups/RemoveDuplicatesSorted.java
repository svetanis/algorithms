package com.svetanis.algorithms.twopointers.dups;

// 26. Remove Duplicates from Sorted Array

// Remove duplicates from Sorted Array
// Given a sorted array, remove the duplicates in place 
// such that each element appears only once and return the new length.

public final class RemoveDuplicatesSorted {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	// slow = the WRITE cursor: everything in [0, slow) is finished
	// fast = the READ cursor: visits every element exactly once
	public static int remove(int[] a) {
		int slow = 0;
		for (int fast = 0; fast < a.length; fast++) {
			int curr = a[fast];
			if (slow == 0 || curr != a[slow - 1]) {
				a[slow] = curr;
				slow++;
			}
		}
		return slow; // slow is a COUNT here
	}

	// the same algorithm with slow meaning the LAST WRITTEN index
	// rather than the next free slot, hence the + 1 on the way out
	public static int remove1(int[] a) {
		if (a.length == 0) {
			return 0; // slow is an INDEX here, so the +1 below would report 1
		}
		int slow = 0;
		for (int fast = 1; fast < a.length; fast++) {
			if (a[fast] != a[slow]) {
				slow++;
				a[slow] = a[fast];
			}
		}
		return slow + 1;
	}

	public static int remove2(int[] a) {
		if (a.length == 0) {
			return 0; // slow is seeded to 1, so an empty array would report 1
		}
		int slow = 1; // the next free slot, so no +1 on the way out
		for (int fast = 1; fast < a.length; fast++) {
			if (a[fast] != a[slow - 1]) {
				a[slow] = a[fast];
				slow++;
			}
		}
		return slow;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2 };
		System.out.println(remove(a)); // 2

		int[] a2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println(remove(a2)); // 5
	}
}