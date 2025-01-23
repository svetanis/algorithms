package com.svetanis.algorithms.search.binary.peak;

import static java.util.Arrays.asList;

import java.util.List;

// 852. Peak Index in a Mountain Array

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

public final class PeakInMountainArray852 {
	// Time Complexity: O(log n)

	public static int peak(int[] a) {
		int left = 0;
		int right = a.length - 1;
		int peak = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a[mid] > a[mid + 1]) {
				peak = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return peak;
	}

	public static int peak(List<Integer> arr) {
		int left = 0;
		int right = arr.size() - 1;
		int peak = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid) > arr.get(mid + 1)) {
				peak = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return peak;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 0 };
		System.out.println(peak(a1)); // 1
		int[] a2 = { 0, 2, 1, 0 };
		System.out.println(peak(a2)); // 1
		int[] a3 = { 0, 10, 5, 2 };
		System.out.println(peak(a3)); // 1

		System.out.println(peak(asList(0, 1, 2, 3, 2, 1, 0))); // 3 -> 3
	}
}