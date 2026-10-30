package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

// 1424. Diagonal Traverse II
//
// nums is a list of rows, and the rows may have different lengths. Returns every element in
// diagonal order: diagonal d holds the cells with row + col == d, the diagonals go in increasing d,
// and inside one diagonal the cells go from the bottom row up (largest row first).
//
// Two methods, the same order reached two ways:
//   diagonalTraverse -- a breadth-first walk from (0, 0). Each cell sends its right neighbour, and a
//     cell in column 0 also sends the cell below it, so every cell is reached exactly once and from
//     exactly one parent. The queue then holds diagonal d before diagonal d + 1, and inside a
//     diagonal the cell below (sent from column 0 first) comes before the cells to its upper right.
//   diagonalOrder -- tag every cell with (row + col, col) and let a min-heap sort the tags: the
//     smaller diagonal first, and inside it the smaller column, which is the larger row.
//
// Time: diagonalTraverse O(n), n the count of cells -- each is queued once;
//       diagonalOrder O(n log n) -- every cell goes through the heap
// Space: O(n) for the queue / the heap, and the answer

public final class DiagonalTraverseII {

	public static int[] diagonalTraverse(List<List<Integer>> lists) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { 0, 0 });                  // START: the top-left cell
		List<Integer> list = new ArrayList<>();
		while (!dq.isEmpty()) {
			int[] p = dq.poll();                       // POLL: the next cell in diagonal order
			int row = p[0];
			int col = p[1];
			list.add(lists.get(row).get(col));         // REPORT: its value

			if (col == 0 && row + 1 < lists.size()) {
				dq.offer(new int[] { row + 1, col });  // DOWN: only column 0 sends the cell below, so no cell is queued twice
			}
			if (col + 1 < lists.get(row).size()) {
				dq.offer(new int[] { row, col + 1 });  // RIGHT: queued after DOWN, so the lower cell of the next diagonal goes first
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static int[] diagonalOrder(List<List<Integer>> lists) {
		// ORDER: smaller diagonal first; inside a diagonal the smaller column, which is the lower row
		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < lists.get(i).size(); j++) {
				pq.offer(new int[] { i + j, j, lists.get(i).get(j) }); // PUSH: (diagonal, column, value)
			}
		}
		int[] a = new int[pq.size()];
		int index = 0;
		while (!pq.isEmpty()) {
			a[index++] = pq.poll()[2];                 // POLL: the values come out in diagonal order
		}
		return a;
	}

	public static void main(String[] args) {
		List<List<Integer>> list1 = new ArrayList<>();
		list1.add(Arrays.asList(1, 2, 3));
		list1.add(Arrays.asList(4, 5, 6));
		list1.add(Arrays.asList(7, 8, 9));
		System.out.println(Arrays.toString(diagonalTraverse(list1))); // [1, 4, 2, 7, 5, 3, 8, 6, 9]

		List<List<Integer>> list2 = new ArrayList<>();
		list2.add(Arrays.asList(1, 2, 3, 4, 5));
		list2.add(Arrays.asList(6, 7));
		list2.add(Arrays.asList(8));
		list2.add(Arrays.asList(9, 10, 11));
		list2.add(Arrays.asList(12, 13, 14, 15, 16));
		System.out.println(Arrays.toString(diagonalTraverse(list2))); // [1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16]
	}
}
