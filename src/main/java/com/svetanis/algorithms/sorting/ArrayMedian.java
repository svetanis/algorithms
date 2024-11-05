package com.svetanis.algorithms.sorting;

import static com.svetanis.java.base.utils.Nums.isOdd;
import static java.util.Arrays.sort;

public final class ArrayMedian {

	public static double median(int[] a) {
		int n = a.length;
		if (isOdd(n)) {
			return new Integer(a[n / 2]).doubleValue();
		}
		return (a[(n - 1) / 2] + a[n / 2]) / 2.0;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 4, 2, 7, 5, 8, 6 };
		sort(a);
		System.out.println(median(a));
	}
}
