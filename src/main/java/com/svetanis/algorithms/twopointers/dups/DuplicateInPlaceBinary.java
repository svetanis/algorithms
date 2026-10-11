package com.svetanis.algorithms.twopointers.dups;

// 287. Find the Duplicate Number
//
// Input: n + 1 integers, each in [1, n], 1 <= n <= 10^5. All of them appear once except
// one value, which appears two or more times.
// Return: that repeated value -- without modifying the array, in O(1) extra space.
//
// The one idea: binary search on the VALUE, not the array. Count the numbers <= mid:
// without a repeat among 1..mid there are at most mid of them, so a count above mid
// means the repeated value is <= mid. Each guess costs one pass over the array.
//
// Siblings -- the same problem:
//   DuplicateInPlaceFloyd   -- O(n): reads i -> a[i] as a linked list; the cycle's entrance is the repeat
//   DuplicateInPlaceMarking -- O(n), but flips signs in the array, which 287 forbids
//   DuplicateInPermutationSum, DuplicateInPermutationXor -- need every value 1..n present; 287 does not promise it
//
// Time: O(n log n) -- log n guesses, one pass each.
// Space: O(1), and the array is not modified.

public final class DuplicateInPlaceBinary {

	public static int duplicate(int[] a) {
		int low = 0;                      // START: the repeat is a value in [low, high]
		int high = a.length - 1;          //        high = n, the largest value allowed
		while (low < high) {              // STOP when one value is left
			int mid = low + (high - low) / 2;
			int count = count(mid, a);
			if (count > mid) {            // COMPARE: more than mid values in 1..mid, so the repeat is <= mid
				high = mid;               // KEEP the lower half, mid included
			} else {
				low = mid + 1;            // DROP mid and everything below it
			}
		}
		return low;                       // RETURN: low == high, the one value left
	}

	private static int count(int mid, int[] a) {
		int count = 0;
		for (int element : a) {
			if (element <= mid) {
				count++;                  // COUNT a number at or below mid
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
