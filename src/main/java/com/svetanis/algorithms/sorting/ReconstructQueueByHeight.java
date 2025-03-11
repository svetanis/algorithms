package com.svetanis.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 406. Queue Reconstruction by Height

public final class ReconstructQueueByHeight {

	public static int[][] reconstruct(int[][] people) {
		Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
		List<int[]> list = new ArrayList<>();
		for (int[] p : people) {
			list.add(p[1], p);
		}
		return list.toArray(new int[list.size()][2]);
	}

	public static void main(String[] args) {
		int[][] people = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
		int[][] people2 = { { 6, 0 }, { 5, 0 }, { 4, 0 }, { 3, 2 }, { 2, 2 }, { 1, 4 } };
		Print.print(reconstruct(people));
		System.out.println();
		Print.print(reconstruct(people2));

	}

}
