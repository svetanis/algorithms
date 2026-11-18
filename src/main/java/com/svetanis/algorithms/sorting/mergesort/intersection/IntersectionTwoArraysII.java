package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 350. Intersection of Two Arrays II
//
// Input: two int arrays a1 and a2, unsorted, repeats allowed. On LeetCode: 1 <= length <= 1000,
// 0 <= value <= 1000. Returns the values that are in both, each as many times as it appears in
// both arrays (the smaller of the two counts), in any order.
//
// The one idea: what is wanted is, for each value, min(count in a1, count in a2). Three ways to
// get it, in the three methods:
//   intersectSimple -- for each number of a1 find an unused equal number in a2 by scanning, and
//     mark it used by writing -1 (safe: the values are never negative). a2 is changed.
//   intersectSort   -- sort both, then walk them together like a merge: smaller front moves on,
//     equal fronts are one common copy. Both arrays are changed (sorted, a1 overwritten).
//   intersect       -- count the shorter array's values in a map; for each number of the other
//     array that still has a count left, take it and use one count up. Nothing is changed.
//
// Time: intersectSimple O(n * m); intersectSort O(n log n + m log m); intersect O(n + m).
// Space: intersectSimple O(1) beyond the result; intersectSort O(1) beyond the sorts and result;
//   intersect O(min(n, m)) for the map.
//
// Siblings: IntersectionSortedMerging -- the same walk for arrays that arrive sorted;
// IntersectionUnsortedHashing (349) -- the same question, each value once.

public final class IntersectionTwoArraysII {

	public static int[] intersectSimple(int[] a1, int[] a2) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) { // SCAN a2 for an unused copy of a1[i]
				if (a1[i] == a2[j]) {
					list.add(a1[i]); // TAKE
					a2[j] = -1; // MARK used: no value is negative, so -1 never matches
					break;
				}
			}
		}
		int[] a = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = list.get(i);
		}
		return a;
	}

	public static int[] intersectSort(int[] a1, int[] a2) {
		Arrays.sort(a1); // SORT both
		Arrays.sort(a2);
		int i = 0, j = 0, k = 0; // k: next free slot of the result, kept at the front of a1
		while (i < a1.length && j < a2.length) {
			if (a1[i] < a2[j]) { // SKIP a1[i]: not in what is left of a2
				i += 1;
			} else if (a1[i] > a2[j]) { // SKIP a2[j]: not in what is left of a1
				j += 1;
			} else { // TAKE one common copy; k <= i, so it never overwrites an unread number
				a1[k++] = a1[i++];
				j++;
			}
		}
		return Arrays.copyOfRange(a1, 0, k);
	}

	public static int[] intersect(int[] a1, int[] a2) {
		if (a1.length > a2.length) { // the map holds the shorter array
			return intersect(a2, a1);
		}
		Map<Integer, Integer> map = new HashMap<>(); // value -> copies in a1 not yet matched
		for (int num : a1) {
			map.merge(num, +1, Integer::sum);
		}
		List<Integer> list = new ArrayList<>();
		for (int num : a2) {
			if (map.containsKey(num)) { // a copy is still left in a1
				list.add(num); // TAKE
				map.merge(num, -1, Integer::sum); // use one copy up
				if (map.get(num) == 0) {
					map.remove(num);
				}
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 1 };
		int[] a2 = { 2, 2 };
		System.out.println(Arrays.toString(intersectSimple(a1, a2))); // [2, 2]

		int[] a3 = { 4, 9, 5 };
		int[] a4 = { 9, 4, 9, 8, 4 };
		System.out.println(Arrays.toString(intersectSimple(a3, a4))); // [4, 9]

		int[] a5 = { 1, 2, 2, 1 };
		int[] a6 = { 2, 2 };
		System.out.println(Arrays.toString(intersectSort(a5, a6))); // [2, 2]

		int[] a7 = { 4, 9, 5 };
		int[] a8 = { 9, 4, 9, 8, 4 };
		System.out.println(Arrays.toString(intersectSort(a7, a8))); // [4, 9]

		int[] a9 = { 1, 2, 2, 1 };
		int[] a10 = { 2, 2 };
		System.out.println(Arrays.toString(intersect(a9, a10))); // [2, 2]

		int[] a11 = { 4, 9, 5 };
		int[] a12 = { 9, 4, 9, 8, 4 };
		System.out.println(Arrays.toString(intersect(a11, a12))); // [9, 4]
	}
}