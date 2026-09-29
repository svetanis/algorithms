package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// 1944. Number of Visible People in a Queue
//
// People stand in a line, all heights distinct. Person i sees person j to the right when
// everyone between them is shorter than both. For each person, count how many they see.
//
// Scan from the right. The stack holds the heights someone further left could still see,
// rising from top to bottom. Person i sees every shorter one they pop, and the first taller
// one left on top, which blocks everything behind it.

public final class CountVisiblePeopleInQueue {
	// Time Complexity: O(n), every person is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	// The loop needs the heights to be DISTINCT, as LC 1944 guarantees: it pops on a strict
	// '<', so two people of equal height both stay on the stack and both get counted, though
	// the first of them blocks the view of the second. {4, 1, 1, 5, 5} gives 3 for person 0
	// where the answer is 2.
	public static int[] countVisible(int[] heights) {
		int n = heights.length;
		int[] visible = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // heights, not positions
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() < heights[i]) {
				stack.pop(); // shorter: seen by i, and hidden by i from everyone further left
				visible[i]++;
			}
			if (!stack.isEmpty()) {
				visible[i]++; // the first taller person: seen, and blocks the rest
			}
			stack.push(heights[i]);
		}
		return visible;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 6, 8, 5, 11, 9 };
		Print.print(countVisible(a1)); // 3,1,2,1,1,0
		int[] a2 = { 5, 1, 2, 3, 10 };
		Print.print(countVisible(a2)); // 4,1,1,1,0
	}
}
