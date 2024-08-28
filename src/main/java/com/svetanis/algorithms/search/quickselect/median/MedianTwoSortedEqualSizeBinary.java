package com.svetanis.algorithms.search.quickselect.median;

public final class MedianTwoSortedEqualSizeBinary {

	public static int median(int[] a1, int[] a2) {
		int n = a1.length;

		if (a1[n - 1] < a2[0]) {
			return (a1[n - 1] + a2[0]) / 2;
		}

		if (a2[n - 1] < a1[0]) {
			return (a2[n - 1] + a1[0]) / 2;
		}

		return binary(a1, a2, 0, n - 1);
	}

	private static int binary(int[] a1, int[] a2, int left, int right) {
		// Time compelixity: O(log n)

		int n = a1.length;

		// we have reached at the end (left or right) of a1
		if (left > right) {
			return binary(a2, a1, 0, n - 1);
		}

		// middle element of a1
		int i = left + (right - left) / 2;
		int j = n - i - 1; // corresponding index of a2

		if (a1[i] > a2[j] && (j == n - 1 || a1[i] <= a2[j + 1])) {
			// a1[i] is decided as median2, now select the median1
			// (element just before a1[i] in merged array)
			// to get the average of both
			if (a2[j] > a1[i - 1] || i == 0) {
				return (a1[i] + a2[j]) / 2;
			} else {
				return (a1[i] + a1[i - 1]) / 2;
			}
		} else if (a1[i] > a2[j] && j != n - 1 && a1[i] > a2[j + 1]) {
			return binary(a1, a2, left, i - 1);
		} else { // a1[i] < than both a2[j] and a2[j + 1]
			return binary(a1, a2, i + 1, right);
		}
	}

	public static void main(String[] args) {
		int a1[] = { 1, 12, 15, 26, 38 };
		int a2[] = { 2, 13, 17, 30, 45 };
		System.out.println(median(a1, a2));
	}
}
