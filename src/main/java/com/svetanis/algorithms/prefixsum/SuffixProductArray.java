package com.svetanis.algorithms.prefixsum;

// Suffix product array -- not a LeetCode problem; a teaching helper for this package
//
// Input: a non-empty array a. Returns suffix, where suffix[i] is a[i] * a[i + 1] * ... * a[n - 1]
// -- the product from index i to the end, so suffix has the same length as a. An empty array
// throws ArrayIndexOutOfBoundsException at suffix[n - 1] = a[n - 1]. The products are ints and
// wrap past 2^31 with no error.
//
// The one idea: the mirror of a prefix product. Each product is the one after it times one more
// element, suffix[i] = suffix[i + 1] * a[i], so a single right-to-left pass builds them all,
// starting from the last element alone.
//
// Time: O(n) -- one pass.
// Space: O(n) -- the returned array.
//
// Siblings: PrefixProductArray builds the same products from the left. SuffixSumArray is this
// build with + in place of *.

public final class SuffixProductArray {

	public static int[] suffixProduct(int[] a) {
		int n = a.length;
		int[] suffix = new int[n];
		suffix[n - 1] = a[n - 1]; // LAST product: a[n - 1] alone
		for (int i = n - 2; i >= 0; i--) { // WALK right to left
			suffix[i] = suffix[i + 1] * a[i]; // EXTEND: the product after, times a[i]
		}
		return suffix;
	}

	// PRINT: each value followed by a space, then a line break
	private static void print(int[] a) {
		for (int value : a) {
			System.out.printf("%d ", value);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 20, 10, 5, 15 };
		print(suffixProduct(a1)); // 150000 15000 750 75 15

		int[] a2 = { 10, 4, 16, 20 };
		print(suffixProduct(a2)); // 12800 1280 320 20
	}
}
