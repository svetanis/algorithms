package com.svetanis.algorithms.twopointers.quadruple;

// Maximum product of four numbers
//
// Input: an array of integers.
// Return: the largest product of four numbers at four different positions; -1 when there
// are fewer than four numbers.
//
// The one idea: signs decide, so only the ends of the sorted order matter. The best four are
// the four LARGEST, the four SMALLEST (four negatives make a positive), or the two smallest
// with the two largest (two negatives times two positives). One pass keeps the top four and
// the bottom four, with no sort.
//
// Siblings:
//   twopointers.quadruple.MaxProductQuadrupleSorting -- sorts, then reads the same three
//                                                      products off both ends: O(n log n)
//   twopointers.triplet.MaxProductTriplet           -- three numbers: two candidates, not three
//
// Time: O(n) -- two passes.
// Space: O(1).

public final class MaxProductQuadruple {

	public static int quadruple(int[] a) {
		int n = a.length;
		if (n < 4) {
			return -1;                                   // RETURN -1: no four numbers to multiply
		}
		int[] max = maxQuadruple(a);                     // the four largest, largest first
		int[] min = minQuadruple(a);                     // the four smallest, smallest first

		int x = max[0] * max[1] * max[2] * max[3];       // COMPARE the four largest,
		int y = min[0] * min[1] * min[2] * min[3];       // the four smallest,
		int z = min[0] * min[1] * max[0] * max[1];       // and two of each
		return Math.max(x, Math.max(y, z));
	}

	private static int[] minQuadruple(int[] a) {
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		int third = Integer.MAX_VALUE;
		int fourth = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < first) {                          // RECORD a new smallest: the rest shift up
				fourth = third;
				third = second;
				second = first;
				first = a[i];
			} else if (a[i] < second) {
				fourth = third;
				third = second;
				second = a[i];
			} else if (a[i] < third) {
				fourth = third;
				third = a[i];
			} else if (a[i] < fourth) {
				fourth = a[i];
			}
		}
		return new int[] { first, second, third, fourth };
	}

	private static int[] maxQuadruple(int[] a) {
		int first = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		int third = Integer.MIN_VALUE;
		int fourth = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > first) {                          // RECORD a new largest: the rest shift down
				fourth = third;
				third = second;
				second = first;
				first = a[i];
			} else if (a[i] > second) {
				fourth = third;
				third = second;
				second = a[i];
			} else if (a[i] > third) {
				fourth = third;
				third = a[i];
			} else if (a[i] > fourth) {
				fourth = a[i];
			}
		}
		return new int[] { first, second, third, fourth };
	}

	public static void main(String[] args) {
		int[] a = { 10, 3, 5, 6, 20 };
		System.out.println(quadruple(a)); // 6000

		int[] a1 = { -10, -3, -5, -6, -20 };
		System.out.println(quadruple(a1)); // 6000

		int[] a2 = { 1, -4, 3, -6, 7, 0 };
		System.out.println(quadruple(a2)); // 504
	}
}
