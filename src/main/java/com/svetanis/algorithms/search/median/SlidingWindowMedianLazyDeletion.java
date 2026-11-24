package com.svetanis.algorithms.search.median;

import java.util.Arrays;

// 480. Sliding Window Median

// Given an array a and a window size k, return the median of every
// window of k consecutive values, left to right: n - k + 1 medians, each
// the middle value for odd k or the average of the two middles for
// even k. LC 480 takes 1 <= k <= n and values over the whole int range.
// Named maxSlidingWindow, but it returns medians.

// Slide a window over MedianTwoHeapsLazyDeletion, constructed with the window size:
// add the first k values and read; then at each step add the value
// entering on the right, remove the one leaving on the left, and read
// again. MedianTwoHeapsLazyDeletion removes lazily, so each step is O(log n), and it
// averages the two middles as doubles, so values near Integer.MAX_VALUE
// do not overflow.

// Time: O(n log n) -- one add, one remove and one read per step, each
//   O(log n) amortised; the heaps can hold values already removed.
// Space: O(n) -- removed values wait in the heaps until they surface.

// Siblings:
//   SlidingWindowMedianHeapRemove -- the same slide over OnlineMedianTwoHeapsRemove, whose remove
//     scans the heap: O(n * k); returns a List<Double>.
//   MedianTwoHeapsLazyDeletion -- the two heaps with lazy deletion this drives.

public final class SlidingWindowMedianLazyDeletion {

	public static double[] maxSlidingWindow(int[] a, int k) {
		MedianTwoHeapsLazyDeletion om = new MedianTwoHeapsLazyDeletion(k);
		for (int i = 0; i < k; i++) { // SEED: the first window
			om.add(a[i]);
		}
		int n = a.length;
		double[] medians = new double[n - k + 1];
		medians[0] = om.median(); // READ
		for (int i = k; i < n; i++) {
			om.add(a[i]); // ENTER: the value on the right
			om.remove(a[i - k]); // LEAVE: the value that drops off the left
			medians[i - k + 1] = om.median(); // READ
		}
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
		// 2.147483647E9 -- no overflow

		System.out.println();
		int[] a4 = { 2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647 };
		double[] m4 = maxSlidingWindow(a4, 2);
		Arrays.stream(m4).forEach(System.out::println);
		// 1.073741824E9, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 1.073741827E9

		System.out.println();
		int[] a5 = { 1, 2 };
		double[] m5 = maxSlidingWindow(a5, 1);
		Arrays.stream(m5).forEach(System.out::println);
		// 1.0, 2.0

		System.out.println();
		int[] a6 = { 7, 9, 3, 8, 0, 2, 4, 8, 3, 9 };
		double[] m6 = maxSlidingWindow(a6, 1);
		Arrays.stream(m6).forEach(System.out::println);
		// 7.0, 9.0, 3.0, 8.0, 0.0, 2.0, 4.0, 8.0, 3.0, 9.0

		System.out.println();
		int[] a7 = { -2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647,
				2147483647, 2147483647, -2147483648, 2147483647, -2147483648 };
		double[] m7 = maxSlidingWindow(a7, 3);
		Arrays.stream(m7).forEach(System.out::println);
		// -2.147483648E9 x 5, then 2.147483647E9 x 5, then -2.147483648E9
	}
}