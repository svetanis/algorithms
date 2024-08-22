package com.svetanis.algorithms.search.quickselect;

import static com.svetanis.java.base.collect.Lists.newList;

import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

public final class TopKElementsPQ {

	public static ImmutableList<Integer> topK(int a[], int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = priorityQueue(a, k);
		
		// go through the remaining numbers of the array,
		// if the number from the array is bigger than
		// the top (smallest) number of the min-heap
		// remove the top number from heap and add
		// the number from the array
		for (int i = k; i < a.length; i++) {
			if (a[i] > pq.peek()) {
				pq.poll();
				pq.add(a[i]);
			}
		}
		return newList(pq);
	}

	private static Queue<Integer> priorityQueue(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> (x - y));
		// put first k numbers in the min heap
		for (int i = 0; i < k; i++) {
			pq.add(a[i]);
		}
		return pq;
	}

	public static Queue<Integer> topKSingleLoop(int a[], int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = new PriorityQueue<Integer>((x, y) -> (x - y));
		for (int item : a) {
			if (pq.size() < k || item > pq.peek()) {
				if (pq.size() == k) {
					pq.poll();
				}
				pq.offer(item);
			}
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
		System.out.println(topK(a1, 4));

		int[] a2 = { 3, 1, 5, 12, 2, 11 };
		System.out.println(topK(a2, 3));

		int[] a3 = { 5, 12, 11, -1, 12 };
		System.out.println(topK(a3, 3));
	}
}