package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// Count triplets with a given product
//
// Input: an array of DISTINCT positive integers and a target k.
// Return: how many triples of positions have a[i] * a[j] * a[l] == k.
//
// The one idea: sort, FIX the smallest factor a[i] -- only when it divides k -- and converge
// on the numbers to its right for a pair with product k / a[i]. With positive numbers the
// product grows with either pointer, so the moves are Two Sum's: too small, drop left; too
// big, drop right. On a hit both move: with distinct values, neither number has a second
// partner giving the same product.
//
// Both rules on the input matter. With repeats, moving both pointers on a hit skips pairs:
// six 1s with k = 1 hold 20 triplets, and this counts 6. A zero or a negative number breaks
// "the product grows with either pointer".
//
// Time: O(n^2) -- n fixed factors, an O(n) converging pass for each.
// Space: O(1) besides the sort.

public final class CountTripletsGivenProduct {

	public static int count(int[] a, int k) {
		Arrays.sort(a);                                  // SORT: the converging pass needs it

		int count = 0;
		for (int i = 0; i < a.length; i++) {             // FIX the smallest factor a[i]
			if (a[i] != 0 && k % a[i] == 0) {            // SKIP a factor that does not divide k
				int target = k / a[i];                   // the other two must multiply to this
				count += count(a, target, i);
			}
		}
		return count;
	}

	private static int count(int[] a, int target, int first) {
		int count = 0;
		int left = first + 1;                            // START: the two ends right of first
		int right = a.length - 1;
		while (left < right) {                           // STOP: a pair needs two positions
			int prod = a[left] * a[right];               // COMPARE
			if (prod > target) {
				right--;                                 // DROP RIGHT: too big even with the smallest
			} else if (prod < target) {
				left++;                                  // DROP LEFT: too small even with the largest
			} else {
				left++;                                  // FOUND: MOVE both, distinct values
				right--;
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 1, 1, 1, 1 };
		System.out.println(count(a, 1)); // 6 -- repeats break the input rule; the true count is 20

		int[] a1 = { 1, 4, 6, 2, 3, 8 };
		System.out.println(count(a1, 24)); // 3

		int[] a2 = { 0, 4, 6, 2, 3, 8 };
		System.out.println(count(a2, 18)); // 0
	}
}
