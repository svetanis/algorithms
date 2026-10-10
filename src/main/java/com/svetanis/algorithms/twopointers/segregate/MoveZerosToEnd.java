package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// 283. Move Zeroes
//
// Input: an array of integers.
// Return: nothing -- the array is changed in place so that every 0 is at the back and
// the other numbers keep their original order.
//
// The one idea: two pointers moving the same way. fast reads every number once; slow
// is the next slot for a number that is kept. Everything before slow is finished: the
// non-zeros seen so far, in their original order. A non-zero at fast is written at
// slow, and slow moves; a zero is passed over.
//
// Two methods, the same problem:
//   segregate -- OVERWRITES a[slow] with a[fast], then fills the tail with zeros in a
//                second pass
//   moveZeros -- SWAPS a[slow] and a[fast]: a zero is carried rightward for free, one pass
// Sibling: twopointers.MoveZeros -- the same two methods on int[], plus the swap version
//   on a List<Integer>.
//
// Time: O(n) -- fast visits each number once; segregate's fill adds at most n writes.
// Space: O(1).

public final class MoveZerosToEnd {

	public static void segregate(int[] a) {
		int n = a.length;
		int slow = 0;                          // START: the next slot for a non-zero
		for (int fast = 0; fast < n; fast++) { // READ every number once
			if (a[fast] != 0) {
				a[slow++] = a[fast];           // WRITE: pass 1 compacts the non-zeros
			}
		}
		while (slow < n) {
			a[slow++] = 0;                     // FILL: pass 2 writes the zeros into the tail
		}
	}

	public static void moveZeros(int[] a) {
		int n = a.length;
		int slow = 0;                          // START: the next slot for a non-zero
		for (int fast = 0; fast < n; fast++) { // READ every number once
			if (a[fast] != 0) {
				int temp = a[slow];
				a[slow] = a[fast];             // WRITE it at slow
				a[fast] = temp;                // SWAP: the zero is pushed rightward for free
				slow++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };
		segregate(a);
		System.out.println(Arrays.toString(a)); // [1, 9, 8, 4, 2, 7, 6, 9, 0, 0, 0, 0]

		int[] a1 = { 0, 1, 0, 3, 12 };
		moveZeros(a1);
		System.out.println(Arrays.toString(a1)); // [1, 3, 12, 0, 0]

		int[] a2 = { 0 };
		moveZeros(a2);
		System.out.println(Arrays.toString(a2)); // [0]
	}
}