package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 768. Max Chunks To Make Sorted II

public final class MaxChunksToSort {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int maxChunks(int[] a) {
		Deque<Integer> dq = new ArrayDeque<>();
		for (int num : a) {
			if (dq.isEmpty() || dq.peek() <= num) {
				dq.push(num);
			} else {
				int top = dq.pop();
				while (!dq.isEmpty() && dq.peek() > num) {
					dq.pop();
				}
				dq.push(top);
			}
		}
		return dq.size();
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 3, 2, 1 };
		System.out.println(maxChunks(a1)); // 1

		int[] a2 = { 2, 1, 3, 4, 4 };
		System.out.println(maxChunks(a2)); // 4

		int[] a3 = { 5, 1, 1, 8, 1, 6, 5, 9, 7, 8 };
		System.out.println(maxChunks(a3)); // 1

	}
}
