package com.svetanis.algorithms.search.kthelement;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchInsertPositionIterative.binary2;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// 658. Find K Closest Elements

// given a sorted array and two integers k and target
// find k closest numbers to target in the array
// return the numbers in the sorted order
// target is not necessarily present in the array

public final class KClosestToGivenNumberTwoPointers {
	// Time Complexity: O(log n + k)

	public static List<Integer> kClosestElements(int[] a, int k, int target) {
		int left = 0;
		int right = a.length - k;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (target - a[mid] <= a[mid + k] - target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = left; i < left + k; i++) {
			list.add(a[i]);
		}
		return list;
	}

	// the same answer grown outward from the middle instead of located by its
	// left edge. k is assumed to be at most a.length, which LC 658 guarantees
	public static ImmutableList<Integer> kClosest(int[] a, int k, int target) {
		// first index whose value is >= target -- LC 35's insert position,
		// which is where the window has to start growing from. an exact hit
		// is not needed and is not looked for
		int right = binary2(a, target);
		int left = right - 1;
		List<Integer> list = newArrayList();
		for (int i = 0; i < k; i++) {
			if (takeLeft(a, target, left, right)) {
				list.add(0, a[left--]);
			} else {
				list.add(a[right++]);
			}
		}
		return newList(list);
	}

	// prepending the left candidate and appending the right one keeps the
	// result in array order, so no sort is needed at the end
	private static boolean takeLeft(int[] a, int target, int left, int right) {
		if (left < 0) {
			return false;
		}
		if (right >= a.length) {
			return true;
		}
		// on a tie LC 658 wants the SMALLER value, which in a sorted array
		// is the left candidate -- hence <=, not <
		return target - a[left] <= a[right] - target;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 7, 8, 9 };
		System.out.println(kClosest(a1, 3, 7)); // 6, 7, 8
		int[] a2 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a2, 3, 6)); // 4, 5, 6
		int[] a3 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a3, 3, 10)); // 5, 6, 9

		// two of these four steps are ties, and both have to go left
		int[] a6 = { 1, 2, 3, 4, 5 };
		System.out.println(kClosest(a6, 4, 3)); // 1, 2, 3, 4 -- was 2, 3, 4, 5

		// target absent and interior: the old code walked to the last index
		int[] a7 = { 1, 2, 3, 100, 200 };
		System.out.println(kClosest(a7, 2, 4)); // 2, 3 -- was 100, 200

		int[] a4 = { 1, 2, 3, 4, 5 };
		System.out.println(kClosestElements(a4, 4, 3)); // 1 2 3 4
		int[] a5 = { 1, 1, 2, 3, 4, 5 };
		System.out.println(kClosestElements(a5, 4, -1)); // 1 1 2 3
	}
}
