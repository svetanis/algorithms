package com.svetanis.algorithms.twopointers;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 11. Container With Most Water
//
// Input: heights of n vertical lines standing at x = 0, 1, ..., n - 1 (n >= 2).
// Return: the most water two of the lines can hold together with the x-axis.
// Two lines at positions i < j hold min(height[i], height[j]) * (j - i): the water
// spills over the shorter line, and the width is the DISTANCE between the lines,
// j - i, not the count of positions j - i + 1. The lines in between do not matter.
//
// The one idea: start with the widest container, the two ends. Every other container
// that uses the SHORTER end line is narrower, and its water is still capped by that
// short line, so none of them can beat the current one -- throw the shorter line
// away for good. Throwing away the taller one can lose the answer (on 1,8,6,2,5,4,8,3,7
// it loses 49). With equal heights either one may go, by the same argument.
//
// Two methods, the same algorithm -- they differ only in the seed for max:
//   maxWater(List<Integer>) -- seeds 0
//   maxArea(int[])          -- seeds Integer.MIN_VALUE
// They agree on every input with at least two lines; on a single line neither loop
// runs, and maxArea returns Integer.MIN_VALUE where maxWater returns 0.
// Not to be confused with LC 42 Trapping Rain Water, where every bar holds water on
// top of it: on 2,0,2 this problem answers 4 (width 2, height 2), LC 42 answers 2.
//
// Time: O(n) -- every step throws away one line, so at most n - 1 steps.
// Space: O(1).
// The area fits in int because heights are at most 10^4 and n at most 10^5:
// 99,999 * 10,000 < 2^31.

public final class ContainerWithMostWater {

	public static int maxWater(List<Integer> list) {
		int max = 0;
		int left = 0;                                    // START: the widest container
		int right = list.size() - 1;
		while (left < right) {                           // STOP: a container needs two lines
			int width = right - left;                    // MEASURE: distance, no + 1
			int height = min(list.get(left), list.get(right)); // MEASURE: the shorter line
			max = max(max, width * height);              // RECORD before a line is thrown away
			if (list.get(left) < list.get(right)) {
				left++;                                  // DROP LEFT: the shorter line
			} else {
				right--;                                 // DROP RIGHT: shorter, or equal
			}
		}
		return max;
	}

	public static int maxArea(int[] a) {
		int left = 0;                                    // START: the widest container
		int right = a.length - 1;
		int max = Integer.MIN_VALUE;
		while (left < right) {                           // STOP: a container needs two lines
			int min = Math.min(a[left], a[right]);       // MEASURE: the shorter line
			int area = min * (right - left);             // MEASURE: times the distance
			max = Math.max(max, area);                   // RECORD before a line is thrown away
			if (a[left] < a[right]) {
				++left;                                  // DROP LEFT: the shorter line
			} else {
				--right;                                 // DROP RIGHT: shorter, or equal
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxWater(asList(2, 0, 2)));                               // 4
		System.out.println(maxWater(asList(3, 0, 0, 2, 0, 4)));                      // 15
		System.out.println(maxWater(asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));    // 14
		System.out.println(maxWater(asList(1, 8, 6, 2, 5, 4, 8, 3, 7)));             // 49

		int[] a = { 2, 5, 8, 1, 3, 7 };
		System.out.println(maxArea(a)); // 21

		int[] a1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(maxArea(a1)); // 49

		int[] a2 = { 1, 1 };
		System.out.println(maxArea(a2)); // 1
	}
}
