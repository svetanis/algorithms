package com.svetanis.algorithms.search.median;

import static com.svetanis.algorithms.search.median.MedianUnsortedMinSelect.minSelect;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;

import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// Design an O(n) time algorithm to compute 
// the k elements closest to the median of an array A.

public final class ClosestToMedian {

	public static ImmutableList<Integer> closestToMedian(int[] a, int k) {
		// Time Complexity: O(n * log k)
		
		int median = minSelect(a);
		return closest(a, k, median);
	}

	private static ImmutableList<Integer> closest(int[] a, int k, int median) {
		Queue<Integer> pq = priorityQueue(a, k);
		for (int i = k; i < a.length; ++i) {
			int dist1 = abs(median - pq.peek());
			int dist2 = abs(median - a[i]);
			if (dist1 > dist2) {
				pq.poll();
				pq.add(a[i]);
			}
		}
		return newList(pq);
	}

	private static Queue<Integer> priorityQueue(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> x - y);
		for (int i = 0; i < k; ++i) {
			pq.offer(a[i]);
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
		System.out.println(closestToMedian(a, 5));
		// median == 10
		// 7, 10, 14, 11, 12
	}
}
