package com.svetanis.algorithms.bits.xor;

import com.svetanis.java.base.utils.Print;

// 645. Set Mismatch

// XOR the array with 1..n: dup appears three times, missing once and
// every other number twice, so dup ^ missing is left. They differ in
// some slot; split both lists on it, and in each half everything but
// one of the two appears an even number of times. A last pass says
// which of them is the duplicate.

public final class SetMismatch645 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int[] setMismatch(int[] a) {
		int xor = xor(a);
		int x = separate(a, xor);
		int y = xor ^ x; // xor is x ^ y, so ^ x leaves y
		return segregate(a, x, y);
	}

	private static int xor(int[] a) {
		int xor = 0;
		// xor all numbers in a[] and from 1 to n
		for (int i = 1; i <= a.length; i++) {
			xor = xor ^ i ^ a[i - 1];
		}
		return xor;
	}

	private static int xor2(int[] a) {
		int n = a.length;
		int xor = n;
		// xor all numbers from 1 to n
		for (int i = 1; i < n; i++) {
			xor = xor ^ i;
		}
		// xor all numbers in a[]
		for (int i = 0; i < n; i++) {
			xor = xor ^ a[i];
		}
		return xor;
	}

	private static int separate(int[] a, int xor) {
		// a slot where dup and missing differ
		int rsb = xor & -xor; // xor & ~(xor - 1);
		int x = 0;
		for (int i = 1; i <= a.length; ++i) {
			if ((i & rsb) > 0) { // only the half with that slot on
				x = x ^ i;
			}
			if ((a[i - 1] & rsb) > 0) {
				x = x ^ a[i - 1];
			}
		}
		return x;
	}

	// find which one is a duplicate
	private static int[] segregate(int[] a, int x, int y) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				return new int[] { x, y };
			}
		}
		return new int[] { y, x };
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 4 };
		Print.print(setMismatch(a1)); // [2,3]

		int[] a2 = { 1, 1 };
		Print.print(setMismatch(a2)); // [1,2]
	}
}
