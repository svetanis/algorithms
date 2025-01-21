package com.svetanis.algorithms.search.binary.unbounded;

import static java.lang.Integer.MAX_VALUE;

// 702. Search in a Sorted Array of Unknown Size

// given an infinite sorted array (or an array with unknown size)
// find the index of the given number k if it is present in the array

public final class BinarySearchSortedArrayUnknownSize702 {
	// O(log p), p is position of element to be search

	public static int binary(ArrayReader reader, int target) {
		int left = 0;
		int right = 20000;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int val = reader.get(mid);
			if (val >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return reader.get(left) == target ? left : -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 };
		ArrayReader reader = new ArrayReader(a1);
		System.out.println(binary(reader, 16));
		System.out.println(binary(reader, 11));

		int[] a2 = { 1, 3, 8, 10, 15 };
		ArrayReader reader2 = new ArrayReader(a2);
		System.out.println(binary(reader2, 15));
		System.out.println(binary(reader2, 200));
	}

	private static class ArrayReader {
		private int[] a;

		public ArrayReader(int[] a) {
			this.a = a;
		}

		public int get(int index) {
			if (index >= a.length) {
				return MAX_VALUE;
			}
			return a[index];
		}
	}
}
