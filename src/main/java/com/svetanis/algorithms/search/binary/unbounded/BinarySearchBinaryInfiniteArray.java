package com.svetanis.algorithms.search.binary.unbounded;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.lang.Integer.MAX_VALUE;

import com.google.common.base.Optional;
import com.svetanis.algorithms.intervals.Interval;

// given an infinite sorted array (or an array with unknown size)
// find the index of the given number k if it is present in the array

public final class BinarySearchBinaryInfiniteArray {

	public static Optional<Integer> binary(ArrayReader reader) {
		// O(log p), p is position of element to be search
		Interval bounds = bounds(reader);
		return binarySearch(reader, bounds.start, bounds.end);
	}

	private static Interval bounds(ArrayReader reader) {
		// find the proper bounds first
		int start = 0;
		int end = 1;
		while (reader.get(end) == 0) {
			start = end;
			end = 2 * end;
		}
		return new Interval(start, end);
	}

	private static Optional<Integer> binarySearch(ArrayReader reader, int start, int end) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int curr = reader.get(mid);
			if (curr == 1 && (mid == 0 || reader.get(mid - 1) == 0)) {
				return of(mid);
			} else if (curr == 1) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return absent();
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 0, 1, 1, 1, 1 };
		ArrayReader reader = new ArrayReader(a1);
		System.out.println(binary(reader));

		int[] a2 = { 1, 1, 1, 1, 1, 1 };
		ArrayReader reader2 = new ArrayReader(a2);
		System.out.println(binary(reader2));

		int[] a3 = { 0, 0, 0, 0, 0, 0 };
		ArrayReader reader3 = new ArrayReader(a3);
		System.out.println(binary(reader3));		
	
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
