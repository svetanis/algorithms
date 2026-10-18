package com.svetanis.algorithms.slidingwindow.fixed;

// Sum of the products of all subarrays of size k -- no LeetCode number; source unknown
//
// Given an array and a number k, calculate the sum of, 
// the product of all elements of subarray of size ‘k’.
// Returns that sum, or -1 when the array is shorter than k.
//
// The window's product is edited, not rebuilt: divide out the number leaving, multiply in
// the number entering. The division is exact, because the number leaving is one of the
// factors of the product. Assumed: no element is 0 (the example is all positive). A zero that
// has to leave the window is divided by and throws ArithmeticException. Products and the
// total past 2^31 - 1 wrap.
//
// Time: O(n) -- k multiplications build the first window, then one division and one
//   multiplication per step
// Space: O(1) -- the product and the total
//
// Related: MaxProductSubarraySizeK slides the same product and keeps the largest instead of
// adding them up.

public final class ProductSumSubArrSizeK {

	public static int productSum(int[] a, int k) {
		int n = a.length;
		if (n < k) {
			return -1; // RETURN: no window of size k fits
		}

		int sum = 0;
		int prod = 1;
		
		// BUILD: product of first k elements
		for(int i = 0; i < k; i++) {
			prod *= a[i];
		}

		sum += prod; // RECORD: the first window's product joins the total
		
		for (int i = k; i < n; i++) {
			prod = (prod/a[i - k]) * a[i]; // OUT a[i - k] by division, IN a[i]
			sum += prod; // RECORD: this window's product joins the total
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 10, 2, 3, 1, 20 };
		System.out.println(productSum(a, 4));
	}
}
