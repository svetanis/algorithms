package com.svetanis.algorithms.twopointers;

// 42. Trapping Rain Water
//
// Input: n >= 1 non-negative bar heights; every bar is 1 wide.
// Return: the total water held on top of the bars after rain.
//
// The one idea: water stands on top of EACH bar, and on bar i its surface is
//   min(tallest bar in 0..i, tallest bar in i..n-1)        -- both ranges include i
// so the water there is that surface minus height[i]. Including bar i in both ranges
// keeps the amount from going negative: a bar taller than everything around it gets 0.
// Instead of scanning both ways for every bar (O(n^2)), fill two helper arrays once:
// left[i] builds on left[i - 1] from the left, right[i] on right[i + 1] from the right.
//
// Sibling: TrappingWaterSpaceEfficient -- the same formula with two pointers and
//   O(1) extra space; it settles a bar as soon as one side is known to be the lower.
// Not to be confused with LC 11 ContainerWithMostWater, where the bars in between
//   hold nothing: on 2,0,2 this problem answers 2, LC 11 answers 4.
//
// Time: O(n) -- three passes.
// Space: O(n) -- the two helper arrays.
// The total fits in int: at most (2 * 10^4 - 2) * 10^5 = 1,999,800,000 < 2^31.

public final class TrappingWater {

	public static int trapWater(int[] a) {
		int total = 0;
		int[] left = left(a);
		int[] right = right(a);
		for (int i = 0; i < a.length; i++) {
			total += Math.min(left[i], right[i]) - a[i]; // SETTLE: surface minus the bar
		}
		return total;
	}

	// left[i] contains height of tallest bar
	// to the left of i'th bar including itself
	private static int[] left(int[] a) {
		int n = a.length;
		int[] left = new int[n];
		left[0] = a[0];                                  // START: the first bar alone
		for (int i = 1; i < n; i++) {
			left[i] = Math.max(left[i - 1], a[i]);       // SEE: the bar itself, or taller before it
		}
		return left;
	}

	// right[i] contains height of tallest bar
	// to the right of i-th bar including itself
	private static int[] right(int[] a) {
		int n = a.length;
		int[] right = new int[n];
		right[n - 1] = a[n - 1];                         // START: the last bar alone
		for (int i = n - 2; i >= 0; i--) {               // right to left: right[i + 1] is filled first
			right[i] = Math.max(right[i + 1], a[i]);     // SEE: the bar itself, or taller after it
		}
		return right;
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

		int[] a5 = { 4, 2, 0, 3, 2, 5 };
		System.out.println(trapWater(a5)); // 9
	}
}
