package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 1673. Find the Most Competitive Subsequence
//
// Return the subsequence of length k that is smallest: compared position by position, the
// first difference decides.
//
// Remove K Digits with a different budget. A larger value followed by a smaller one should
// go, but a pop is allowed only while enough values remain to still fill k places.
//
// Input: an array of integers nums and k, 1 <= k <= nums.length. Returns: the k values of the
// most competitive subsequence, in their original order.
//
// Related:
//   RemoveKDigits, RemoveKDigitsDeque -- 402, the same pop rule, budgeted by k removals
//                                        instead of k places to fill

public final class MostCompetitiveSubSeq {
	// Time Complexity: O(n), every value is pushed once and popped at most once
	// Space Complexity: O(k), the stack never grows past k

	public static int[] mostCompetitive(int[] nums, int k) {
		int n = nums.length;
		Deque<Integer> stack = new ArrayDeque<>(); // KEPT: the subsequence so far, newest on top
		for (int index = 0; index < n; index++) { // WALK: nums[index] arrives
			// stack.size() + n - index: what is held, plus what is still to come
			while (!stack.isEmpty() && stack.size() + n - index > k && stack.peek() > nums[index]) { // BEAT: smaller than the top, k still fillable
				stack.pop(); // POP: the larger value leaves
			}
			if (stack.size() < k) { // ROOM: fewer than k values held
				stack.push(nums[index]); // PUSH: nums[index] joins the subsequence
			}
		}
		int[] subseq = new int[k]; // ANSWER: k places
		for (int i = k - 1; i >= 0; i--) { // END: empty the stack into the answer
			subseq[i] = stack.pop(); // FILL: filled backwards: the stack is newest first
		}
		return subseq; // RETURN: the most competitive subsequence
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 2, 6 };
		System.out.println(Arrays.toString(mostCompetitive(a1, 2))); // 2,6

		int[] a2 = { 2, 4, 3, 3, 5, 4, 9, 6 };
		System.out.println(Arrays.toString(mostCompetitive(a2, 4))); // 2,3,3,4
	}
}
