package com.svetanis.algorithms.search.binary.rotated;

import static java.util.Arrays.asList;

import java.util.List;

// a sorted array of unique integers
// was rotated at an unknown pivot.
// find the index of the minimum 
// element in this array

public final class RotatedMin {
	// Time Complexity: O(log n)

	public static int min(List<Integer> arr) {
		int left = 0;
		int last = arr.size() - 1;
		int right = last;
		int pivot = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid) <= arr.get(last)) {
				pivot = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return pivot;
	}

	public static void main(String[] args) {
		System.out.println(min(asList(30, 40, 50, 10, 20))); // 3 -> 10
		System.out.println(min(asList(3, 5, 7, 11, 13, 17, 19, 2))); // 7 -> 2
		System.out.println(min(asList(0, 1, 2, 3, 4, 5))); // 0 -> 0
	}
}