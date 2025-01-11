package com.svetanis.algorithms.twopointers.dups;

// 26. Remove Duplicates from Sorted Array

// Remove duplicates from Sorted Array
// Given a sorted array, remove the duplicates in place 
// such that each element appears only once and return the new length.

public final class RemoveDuplicatesSorted {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int remove(int[] a) {
		int index = 0; // index of the next non-duplicate element
		for (int curr : a) {
			if (index == 0 || curr != a[index - 1]) {
				a[index] = curr;
				index++;
			}
		}
		return index;
	}

	public static int remove1(int[] a) {
		int next = 0; // index of the next non-duplicate element
		for (int i = 1; i < a.length; i++) {
			if (a[i] != a[next]) {
				next++;
				a[next] = a[i];
			}
		}
		return next + 1;
	}

	public static int remove2(int[] a) {
		int next = 1; // index of the next non-duplicate element
		for (int i = 1; i < a.length; i++) {
			if (a[i] != a[next - 1]) {
				a[next] = a[i];
				next++;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2 };
		System.out.println(remove(a)); // 2

		int[] a2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println(remove(a2)); // 5
	}
}