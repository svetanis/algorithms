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

public final class MaxFruitCountOfTwoTypes {
	// Time complexity: O(n)

	public static int maxLen(char[] a) {
		int n = a.length;
		int left = 0; // current start
		int max = 0; // max window size
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char next = a[right];
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq);
			// shrink the sliding window, until k
			// distinct chars left in frequency map
			while (map.size() > 2) {
				char front = a[left];
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front);
				}
				left++; // shrink the window
			}
			max = max(max, right - left + 1);
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
