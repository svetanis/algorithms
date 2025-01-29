package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// 496. Next Greater Element I

public final class NextGreater496 {
	// Time complexity: O(n)

	public static int[] nextGreater(int[] a1, int[] a2) {
		Map<Integer, Integer> map = nextGreater(a2);
		int n = a1.length;
		int[] greater = new int[n];
		for (int i = 0; i < n; i++) {
			greater[i] = map.getOrDefault(a1[i], -1);
		}
		return greater;
	}

	private static Map<Integer, Integer> nextGreater(int[] a) {
		Deque<Integer> dq = new ArrayDeque<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int element : a) {
			while (!dq.isEmpty() && dq.peekLast() < element) {
				map.put(dq.pollLast(), element);
			}
			dq.addLast(element);
		}
		return map;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 1, 2 };
		int[] a2 = { 1, 3, 4, 2 };
		print(nextGreater(a1, a2)); // -1, 3, -1

		int[] a3 = { 2, 4 };
		int[] a4 = { 1, 2, 3, 4 };
		print(nextGreater(a3, a4)); // 3, -1
	}
}