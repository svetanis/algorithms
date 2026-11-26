package com.svetanis.algorithms.search.kthelement;

import static com.svetanis.algorithms.search.binary.BinarySearchInsertPositionIterative.binary2;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Collections.sort;
import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 658. Find K Closest Elements

// given a sorted array and two integers k and target
// find k closest numbers to target in the array
// return the numbers in the sorted order
// target is not necessarily present in the array
// closer wins; at equal distance the SMALLER number wins

// Every one of the k closest sits within k positions of the place target
// would be inserted, so only that window of at most 2k + 1 numbers is read.
// A min-heap ordered by distance, then by index, hands them back nearest
// first with ties going to the lower index -- in a sorted array, the smaller
// number. The first k indexes are then sorted back into array order.

// the heap is the wrong tool here and the file exists to show why:
// the array is already sorted, so the answer is a contiguous window and
// KClosestToGivenNumberTwoPointers finds its edge in O(log n + k)

// Time: O(log n + k log k) -- one binary search, then at most 2k + 1 adds
//   and k polls on the heap, and a sort of the k indexes
// Space: O(k) -- the heap holds the window, at most 2k + 1 candidates

// Siblings, same problem:
//   KClosestToGivenNumber            -- this file: a heap over the window around the insert position
//   KClosestToGivenNumberTwoPointers -- the window itself: its left edge by binary search
//                                       (kClosestElements), or grown outward from the insert position (kClosest)

public final class KClosestToGivenNumber {

	public static List<Integer> kClosest(int[] a, int k, int target) {
		// first index whose value is >= target -- LC 35's insert position
		int index = binary2(a, target); // FIND where target would go
		// every one of the k closest sits within k positions of that index,
		// so this window holds all the candidates and nothing else is read.
		// both bounds are INDEXES: clamp the upper one to n - 1, not to n
		int left = max(index - k, 0); // WINDOW: first candidate
		int right = min(index + k, a.length - 1); // WINDOW: last candidate, inclusive
		Queue<Candidate> pq = new PriorityQueue<>(byDistanceThenIndex()); // HEAP: min-heap, the nearest on top
		for (int i = left; i <= right; i++) {
			pq.add(new Candidate(abs(target - a[i]), i)); // ADD every candidate in the window
		}
		return kClosest(a, k, pq);
	}

	// LC 658 breaks a tie toward the SMALLER value, which in a sorted array
	// is the lower index. comparing distance alone leaves ties to whatever
	// order the heap happens to have, which is not an order at all
	private static Comparator<Candidate> byDistanceThenIndex() {
		Comparator<Candidate> byDistance = comparingInt(Candidate::distance); // ORDER: nearer first
		return byDistance.thenComparingInt(Candidate::index); // TIE: the lower index, so the smaller number
	}

	private static List<Integer> kClosest(int[] a, int k, Queue<Candidate> pq) {
		List<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			indexes.add(pq.poll().index()); // TAKE the k nearest, nearest first
		}
		// the heap gives them back nearest-first; the problem asks for
		// array order, so the distances have to be forgotten again
		sort(indexes); // SORT the indexes back into array order
		List<Integer> list = new ArrayList<>();
		for (int index : indexes) {
			list.add(a[index]); // MAP each index to its number
		}
		return list; // REPORT
	}

	// one number in the window: how far it is from target, and where it sits
	private record Candidate(int distance, int index) {
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 7, 8, 9 };
		System.out.println(kClosest(a1, 3, 7)); // 6, 7, 8 -- was 6, 8, 7, in distance order
		int[] a2 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a2, 3, 6)); // 4, 5, 6
		int[] a3 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a3, 3, 10)); // 5, 6, 9

		// two of these four steps are ties, and both have to go left
		int[] a4 = { 1, 2, 3, 4, 5 };
		System.out.println(kClosest(a4, 4, 3)); // 1, 2, 3, 4

		// target absent and interior: the old bound walked to the last index
		int[] a5 = { 1, 2, 3, 100, 200 };
		System.out.println(kClosest(a5, 2, 4)); // 2, 3

		// target above everything: the old max(index + k, n - 1) read a[n]
		int[] a6 = { 0 };
		System.out.println(kClosest(a6, 1, 1)); // 0
	}
}
