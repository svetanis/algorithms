package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// 503. Next Greater Element II
//
// The array is circular: after the last element comes the first again. For each element,
// return the first larger element going around; -1 if none.
//
// Two passes. The first is the plain next-greater pass. The second walks the values from the
// start again, now standing to the right of everything still waiting, and only answers:
// nothing is pushed. NextGreaterCircularSubmit does the same in one loop of 2n steps.

public final class NextGreaterCircular {
	// Time Complexity: O(n), every element is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static List<Integer> nextGreater(List<Integer> nums) {
		int n = nums.size();
		Integer[] greater = new Integer[n];
		fill(greater, -1);

		Deque<Element> stack = new ArrayDeque<>(); // values never rising to the top
		for (int i = 0; i < n; i++) {
			int value = nums.get(i);
			while (!stack.isEmpty() && value > stack.peek().value()) {
				int index = stack.pop().index(); // value is the first larger one after it
				greater[index] = value;
			}
			stack.push(new Element(value, i));
		}
		// the wrap-around. No isEmpty() guard is needed: the largest value is never popped,
		// since nothing is strictly larger, so the stack always holds at least that one.
		// An empty list never enters this loop.
		for (int value : nums) {
			while (value > stack.peek().value()) {
				int index = stack.pop().index(); // answered from the far side of the circle
				greater[index] = value;
			}
		}
		return asList(greater);
	}

	// a waiting value and the position its answer is written to
	private record Element(int value, int index) {
	}

	public static void main(String[] args) {
		print(nextGreater(asList(1, 2, 1))); // 2, -1, 2
		// 2, 4, 2, 2, 2, 4, -1, 2
		print(nextGreater(asList(1, 2, 1, 1, 1, 2, 4, 1)));
		// 5, 6, 8, 9, -1, 2, 4, 2, 4
		print(nextGreater(asList(4, 5, 6, 8, 9, 1, 2, 1, 2)));
		// 6, 3, 6, 8, 4, 8, -1, 8, 3, 8, -1, 8
		print(nextGreater(asList(5, 2, 3, 6, 1, 4, 8, 7, 2, 3, 8, 6)));
		// 10, 10, -1, 10, 10, 10, 10, 10, 10, 10
		print(nextGreater(asList(2, 1, 10, 9, 8, 7, 6, 5, 4, 3)));
		// 4, 5, 6, 4, 5, 6, 4, 5, 6, 7, -1, 2, 3
		print(nextGreater(asList(3, 4, 5, 3, 4, 5, 3, 4, 5, 6, 7, 1, 2)));
	}
}
