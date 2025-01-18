package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.Deque;

// 739. Daily Temperatures

// given an array of integers (temperatures)
// represents the daily temperatures, return
// an array such that a[i] is the number of
// days you have to wait after the i-th day
// to get a warmer temperature. if there is
// no future day for which this is possible
// keep 0 instead

public final class DailyTemperatures {
	// Time complexity: O(n)

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		Deque<Integer> dq = new ArrayDeque<>();
		for (int index = 0; index < n; index++) {
			while (!dq.isEmpty() && a[index] > a[dq.peek()]) {
				int prev = dq.pop();
				greater[prev] = index - prev;
			}
			dq.push(index);
		}
		return greater;
	}

	public static void main(String[] args) {
		int[] a1 = { 73, 74, 75, 71, 69, 72, 76, 73 };
		print(nextGreater(a1)); // 1, 1, 4, 2, 1, 1, 0, 0

		int[] a2 = { 30, 40, 50, 60 };
		print(nextGreater(a2)); // 1, 1, 1, 0

		int[] a3 = { 30, 60, 90 };
		print(nextGreater(a3)); // 1, 1, 0
	}
}
