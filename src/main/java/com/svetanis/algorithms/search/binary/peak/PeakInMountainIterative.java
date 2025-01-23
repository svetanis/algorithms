package com.svetanis.algorithms.search.binary.peak;

// 162. Find Peak Element

// A peak element is an element that
// is strictly greater than its neighbors

// a mountain array is an array that
// - has at least 3 elements
// - has an element with the largest
// value called "peak", with index k
// the array elements strictly increase
// from the first element to a[k] and
// then strictly decrease from a[k+1] 
// to the last element of the array
// thus creating a mountain of numbers
// the peak element is neither the first
// nor the last index of the array
// find the index of the peak element
// assume there is only one peak element

public final class PeakInMountainIterative {
	// Time Complexity: O(log n)

	public static int peak(int[] a) {
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] > a[mid + 1]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 1 };
		System.out.println(peak(a1)); // 2

		int[] a2 = { 1, 2, 1, 3, 5, 6, 4 };
		System.out.println(peak(a2)); // 5

	}
}