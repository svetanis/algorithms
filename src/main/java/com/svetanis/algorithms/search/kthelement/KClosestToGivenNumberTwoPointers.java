package com.svetanis.algorithms.search.kthelement;

import static com.svetanis.algorithms.search.binary.BinarySearchInsertPositionIterative.binary2;

import java.util.ArrayList;
import java.util.List;

// 658. Find K Closest Elements

// given a sorted array and two integers k and target
// find k closest numbers to target in the array
// return the numbers in the sorted order
// target is not necessarily present in the array
// closer wins; at equal distance the SMALLER number wins

// The array is sorted, so the k closest are k numbers in a row -- a window.
// kClosestElements finds the window's first index by binary search: a start
// is too far left exactly when the number just past its window, a[mid + k],
// is closer than its own first number, a[mid]. kClosest finds the same
// window by growing it outward from target's insert position, one number
// at a time, taking whichever end is closer.

// Time: kClosestElements O(log(n - k) + k) -- a search over the n - k + 1
//   possible starts, then the copy; kClosest O(log n + k) -- one binary
//   search, k steps that each move one end, then the copy
// Space: O(1) besides the k numbers returned

// Siblings, same problem:
//   KClosestToGivenNumberTwoPointers -- this file: the window, located directly
//   KClosestToGivenNumber            -- a heap over the window around the insert position,
//                                       O(log n + k log k); it exists to show why a heap is the wrong tool

public final class KClosestToGivenNumberTwoPointers {

	public static List<Integer> kClosestElements(int[] a, int k, int target) {
		int left = 0; // RANGE: the window's first index is one of 0 .. n - k
		int right = a.length - k;
		while (left < right) { // SEARCH until one start is left
			int mid = left + (right - left) / 2; // MID: a candidate start
			// the windows starting at mid and at mid + 1 differ only in a[mid]
			// and a[mid + k]. no abs(): when both sit above target the left
			// side is negative and the start moves left; when both sit below,
			// the right side is negative and it moves right
			if (target - a[mid] <= a[mid + k] - target) { // TEST: <= keeps a[mid] on a tie, the smaller number
				right = mid; // KEEP: the start is mid or to its left
			} else {
				left = mid + 1; // DROP: a[mid + k] is closer, the start is past mid
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = left; i < left + k; i++) {
			list.add(a[i]); // COPY the window, already in array order
		}
		return list; // REPORT
	}

	// the same answer grown outward from the middle instead of located by its
	// left edge. k is assumed to be at most a.length, which LC 658 guarantees
	public static List<Integer> kClosest(int[] a, int k, int target) {
		// first index whose value is >= target -- LC 35's insert position,
		// which is where the window has to start growing from. an exact hit
		// is not needed and is not looked for
		int right = binary2(a, target); // START: the first index past the window
		int left = right - 1; // START: the last index before it -- the window a[left + 1 .. right - 1] is empty
		for (int i = 0; i < k; i++) {
			if (takeLeft(a, target, left, right)) {
				left--; // GROW left: a[left] joins the window
			} else {
				right++; // GROW right: a[right] joins the window
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = left + 1; i < right; i++) {
			list.add(a[i]); // COPY the window, already in array order
		}
		return list; // REPORT
	}

	// the window only ever grows at its two ends, so copying it once at the
	// end gives array order with no sort and no insert at the front
	private static boolean takeLeft(int[] a, int target, int left, int right) {
		if (left < 0) {
			return false; // EDGE: nothing left of the window
		}
		if (right >= a.length) {
			return true; // EDGE: nothing right of the window
		}
		// on a tie LC 658 wants the SMALLER value, which in a sorted array
		// is the left candidate -- hence <=, not <
		return target - a[left] <= a[right] - target; // TEST: the left candidate is no farther
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
