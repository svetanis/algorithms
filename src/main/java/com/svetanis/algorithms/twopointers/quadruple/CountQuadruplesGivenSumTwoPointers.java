package com.svetanis.algorithms.twopointers.quadruple;

// Count quadruples with a given sum from four sorted arrays, by two pointers
//
// Input: four sorted arrays of the same length n, each holding distinct integers, and a
// target.
// Return: how many ways there are to take one number from each array so that the four
// sum to the target.
//
// The one idea: FIX one number from a1 and one from a2; what is left is counting pairs
// across two sorted arrays with a given sum. Start at the smallest of a3 and the largest of
// a4: a sum too small can only grow by dropping the a3 number, a sum too big only shrink by
// dropping the a4 number. On a hit both move: with distinct values, neither number has a
// second partner.
//
// Siblings -- the same count:
//   twopointers.quadruple.CountQuadruplesGivenSumBinary  -- fixes three numbers, binary
//                                                          searches the fourth: O(n^3 log n)
//   twopointers.quadruple.CountQuadruplesGivenSumHashing -- counts the a1 + a2 sums in a
//                                                          map: O(n^2)
//   twopointers.pairs.CountPairsGivenSum2SortedTwoPointers -- the pair count on its own
//
// Time: O(n^3) -- n^2 fixed pairs, an O(n) converging pass for each.
// Space: O(1).

public final class CountQuadruplesGivenSumTwoPointers {

	public static int count(int[] a1, int[] a2, int[] a3, int[] a4, int target) {
		int n = a1.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {                // FIX one number from a1 and one from a2
				int sum = a1[i] + a2[j];
				count += countPairs(a3, a4, target - sum); // COUNT the pairs that complete them
			}
		}
		return count;
	}

	private static int countPairs(int[] a1, int[] a2, int k) {
		int n = a1.length;
		int m = a2.length;
		int left = 0;                                    // START: the smallest of a1
		int right = m - 1;                               // START: the largest of a2
		int count = 0;
		while (left < n && right >= 0) {                 // STOP: one array ran out; right reaches 0
			int sum = a1[left] + a2[right];              // COMPARE
			if (sum == k) {
				count++;                                 // FOUND: MOVE both, distinct values
				++left;
				--right;
			} else if (sum < k) {
				left++;                                  // DROP LEFT: too small even with the largest
			} else {
				right--;                                 // DROP RIGHT: too big even with the smallest
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 5, 6 };
		int[] a2 = { 2, 3, 7, 8 };
		int[] a3 = { 1, 4, 6, 10 };
		int[] a4 = { 2, 4, 7, 8 };
		System.out.println(count(a1, a2, a3, a4, 30)); // 4

		int[] b1 = { 5, 7, 12 };
		int[] b2 = { 1, 3, 10 };
		int[] b3 = { 2, 6, 7 };
		int[] b4 = { 3, 4, 10 };
		System.out.println(count(b1, b2, b3, b4, 26)); // 4
	}
}
