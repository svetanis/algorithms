package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
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

public final class NextGreater496 {
	// Time Complexity: O(n + m), n = nums1.length, m = nums2.length
	// Space Complexity: O(m) for the stack and the map

	public static int[] nextGreater(int[] nums1, int[] nums2) {
		Map<Integer, Integer> greaterOf = nextGreaterByValue(nums2);
		int n = nums1.length;
		int[] greater = new int[n];
		for (int i = 0; i < n; i++) {
			greater[i] = greaterOf.getOrDefault(nums1[i], -1); // absent: nothing larger followed
		}
		return greater;
	}

	// value -> the first larger value after it; values with none are left out
	private static Map<Integer, Integer> nextGreaterByValue(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<>(); // values still waiting, never rising to the top
		Map<Integer, Integer> greaterOf = new HashMap<>();
		for (int value : nums) {
			while (!stack.isEmpty() && stack.peek() < value) {
				greaterOf.put(stack.pop(), value); // value is the first larger one after it
			}
			stack.push(value); // no answer yet
		}
		return greaterOf;
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
