package com.svetanis.algorithms.sorting;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.util.Arrays.asList;

import java.util.List;

// 2340. Minimum Adjacent Swaps to Make a Valid Array

public final class MinAdjacentSwaps {
	// Time complexity: O(n)

	public static int minAdjacentSwaps(List<Integer> list) {
		int n = list.size();
		int min = min(list);
		int max = max(list);
		if(min == max) {
			return 0;
		}
		int swap = min > max ? 1 : 0;
		return min + n - 1 - max - swap;
	}

	private static int min(List<Integer> list) {
		int min = MAX_VALUE;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			int curr = list.get(i);
			if (curr < min) {
				min = curr;
				index = i;
			}
		}
		return index;
	}

	private static int max(List<Integer> list) {
		int max = MIN_VALUE;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			int curr = list.get(i);
			if (curr > max) {
				max = curr;
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(3, 1, 2, 4);
		System.out.println(minAdjacentSwaps(list)); // 1
	}
}
