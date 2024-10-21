package com.svetanis.algorithms.search.duplicate;

// 287. Find the Duplicate Number

// given an array of n + 1 integers in
// the range from 1 to n inclusive
// there is only one repeated number in nums 
// find this repeated number

public final class DuplicateInPlaceBinary {
	// Time Complexity: O(n log n)

	public static int duplicate(int[] a) {
		int low = 0;
		int high = a.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			int count = count(mid, a);
			if (count > mid) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		// when low == high, duplicate is found
		return low;
	}

	private static int count(int mid, int[] a) {
		int count = 0;
		for (int element : a) {
			if (element <= mid) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
