package com.svetanis.algorithms.search.median;

import static com.svetanis.algorithms.search.median.MedianUnsortedMinSelect.minSelect;
import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// The k values closest to the median of an unsorted array.

// Design an O(n) time algorithm to compute
// the k elements closest to the median of an array A.
// Takes 1 <= k <= n and values whose differences fit in an int, as the
// code assumes. The median is MedianUnsortedMinSelect's: the lower middle
// for even n. Values equally far from the median are kept in no
// particular order of preference; the k values come back in heap order.

// Find the median, then keep k values in a heap whose top is the kept
// value FARTHEST from the median. Each later value that is closer than
// that top replaces it, so the top is always the next one to give up.

// Time: O(n^2) -- minSelect's partial selection sort; the heap pass is
//   O(n log k). The O(n) the statement asks for is not met: it needs a
//   linear median (quickselect, or median of medians) in place of
//   minSelect, and a selection on distances in place of the heap.
// Space: O(k) -- the heap.

// Siblings:
//   MedianUnsortedMinSelect -- the median this file calls; it rearranges a.
//   KClosestToGivenNumber, KClosestToGivenNumberTwoPointers
//     (search/kthelement, LC 658) -- the k closest to a given value in a
//     SORTED array, by binary search and a window.

public final class ClosestToMedianHeap {

	public static List<Integer> closestToMedian(int[] a, int k) {
		int median = minSelect(a); // MEDIAN: rearranges a, keeps its values
		return closest(a, k, median);
	}

	private static List<Integer> closest(int[] a, int k, int median) {
		Queue<Integer> pq = priorityQueue(a, k, median); // SEED: the first k values
		for (int i = k; i < a.length; ++i) {
			int dist1 = abs(median - pq.peek()); // FARTHEST: the kept value farthest from the median
			int dist2 = abs(median - a[i]);
			if (dist1 > dist2) { // TEST: a[i] is closer than the farthest kept
				pq.poll(); // EVICT
				pq.add(a[i]); // KEEP
			}
		}
		return new ArrayList<>(pq);
	}

	private static Queue<Integer> priorityQueue(int[] a, int k, int median) {
		// ORDER: farthest from the median on top, so peek() is the one to give up
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(abs(median - y), abs(median - x)));
		for (int i = 0; i < k; ++i) {
			pq.offer(a[i]);
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
		System.out.println(closestToMedian(a, 5));
		// [14, 7, 11, 10, 12] -- median == 10; 10, 11, 12, 7, 14 in heap order
	}
}
