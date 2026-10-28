package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// 496. Next Greater Element I
//
// nums1 is a subset of nums2, all values distinct. For each value in nums1, return the first
// larger value to its right in nums2; -1 if none.
//
// One next-greater pass over nums2, stored by VALUE rather than by position, then a lookup
// per query. Storing by value is safe only because the values are distinct.
//
// Siblings:
//   NextGreater -- the same pass over one array, answers stored by position, so values may
//                  repeat

public final class NextGreater496 {
	// Time Complexity: O(n + m), n = nums1.length, m = nums2.length: every nums2 value is
	// pushed once and popped at most once, then one map lookup per nums1 value
	// Space Complexity: O(m) for the stack and the map

	public static int[] nextGreater(int[] nums1, int[] nums2) {
		Map<Integer, Integer> greaterOf = nextGreaterByValue(nums2); // PASS: one next-greater pass over nums2
		int n = nums1.length;
		int[] greater = new int[n];
		for (int i = 0; i < n; i++) { // WALK: one query per nums1 value
			greater[i] = greaterOf.getOrDefault(nums1[i], -1); // ANSWER: absent: nothing larger followed
		}
		return greater; // RETURN: one answer per nums1 value
	}

	// value -> the first larger value after it; values with none are left out
	private static Map<Integer, Integer> nextGreaterByValue(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<>(); // WAITING: values still waiting, never rising to the top
		Map<Integer, Integer> greaterOf = new HashMap<>(); // ANSWERS: value -> the first larger value after it
		for (int value : nums) { // WALK: value arrives
			while (!stack.isEmpty() && stack.peek() < value) { // STOP: value is strictly larger than the top
				greaterOf.put(stack.pop(), value); // ANSWER: value is the first larger one after it
			}
			stack.push(value); // PUSH: no answer yet
		}
		return greaterOf; // RETURN: values with no larger one are absent
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 1, 2 };
		int[] a2 = { 1, 3, 4, 2 };
		System.out.println(Arrays.toString(nextGreater(a1, a2))); // -1, 3, -1

		int[] a3 = { 2, 4 };
		int[] a4 = { 1, 2, 3, 4 };
		System.out.println(Arrays.toString(nextGreater(a3, a4))); // 3, -1
	}
}
