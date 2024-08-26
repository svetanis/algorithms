package com.svetanis.algorithms.search.quickselect.median;

import java.util.PriorityQueue;
import java.util.Queue;

// given a row wise sorted matrix of size r * c
// find the median of the matrix given

// for a number to be median, there should be
// (r * c)/2 numbers smaller than that number

public final class MedianRowSortedPQ {
	// Time Complexity: O(r * c/2 * log r)
	// Space Complexity: O(r)

	public static int median(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		Queue<Element> pq = minHeap(matrix);
		int count = 0;
		int median = -1;
		int medianIndex = (n * m) / 2;
		while (count <= medianIndex) {
			Element top = pq.poll();
			int row = top.row;
			int col = top.col;
			median = top.val;
			count++;
			if (col + 1 < m) {
				col++;
				pq.add(new Element(matrix[row][col], row, col));
			}
		}
		return median;
	}

	private static Queue<Element> minHeap(int[][] matrix) {
		Queue<Element> pq = new PriorityQueue<>((x, y) -> (x.val - y.val));
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
		System.out.println(median(matrix));
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
