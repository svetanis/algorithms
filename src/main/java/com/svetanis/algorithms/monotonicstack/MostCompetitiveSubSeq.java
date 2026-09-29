package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.Deque;

// 1673. Find the Most Competitive Subsequence
//
// Return the subsequence of length k that is smallest: compared position by position, the
// first difference decides.
//
// Remove K Digits with a different budget. A larger value followed by a smaller one should
// go, but a pop is allowed only while enough values remain to still fill k places.

public final class MostCompetitiveSubSeq {
	// Time Complexity: O(n), every value is pushed once and popped at most once
	// Space Complexity: O(k), the stack never grows past k

	public static int[] mostCompetitive(int[] nums, int k) {
		int n = nums.length;
		Deque<Integer> stack = new ArrayDeque<>(); // the subsequence so far, newest on top
		for (int index = 0; index < n; index++) {
			// stack.size() + n - index: what is held, plus what is still to come
			while (!stack.isEmpty() && stack.size() + n - index > k && stack.peek() > nums[index]) {
				stack.pop();
			}
			if (stack.size() < k) {
				stack.push(nums[index]);
			}
		}
		int[] subseq = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			subseq[i] = stack.pop(); // filled backwards: the stack is newest first
		}
		return subseq;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 2, 6 };
		print(mostCompetitive(a1, 2)); // 2,6

		int[] a2 = { 2, 4, 3, 3, 5, 4, 9, 6 };
		print(mostCompetitive(a2, 4)); // 2,3,3,4
	}
}
