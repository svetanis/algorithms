package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// 1944. Number of Visible People in a Queue

public final class CountVisiblePeopleInQueue {
	// Time Complexity: O(n)

	public static int[] countVisible(int[] heights) {
		int[] a = new int[heights.length];
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = heights.length - 1; i >= 0; i--) {
			while (!dq.isEmpty() && dq.peek() < heights[i]) {
				dq.pop();
				a[i] += 1;
			}
			if (!dq.isEmpty()) {
				a[i] += 1;
			}
			dq.push(heights[i]);
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 6, 8, 5, 11, 9 };
		Print.print(countVisible(a1)); // 3,1,2,1,1,0
		int[] a2 = { 5, 1, 2, 3, 10 };
		Print.print(countVisible(a2)); // 4,1,1,1,0
	}
}
