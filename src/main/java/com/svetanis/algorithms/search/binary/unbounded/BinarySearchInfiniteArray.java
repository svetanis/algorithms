package com.svetanis.algorithms.search.binary.unbounded;

import static java.lang.Integer.MAX_VALUE;

import com.svetanis.algorithms.sorting.mergesort.interval.Interval;

// given an infinite sorted array (or an array with unknown size)
// find the index of the given number k if it is present in the array

public final class BinarySearchInfiniteArray {

	public static int binary(ArrayReader reader, int x) {
		// O(log p), p is position of element to be search

		Interval bounds = bounds(reader, x);
		return binarySearch(reader, bounds.start, bounds.end, x);
	}

	private static Interval bounds(ArrayReader reader, int x) {
		// find the proper bounds first
		int start = 0;
		int end = 1;
		while (reader.get(end) < x) {
			int temp = end + 1;
			// double the bounds size
			end += (end - start + 1) * 2;
			start = temp;
		}
		return new Interval(start, end);
	}

	private static int binarySearch(ArrayReader reader, int start, int end, int x) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int val = reader.get(mid);
			if (x < val) {
				end = mid - 1;
			} else if (x > val) {
				start = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
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
