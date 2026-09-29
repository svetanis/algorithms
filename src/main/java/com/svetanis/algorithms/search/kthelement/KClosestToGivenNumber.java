package com.svetanis.algorithms.search.kthelement;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchInsertPositionIterative.binary2;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Collections.sort;
import static java.util.Comparator.comparingInt;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// 658. Find K Closest Elements

// given a sorted array and two integers k and target
// find k closest numbers to target in the array
// return the numbers in the sorted order
// target is not necessarily present in the array

// the heap is the wrong tool here and the file exists to show why:
// the array is already sorted, so the answer is a contiguous window and
// KClosestToGivenNumberTwoPointers finds its edge in O(log n + k)

public final class KClosestToGivenNumber {
	// Time Complexity: O(log n + k * log k)

	public static ImmutableList<Integer> kClosest(int[] a, int k, int target) {
		// first index whose value is >= target -- LC 35's insert position
		int index = binary2(a, target);
		// every one of the k closest sits within k positions of that index,
		// so this window holds all the candidates and nothing else is read.
		// both bounds are INDEXES: clamp the upper one to n - 1, not to n
		int left = max(index - k, 0);
		int right = min(index + k, a.length - 1);
		Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>(byDistanceThenIndex());
		for (int i = left; i <= right; i++) {
			pq.add(Pair.build(abs(target - a[i]), i));
		}
		return kClosest(a, k, pq);
	}

	// LC 658 breaks a tie toward the SMALLER value, which in a sorted array
	// is the lower index. comparing distance alone leaves ties to whatever
	// order the heap happens to have, which is not an order at all
	private static Comparator<Pair<Integer, Integer>> byDistanceThenIndex() {
		Comparator<Pair<Integer, Integer>> byDistance = comparingInt(Pair::getLeft);
		return byDistance.thenComparingInt(Pair::getRight);
	}

	private static ImmutableList<Integer> kClosest(int[] a, int k, Queue<Pair<Integer, Integer>> pq) {
		List<Integer> indexes = newArrayList();
		for (int i = 0; i < k; i++) {
			indexes.add(pq.poll().getRight());
		}
		// the heap gives them back nearest-first; the problem asks for
		// array order, so the distances have to be forgotten again
		sort(indexes);
		List<Integer> list = newArrayList();
		for (int index : indexes) {
			list.add(a[index]);
		}
		return newList(list);
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
