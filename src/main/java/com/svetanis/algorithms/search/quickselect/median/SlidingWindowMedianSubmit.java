package com.svetanis.algorithms.search.quickselect.median;

import java.util.Arrays;

// 480. Sliding Window Median

public final class SlidingWindowMedianSubmit {

	public static double[] maxSlidingWindow(int[] a, int k) {
		MedianFinder om = new MedianFinder(k);
		for (int i = 0; i < k; i++) {
			om.add(a[i]);
		}
		int n = a.length;
		double[] medians = new double[n - k + 1];
		medians[0] = om.median();
		for (int i = k; i < n; i++) {
			om.add(a[i]);
			om.remove(a[i - k]);
			medians[i - k + 1] = om.median();
		}
		// max element of last window
		return medians;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		double[] m1 = maxSlidingWindow(a1, 3);
		Arrays.stream(m1).forEach(System.out::println);
		// 1.0, -1.0, -1.0, 3.0, 5.0, 6.0
		System.out.println();
		int[] a2 = { 1, 2, 3, 4, 2, 3, 1, 4, 2 };
		double[] m2 = maxSlidingWindow(a2, 3);
		Arrays.stream(m2).forEach(System.out::println);
		// 2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0
		System.out.println();
		int[] a3 = { 2147483647, 2147483647 };
		double[] m3 = maxSlidingWindow(a3, 2);
		Arrays.stream(m3).forEach(System.out::println);

		System.out.println();
		int[] a4 = { 2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647 };
		double[] m4 = maxSlidingWindow(a4, 2);
		Arrays.stream(m4).forEach(System.out::println);

		System.out.println();
		int[] a5 = { 1, 2 };
		double[] m5 = maxSlidingWindow(a5, 1);
		Arrays.stream(m5).forEach(System.out::println);

		System.out.println();
		int[] a6 = { 7, 9, 3, 8, 0, 2, 4, 8, 3, 9 };
		double[] m6 = maxSlidingWindow(a6, 1);
		Arrays.stream(m6).forEach(System.out::println);

		System.out.println();
		int[] a7 = { -2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647,
				2147483647, 2147483647, -2147483648, 2147483647, -2147483648 };
		double[] m7 = maxSlidingWindow(a7, 3);
		Arrays.stream(m7).forEach(System.out::println);
	}
}