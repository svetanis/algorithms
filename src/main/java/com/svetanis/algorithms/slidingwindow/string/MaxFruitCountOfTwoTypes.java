package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// 904. Fruit Into Baskets

// Given an array of characters where each character represents a fruit tree.
// The farm has following restrictions:
//	1. Each basket can have only one type of fruit.
//     There is no limit to how many fruit a basket can hold.
//	2. You can start with any tree, but you can’t skip a tree once you have started.
//	3. You will pick exactly one fruit from every tree until you cannot,
//     i.e., you will stop when you have to pick from a third fruit type.
// Write a function to return the maximum number of fruits in both baskets.

// The one idea: the trees picked form one unbroken run holding at most two
// fruit types, so this is the longest window with at most two different
// values. Adding a tree can break the window (a third type) and never repair
// it, so a broken window drops trees from the left until two types remain;
// then left..right is the longest allowed run ending at right.

// Siblings:
//   MaxFruitIntoBaskets -- the same loop over an int[], the form LeetCode passes.
//   LongestSubStrLenAtMostTwoDistinctChars (159) -- the same loop on a string.
//   LongestSubStrLenAtMostKUnique (340) -- the loop for any k.

// Time: O(n) -- each tree enters the window once and leaves at most once.
// Space: O(1) -- the map holds at most three fruit types.

public final class MaxFruitCountOfTwoTypes {

	public static int maxLen(char[] a) {
		int n = a.length;
		int left = 0; // current start
		int max = 0; // max window size
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char next = a[right];
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq); // ADD
			while (map.size() > 2) { // SHRINK: while three fruit types
				char front = a[left];
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front); // REMOVE: count 0, so map.size() stops counting it
				}
				left++;
			}
			max = max(max, right - left + 1); // MEASURE: two types at most again
		}
		return max;
	}

	public static void main(String[] args) {
		char[] a1 = { 'A', 'B', 'C', 'A', 'C' };
		System.out.println(maxLen(a1));

		char[] a2 = { 'A', 'B', 'B', 'B', 'C' };
		System.out.println(maxLen(a2));
	}
}
