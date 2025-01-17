package com.svetanis.algorithms.twopointers;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 11. Container With Most Water

// Suppose you are given an array containing non-negative numbers 
// representing heights of a set of buildings. 
// Now, because of differences in heights of buildings water can be trapped between them. 
// Find the two buildings that will trap the most amount of water. 
// Write a function that will return the maximum volume of water 
// that will be trapped between these two buildings.

public final class MaxTrappingWater {
	// Time Complexity: O(n)

	public static int maxWater(List<Integer> list) {
		int max = 0;
		int left = 0;
		int right = list.size() - 1;
		while (left < right) {
			int width = right - left;
			int height = min(list.get(left), list.get(right));
			max = max(max, width * height);
			if (list.get(left) < list.get(right)) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}

	public static int maxArea(int[] a) {
		int left = 0;
		int right = a.length - 1;
		int max = Integer.MIN_VALUE;
		while (left < right) {
			int min = Math.min(a[left], a[right]);
			int area = min * (right - left);
			max = Math.max(max, area);
			if (a[left] < a[right]) {
				++left;
			} else {
				--right;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxWater(asList(2, 0, 2)));
		System.out.println(maxWater(asList(3, 0, 0, 2, 0, 4)));
		System.out.println(maxWater(asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));
		System.out.println(maxWater(asList(1, 8, 6, 2, 5, 4, 8, 3, 7)));

		int[] a = { 2, 5, 8, 1, 3, 7 };
		System.out.println(maxArea(a)); // 21

		int[] a1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(maxArea(a1)); // 49

		int[] a2 = { 1, 1 };
		System.out.println(maxArea(a2)); // 1
	}
}
