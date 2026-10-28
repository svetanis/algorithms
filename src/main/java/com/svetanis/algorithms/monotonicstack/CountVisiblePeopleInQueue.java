package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 1944. Number of Visible People in a Queue
//
// People stand in a line. Person i sees person j to the right when
// everyone between them is shorter than both. For each person, count how many they see.
// heights[i] is the height of person i, left to right; the answer is an int array whose
// cell i holds how many people person i sees.
//
// Scan from the right. The stack holds the heights someone further left could still see,
// rising from top to bottom. Person i sees every shorter one they pop, and the first taller
// one left on top, which blocks everything behind it.

public final class CountVisiblePeopleInQueue {
	// Time Complexity: O(n), every person is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	// LC 1944 guarantees distinct heights; this version also handles equal ones. The pop is a
	// strict '<', so a person of EQUAL height stays on top: i sees them, and from then on i
	// hides them from everyone further left, so they are popped right after being counted.
	// Without that pop, {4, 1, 1, 5, 5} gives 3 for person 0 where the answer is 2.
	public static int[] countVisible(int[] heights) {
		int n = heights.length;
		int[] visible = new int[n]; // ANSWER: visible[i] counts the people i sees
		Deque<Integer> stack = new ArrayDeque<>(); // heights, not positions
		for (int i = n - 1; i >= 0; i--) { // WALK: right to left, person i arrives
			while (!stack.isEmpty() && stack.peek() < heights[i]) { // SEE: the top is shorter than i
				stack.pop(); // POP: shorter: seen by i, and hidden by i from everyone further left
				visible[i]++; // COUNT: one more person i sees
			}
			if (!stack.isEmpty()) { // TALLER: someone taller or equal is left on top
				visible[i]++; // COUNT: the first taller person: seen, and blocks the rest
				if (stack.peek() == heights[i]) {
					stack.pop(); // EQUAL: seen by i, then hidden by i from everyone further left
				}
			}
			stack.push(heights[i]); // PUSH: i may be seen by someone further left
		}
		return visible; // RETURN: one count per person
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 6, 8, 5, 11, 9 };
		System.out.println(Arrays.toString(countVisible(a1))); // [3, 1, 2, 1, 1, 0]
		int[] a2 = { 5, 1, 2, 3, 10 };
		System.out.println(Arrays.toString(countVisible(a2))); // [4, 1, 1, 1, 0]
		int[] a3 = { 4, 1, 1, 5, 5 };
		System.out.println(Arrays.toString(countVisible(a3))); // [2, 1, 1, 1, 0]
	}
}
