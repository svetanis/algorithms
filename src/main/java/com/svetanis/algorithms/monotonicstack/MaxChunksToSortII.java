package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 768. Max Chunks To Make Sorted II
//
// Any integers, duplicates allowed. Split the array into the most chunks such that sorting
// each chunk on its own, then joining them, gives the whole array sorted.
//
// The stack holds one entry per chunk: its largest value, never falling from bottom to top.
// A value smaller than some chunk's maximum must be sorted into that chunk, so every chunk
// whose maximum exceeds it merges into one, which keeps the largest maximum among them.

public final class MaxChunksToSortII {
	// Time Complexity: O(n), every value is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int maxChunks(int[] arr) {
		Deque<Integer> stack = new ArrayDeque<>(); // one maximum per chunk
		for (int value : arr) {
			if (stack.isEmpty() || stack.peek() <= value) {
				stack.push(value); // a new chunk, whose maximum is value
			} else {
				int chunkMax = stack.pop(); // the maximum of the chunk value merges into
				while (!stack.isEmpty() && stack.peek() > value) {
					stack.pop(); // absorb every chunk whose maximum exceeds value
				}
				stack.push(chunkMax); // the merged chunk keeps the largest maximum
			}
		}
		return stack.size();
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
