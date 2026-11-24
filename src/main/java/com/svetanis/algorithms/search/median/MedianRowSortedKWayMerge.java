package com.svetanis.algorithms.search.median;

import static java.util.Comparator.comparingInt;

import java.util.PriorityQueue;
import java.util.Queue;

// Median of a row-wise sorted matrix.

// given a row wise sorted matrix of size r * c
// find the median of the matrix given
// Takes r, c >= 1, as the code assumes. Returns the value with
// (r * c) / 2 values before it in sorted order: the middle value when
// r * c is odd, the upper of the two middles when it is even.

// for a number to be median, there should be
// (r * c)/2 numbers smaller than that number

// Merge the rows: a min-heap holds the next unread value of each row. Pop
// the smallest, push the next value of the same row, and stop at the pop
// with index (r * c) / 2. The pops come out in sorted order, so that pop
// is the median.

// Time: O((r * c / 2) * log r) -- (r * c) / 2 + 1 pops, each on a heap of at most r.
// Space: O(r) -- the heap, one entry per row.

// Siblings:
//   MedianInMSortedArrays (sorting/kwaymerge) -- the same merge over m
//     sorted lists of any lengths.

public final class MedianRowSortedKWayMerge {

	public static int median(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		Queue<Element> pq = minHeap(matrix); // SEED: each row's first value
		int count = 0;
		int median = -1;
		int medianIndex = (n * m) / 2;
		while (count <= medianIndex) { // POP: up to sorted index (r * c) / 2
			Element top = pq.poll(); // POP: the smallest unread value
			int row = top.row;
			int col = top.col;
			median = top.val;
			count++;
			if (col + 1 < m) { // NEXT: the same row's next value
				col++;
				pq.add(new Element(matrix[row][col], row, col));
			}
		}
		return median; // REPORT: the last pop
	}

	private static Queue<Element> minHeap(int[][] matrix) {
		Queue<Element> pq = new PriorityQueue<>(comparingInt(x -> x.val));
		for (int i = 0; i < matrix.length; i++) {
			pq.add(new Element(matrix[i][0], i, 0));
		}
		return pq;
	}

	public static void main(String[] args) {
		int[][] matrix = { //
				{ 1, 3, 5 }, //
				{ 2, 6, 9 }, //
				{ 3, 6, 9 }//
		};
		System.out.println(median(matrix)); // 5
	}

	private static final class Element {
		private final int val;
		private final int row;
		private final int col;

		public Element(int val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
	}
}
