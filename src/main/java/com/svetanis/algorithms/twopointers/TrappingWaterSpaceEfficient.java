package com.svetanis.algorithms.twopointers;

// 42. Trapping Rain Water
//
// Input: n >= 1 non-negative bar heights; every bar is 1 wide.
// Return: the total water held on top of the bars after rain.
//
// The one idea: the water on a bar is min(tallest on its left, tallest on its right)
// minus the bar, and the min only needs the LOWER side exactly. Walk in from both ends,
// each pointer carrying the tallest bar its side has seen. When a[left] <= a[right],
// the right side -- counting the bar under the right pointer -- already holds a bar at
// least as tall as maxLeft, so unseen bars can only raise the right side: the left
// bar's surface is maxLeft, settle it and move in. Mirror on the other branch.
//
// Two ways to pick the side, both correct: compare the two BARS (here) or the two
// MAXES (leftMax < rightMax). The maxes start from the end bars, not from 0 -- a 0
// would act as a wall of height 0 at both edges if heights could be negative.
//
// Sibling: TrappingWater -- the same formula with two helper arrays, O(n) space.
//
// Time: O(n) -- every step settles one bar.
// Space: O(1).

public final class TrappingWaterSpaceEfficient {

	public static int trapWater(int[] a) {
		int n = a.length;
		int total = 0;
		int left = 0;                                    // START: the two ends
		int right = n - 1;
		int maxLeft = a[left];                           // START: tallest seen from each side
		int maxRight = a[right];
		while (left < right) {                           // STOP: the last bar is the tallest, it holds 0
			if (a[left] <= a[right]) {                   // the right side is tall enough for the left bar
				maxLeft = Math.max(maxLeft, a[left]);    // SEE
				total += maxLeft - a[left];              // SETTLE LEFT
				left++;                                  // MOVE
			} else {
				maxRight = Math.max(maxRight, a[right]); // SEE
				total += maxRight - a[right];            // SETTLE RIGHT
				right--;                                 // MOVE
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 0, 2 };
		System.out.println(trapWater(a1)); // 2

		int[] a2 = { 3, 0, 0, 2, 0, 4 };
		System.out.println(trapWater(a2)); // 10

		int[] a3 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trapWater(a3)); // 6

		int[] a4 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(trapWater(a4)); // 19
	}
}
