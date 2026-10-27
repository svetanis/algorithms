package com.svetanis.algorithms.prefixsum;

// Prefix product array -- not a LeetCode problem; a teaching helper for this package
//
// Input: a non-empty array a. Returns prefix, where prefix[i] is a[0] * a[1] * ... * a[i] -- the
// product up to and including index i, so prefix has the same length as a. An empty array throws
// ArrayIndexOutOfBoundsException at prefix[0] = a[0]. The products are ints and wrap past 2^31
// with no error: forty 3s give 689956897 at index 39.
//
// The one idea: each product is the one before it times one more element, prefix[i] =
// prefix[i - 1] * a[i], so a single left-to-right pass builds them all. Unlike sums, products
// cannot be undone by dividing when an element is 0, which is why the product-of-the-rest problem
// (238) multiplies a prefix product by a suffix product instead of dividing.
//
// Time: O(n) -- one pass.
// Space: O(n) -- the returned array.
//
// Siblings: SuffixProductArray builds the same products from the right. PrefixSumArray is this
// build with + in place of *.

public final class PrefixProductArray {

	public static int[] prefixProduct(int[] a) {
		int n = a.length;
		int[] prefix = new int[n];
		prefix[0] = a[0]; // FIRST product: a[0] alone
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] * a[i]; // EXTEND: the product before, times a[i]
		}
		return prefix;
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
		print(prefixProduct(a1)); // 10 200 2000 10000 150000

		int[] a2 = { 10, 4, 16, 20 };
		print(prefixProduct(a2)); // 10 40 640 12800
	}
}
