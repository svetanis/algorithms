package com.svetanis.algorithms.bits.popcount;

import java.util.Arrays;

// 1356. Sort Integers by The Number of 1 Bits
//
// Input: an int array arr. Returns arr sorted by the number of 1s in each value's binary form,
// fewest first, and values with the same count by value, smallest first. On LeetCode:
// 1 <= arr.length <= 500, 0 <= arr[i] <= 10^4.
//
// The one idea: the order has two keys -- the count of 1s first, the value second. sortByBitsSimple
// gives a comparator both keys: compare the counts, and only on a tie the values (a - b cannot
// overflow, the values are at most 10^4). sortByBits packs both keys into one int: count * 100000 +
// value. A value is below 100000, so a larger count always makes a larger number, whatever the
// values, and an equal count leaves the value to decide; a plain numeric sort then gives the order
// wanted, and % 100000 takes each value back out. It sorts arr itself and returns it.
//
// Time: O(n log n) -- one sort; counting the 1s is O(1) per value.
// Space: sortByBitsSimple O(n) -- a boxed copy of the array; sortByBits O(1) besides the sort's own
// working space -- it sorts arr in place.
//
// Siblings: the two methods differ in how the two keys reach the sort -- a comparator, or one
// packed number.

public final class SortIntegersByBits {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n) for sortByBitsSimple; sortByBits sorts in place

	public static int[] sortByBitsSimple(int[] arr) {
		return Arrays.stream(arr)//
				.boxed()//
				.sorted((a,b) -> {//
					int countA = Integer.bitCount(a);//
					int countB = Integer.bitCount(b);//
					return countA == countB ? a - b : countA - countB; // ORDER: fewer 1s first, ties by value
				})//
				.mapToInt(i -> i)//
				.toArray();
	}

	public static int[] sortByBits(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int bits = Integer.bitCount(a[i]); // COUNT the 1s
			a[i] += bits * 100000; // PACK: two keys in one int: the count above, the value below (values <= 10^4)
		}
		Arrays.sort(a); // SORT: by count, then by value
		for (int i = 0; i < n; i++) {
			a[i] %= 100000; // UNPACK: drop the count, keep the value
		}
		return a;
	}

	public static void main(String args[]) {
		int[] a1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println(Arrays.toString(sortByBits(a1))); // [0, 1, 2, 4, 8, 3, 5, 6, 7]
		int[] a2 = { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
		System.out.println(Arrays.toString(sortByBits(a2))); // [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
		int[] a3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println(Arrays.toString(sortByBitsSimple(a3))); // [0, 1, 2, 4, 8, 3, 5, 6, 7]
	}
}
