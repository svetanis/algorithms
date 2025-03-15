package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 456. 132 Pattern

public final class Find132Pattern {
	// Time Complexity: O(n)

	public static boolean find(int[] a) {
		if (a.length < 3) {
			return false;
		}
		int mid = Integer.MIN_VALUE;
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] < mid) {
				return true;
			}
			while (!dq.isEmpty() && dq.peek() < a[i]) {
				mid = dq.pop();
			}
			dq.push(a[i]);
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(find(a1)); // false
		int[] a2 = { 3, 1, 4, 2 };
		System.out.println(find(a2)); // true
		int[] a3 = { -1, 3, 2, 0 };
		System.out.println(find(a3)); // true
		int[] a4 = { 1, 0, 1, -4, -3 };
		System.out.println(find(a4)); // false
	}
}
