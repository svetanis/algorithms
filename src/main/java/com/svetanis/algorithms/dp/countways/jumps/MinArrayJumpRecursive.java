package com.svetanis.algorithms.dp.countways.jumps;

import static java.lang.Math.min;

// 45. Jump Game II

// Given an array of positive numbers,
// where each element represents the max number of jumps
// that can be made forward from that element,
// write a program to find the minimum number of jumps
// needed to reach the end of the array
// (starting from the first element).
// If an element is 0,
// then we cannot move through that element.

public final class MinArrayJumpRecursive {

	private static final int INF = Integer.MAX_VALUE;

	public static int count(int[] a) {
		int min = count(a, 0);
		return min == INF ? -1 : min;
	}

	private static int count(int[] a, int index) {
		int n = a.length;
		if (index == n - 1) {
			return 0;
		}
		if (a[index] == 0) {
			return INF;
		}
		int min = INF;
		int start = index + 1;
		int end = index + a[index];
		while (start < n && start <= end) {
			int jumps = count(a, start);
			if (jumps != INF) {
				min = min(min, jumps + 1);
			}
			start++;
		}
		return min;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 1, 1, 4 };
		System.out.println(count(a1)); // 3

		int[] a2 = { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
		System.out.println(count(a2)); // 4

		int[] a3 = { 2, 3, 1, 1, 4 };
		System.out.println(count(a3)); // 2

		int[] a4 = { 2, 3, 0, 1, 4 };
		System.out.println(count(a4)); // 2
	}
}
