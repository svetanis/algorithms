package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 349. Intersection of Two Arrays

public final class IntersectionUnsortedHashing {
	// Time Complexity: O(n + m)

	public static int[] intersection(int[] a1, int[] a2) {
		boolean[] visited = new boolean[1001];
		for (int num : a1) {
			visited[num] = true;
		}
		List<Integer> list = new ArrayList<>();
		for (int num : a2) {
			if (visited[num]) {
				list.add(num);
				visited[num] = false;
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 1 };
		int[] a2 = { 2, 2 };
		Print.print(intersection(a1, a2)); // 2

		int[] a3 = { 4, 9, 5 };
		int[] a4 = { 9, 4, 9, 8, 4 };
		Print.print(intersection(a3, a4)); // 9 4
	}
}