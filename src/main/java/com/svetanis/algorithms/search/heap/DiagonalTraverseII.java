package com.svetanis.algorithms.search.heap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.java.base.utils.Print;

// 1424. Diagonal Traverse II

public final class DiagonalTraverseII {

	public static int[] diagonalTraverse(List<List<Integer>> lists) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { 0, 0 });
		List<Integer> list = new ArrayList<>();
		while (!dq.isEmpty()) {
			int[] p = dq.poll();
			int row = p[0];
			int col = p[1];
			list.add(lists.get(row).get(col));

			if (col == 0 && row + 1 < lists.size()) {
				dq.offer(new int[] { row + 1, col });
			}
			if (col + 1 < lists.get(row).size()) {
				dq.offer(new int[] { row, col + 1 });
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static int[] diagonalOrder(List<List<Integer>> lists) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < lists.get(i).size(); j++) {
				pq.offer(new int[] { i + j, j, lists.get(i).get(j) });
			}
		}
		int[] a = new int[pq.size()];
		int index = 0;
		while (!pq.isEmpty()) {
			a[index++] = pq.poll()[2];
		}
		return a;
	}

	public static void main(String[] args) {
		List<List<Integer>> list1 = new ArrayList<>();
		list1.add(Arrays.asList(1, 2, 3));
		list1.add(Arrays.asList(4, 5, 6));
		list1.add(Arrays.asList(7, 8, 9));
		Print.print(diagonalTraverse(list1)); // 1,4,2,7,5,3,8,6,9

		List<List<Integer>> list2 = new ArrayList<>();
		list2.add(Arrays.asList(1, 2, 3, 4, 5));
		list2.add(Arrays.asList(6, 7));
		list2.add(Arrays.asList(8));
		list2.add(Arrays.asList(9, 10, 11));
		list2.add(Arrays.asList(12, 13, 14, 15, 16));
		Print.print(diagonalTraverse(list2)); // 1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16
	}
}
