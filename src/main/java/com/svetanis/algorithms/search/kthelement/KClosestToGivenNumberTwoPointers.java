package com.svetanis.algorithms.search.kthelement;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;

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

	public static ImmutableList<Integer> kClosest(int[] a, int k, int target) {
		int index = targetIndex(a, target);
		int left = index;
		int right = index + 1;
		List<Integer> list = newArrayList();
		for (int i = 0; i < k; i++) {
			if (left >= 0 && right < a.length) {
				int diff1 = abs(target - a[left]);
				int diff2 = abs(target - a[right]);
				if (diff1 < diff2) {
					list.add(0, a[left--]);
				} else {
					list.add(a[right++]);
				}
			} else if (left >= 0) {
				list.add(0, a[left--]);
			} else if (right < a.length) {
				list.add(a[right++]);
			}
		}
		return newList(list);
	}

	private static int targetIndex(int[] a, int target) {
		int index = binarySearch(a, target);
		if (index != -1) {
			return index;
		}
		return target < a[0] ? 0 : a.length - 1;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 7, 8, 9 };
		System.out.println(kClosest(a1, 3, 7)); // 6, 7, 8
		int[] a2 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a2, 3, 6)); // 4, 5, 6
		int[] a3 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a3, 3, 10)); // 5, 6, 9

		int[] a4 = { 1, 2, 3, 4, 5 };
		System.out.println(kClosestElements(a4, 4, 3)); // 1 2 3 4
		int[] a5 = { 1, 1, 2, 3, 4, 5 };
		System.out.println(kClosestElements(a5, 4, -1)); // 1 1 2 3
	}
}