package com.svetanis.algorithms.search.kthelement;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// given a sorted array and two integers k and target
// find k closest numbers to target in the array
// return the numbers in the sorted order
// target is not necessarily present in the array

public final class KClosestToGivenNumber {

	public static ImmutableList<Integer> kClosest(int[] a, int k, int target) {
		// Time Complexity: O(log n + k * log k)

		int index = targetIndex(a, target);
		int left = max(index - k, 0);
		int right = max(index + k, a.length - 1);
		Comparator<Pair<Integer, Integer>> c = (x, y) -> x.getLeft() - y.getLeft();
		Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>(c);
		// add all candidates to the queue
		for (int i = left; i < right; i++) {
			int diff = abs(target - a[i]);
			pq.add(Pair.build(diff, i));
		}
		return kClosest(a, k, pq);
	}

	private static ImmutableList<Integer> kClosest(int[] a, int k, Queue<Pair<Integer, Integer>> pq) {
		List<Integer> list = newArrayList();
		for (int i = 0; i < k; i++) {
			list.add(a[pq.poll().getRight()]);
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
		System.out.println(kClosest(a1, 3, 7)); // 6, 8, 7
		int[] a2 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a2, 3, 6)); // 4, 5, 6
		int[] a3 = { 2, 4, 5, 6, 9 };
		System.out.println(kClosest(a3, 3, 10)); // 5, 6, 9
	}
}