package com.svetanis.algorithms.search.binary.frequency;

import static java.util.Arrays.asList;

import java.util.List;

// given a sorted array of integers and
// a target integer, find the first 
// occurrence of the target and return 
// its index. return -1 if the target is
// not in the array.

public final class FirstOccurrence {
	// Time Complexity: O(log n)

	public static int firstOccurrence(List<Integer> list, int target) {
		int left = 0;
		int right = list.size() - 1;
		int index = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (list.get(mid) == target) {
				index = mid;
				right = mid - 1;
			} else if (list.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		System.out.println(firstOccurrence(asList(1, 3, 3, 3, 3, 6, 10, 10, 10, 100), 3)); // 1
		System.out.println(firstOccurrence(asList(2, 3, 5, 7, 11, 13, 17, 19), 6)); // -1
		System.out.println(firstOccurrence(asList(2, 3, 5, 7, 11), 2)); // 0
	}
}