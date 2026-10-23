package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 904. Fruit Into Baskets
//
// Input: an array where a[i] is the fruit type of the i-th tree in a row.
// You carry two baskets, each holding one type of fruit in any amount; you start
// at any tree, pick one fruit from every tree moving right, and stop at the first
// tree whose type fits neither basket.
// Return: the most fruit you can pick.
//
// The one idea: the trees picked form one unbroken run holding at most two fruit
// types, so this is the longest window with at most two different values. Adding
// a tree can break the window (a third type) and never repair it, so a broken
// window drops trees from the left until two types remain; then left..right is the
// longest allowed run ending at right.
//
// Siblings:
//   MaxFruitCountOfTwoTypes -- the same loop over a char[] of tree labels.
//   LongestSubStrLenAtMostTwoDistinctChars (159) -- the same loop on a string.
//   LongestSubStrLenAtMostKUnique (340) -- the loop for any k.
//
// Time: O(n) -- each tree enters the window once and leaves at most once.
// Space: O(1) -- the map holds at most three fruit types.

public final class MaxFruitIntoBaskets {

	public static int maxLen(int[] a) {
		int n = a.length;
		int left = 0; // current start
		int max = 0; // max window size
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			int next = a[right];
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq); // ADD
			while (map.size() > 2) { // SHRINK: while three fruit types
				int front = a[left];
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front); // REMOVE: count 0, so map.size() stops counting it
				}
				left++;
			}
			max = Math.max(max, right - left + 1); // MEASURE: two types at most again
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1 };
		System.out.println(maxLen(a1)); // 3

		int[] a2 = { 0, 1, 2, 2 };
		System.out.println(maxLen(a2)); // 3

		int[] a3 = { 1, 2, 3, 2, 2 };
		System.out.println(maxLen(a3)); // 4
	}
}
