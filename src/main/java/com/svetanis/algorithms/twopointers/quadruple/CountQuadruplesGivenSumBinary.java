package com.svetanis.algorithms.twopointers.quadruple;

// Count quadruples with a given sum from four sorted arrays, by binary search
//
// Input: four sorted arrays of the same length n, each holding distinct integers, and a
// target.
// Return: how many ways there are to take one number from each array so that the four
// sum to the target.
//
// The one idea: three numbers fix the fourth. For every (x, y, z) from the first three
// arrays, target - x - y - z is forced, and a binary search of a4 says whether it is there.
// a4 holds distinct values, so the fourth number is there at most once and a hit counts 1.
//
// Siblings -- the same count:
//   twopointers.quadruple.CountQuadruplesGivenSumTwoPointers -- fixes two numbers and
//                                                              converges on a3 and a4: O(n^3)
//   twopointers.quadruple.CountQuadruplesGivenSumHashing     -- counts the a1 + a2 sums in a
//                                                              map: O(n^2)
//
// Time: O(n^3 log n) -- n^3 triples, a binary search each.
// Space: O(log n) for the recursion of the binary search.

public final class CountQuadruplesGivenSumBinary {

	public static int count(int[] a1, int[] a2, int[] a3, int[] a4, int target) {
		int n = a1.length;
		int count = 0;
		for (int x = 0; x < n; x++) {                    // FIX a number from each of the
			for (int y = 0; y < n; y++) {                // first three arrays
				for (int z = 0; z < n; z++) {
					int sum = a1[x] + a2[y] + a3[z];
					if (isBinary(a4, 0, n - 1, target - sum)) {
						count++;                         // COUNT: the forced fourth number is in a4
					}
				}
			}
		}
		return count;
	}

	private static boolean isBinary(int[] a, int start, int end, int x) {
		if (end < start) {
			return false;                                // STOP: the range is empty, x is absent
		}
		int mid = start + (end - start) / 2;
		if (a[mid] == x) {
			return true;                                 // FOUND
		} else if (x > a[mid]) {
			return isBinary(a, mid + 1, end, x);         // DROP LEFT half: everything there is too small
		} else {
			return isBinary(a, start, mid - 1, x);       // DROP RIGHT half: everything there is too big
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 5, 6 };
		int[] a2 = { 2, 3, 7, 8 };
		int[] a3 = { 1, 4, 6, 10 };
		int[] a4 = { 2, 4, 7, 8 };
		System.out.println(count(a1, a2, a3, a4, 30)); // 4
	}
}
