package com.svetanis.algorithms.slidingwindow.array;

// Given an array and a number k, calculate the sum of, 
// the product of all elements of subarray of size ‘k’.

public final class ProductSumSubArrSizeK {

	public static int productSum(int[] a, int k) {
		// Time Complexity: O(n)
		// Aux Space: O(1)

		int n = a.length;
		if (n < k) {
			return -1;
		}

		int sum = 0;
		int prod = 1;
		
		// product of first k elements
		for(int i = 0; i < k; i++) {
			prod *= a[i];
		}

		sum += prod;
		
		for (int i = k; i < n; i++) {
			prod = (prod/a[i - k]) * a[i];
			sum += prod;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 10, 2, 3, 1, 20 };
		System.out.println(productSum(a, 4));
	}
}
