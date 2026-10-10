package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate negative and positive numbers, in place, keeping order
//
// Input: an array of positive and negative integers, no zeros. A 0 is neither branch of
// the loop: if i reaches one before every negative is in front, i never moves again and
// the loop never ends.
// Return: nothing -- the array is rearranged in place so that every negative number
// comes before every positive one, and inside each group the numbers keep their
// original order.
//
// The one idea: i is the next slot for a negative; the negatives must end up filling
// [0, count). When a[i] is positive, swap a[i] with a[i+1], then with a[i+2], and so
// on until a negative lands in a[i]. Swapping one fixed slot with each next slot in
// turn shifts the whole run a[i..j] one step right, so the negative moves to the front
// of the run and the positives inside it keep their order.
//
// Siblings -- the same grouping, but POSITIVES first:
//   twopointers.segregate.SegregatePosAndNegExtraSpaceOrderMatters -- order kept, O(n)
//     time with an O(n) second array
//   twopointers.segregate.SegregatePosAndNegInPlaceNoOrder -- one pass, O(1) space,
//     order not kept
//
// Time: O(n^2) worst case -- every negative is shifted past every positive in front of
//   it; k positives followed by k negatives take k * k swaps.
// Space: O(1) -- swaps in place.

public final class SegregatePosAndNegInPlaceOrderMatters {

	public static void segregate(int[] a) {
		int count = countNegatives(a);         // COUNT: the negatives will fill [0, count)

		int i = 0;                             // START: the next slot for a negative
		int j = i + 1;                         // START: the next number to shift into a[i]
		while (i < count) {                    // STOP: every negative is in front
			if (a[i] < 0) {
				i++;                           // KEEP the negative; MOVE to the next slot
				j = i + 1;
			} else if (a[i] > 0 && j < a.length) {
				swap(a, i, j);                 // SWAP: a[i..j] shifts one step right, order kept
				j++;
			}
		}
	}

	private static int countNegatives(int[] a) {
		int count = 0;
		for (int val : a) {
			if (val < 0) {
				count++;                       // COUNT the negatives
			}
		}
		return count;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { -12, 11, -13, -5, 6, -7, 5, -3, -6 };
		segregate(a);
		System.out.println(Arrays.toString(a)); // [-12, -13, -5, -7, -3, -6, 11, 6, 5]

		int[] a1 = { 1, 2, 3, -1, -2, -3 };
		segregate(a1);
		System.out.println(Arrays.toString(a1)); // [-1, -2, -3, 1, 2, 3]
	}
}
