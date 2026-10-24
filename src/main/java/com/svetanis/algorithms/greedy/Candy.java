package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;

import java.util.Arrays;

// 135. Candy
//
// Input: ratings[i] -- the rating of child i; the children stand in a row. Every child gets at
// least one candy, and a child rated higher than a neighbour gets more candy than that neighbour.
// Returns the fewest candies needed in total. On LeetCode: 1 <= ratings.length <= 2 * 10^4,
// 0 <= ratings[i] <= 2 * 10^4.
//
// The one idea: every child faces two separate demands, one from each side. From the left: a
// child rated higher than the child on its left needs one more candy than that child; otherwise 1
// is enough. A pass left to right reaches each child after its left neighbour is settled, so it
// fills in the left demand for everyone. A pass right to left does the same for the right demand.
// A child must meet both, so it gets the larger of its two numbers, and nothing smaller would do,
// because each number is the least that its side allows. Taking the larger never breaks a rule:
// when child i is rated above its right neighbour, that neighbour's left demand is 1 (it is not
// rated above child i), so the neighbour ends with its right demand, and child i's right demand
// is already one more than that. The left side is the same picture reflected.
//
// The two methods are the same idea. candy2 keeps the two demands in two arrays and adds up the
// larger of each pair in a third loop. candy keeps one array: it holds the left demand, then each
// child's right demand is written over it during the right-to-left pass, and the total is added
// up in that same pass.
//
// Time: O(n) -- candy makes two passes over the row, candy2 three plus the sum.
// Space: O(n) -- candy one array of n, candy2 three.
// The total is at most 1 + 2 + ... + n = n(n + 1) / 2, about 2 * 10^8, which fits an int. An
// empty row throws; LeetCode promises at least one child.
//
// Siblings: CandySubmit makes one pass and keeps no array: it counts the lengths of the rising
// and falling runs of ratings and adds each child's candy as it goes.

public final class Candy {

	public static int candy(int[] ratings) {
		int n = ratings.length;
		int[] candies = new int[n]; // candies[i] -- child i's left demand, later its right demand
		candies[0] = 1; // BASE: the first child has no left neighbour
		// from left to right
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1; // LEFT DEMAND: one more than the child on the left
			} else {
				candies[i] = 1;
			}
		}
		int count = candies[n - 1]; // the last child has no right neighbour: its left demand is final
		// from right to left
		for (int i = n - 2; i >= 0; i--) {
			int current = 1; // RIGHT DEMAND of child i
			if (ratings[i] > ratings[i + 1]) {
				current = candies[i + 1] + 1; // candies[i + 1] already holds child i + 1's right demand
			}
			count += max(current, candies[i]); // TAKE the larger of the two demands
			candies[i] = current; // OVERWRITE: the next step left reads the right demand
		}
		return count; // RETURN: the total
	}

	public static int candy2(int[] ratings) {
		int n = ratings.length;

		int[] left = new int[n]; // left[i] -- the least child i needs, judged against its left side
		left[0] = 1;
		// from left to right
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				left[i] = left[i - 1] + 1; // LEFT DEMAND: one more than the child on the left
			} else {
				left[i] = 1;
			}
		}

		int[] right = new int[n]; // right[i] -- the same, judged against its right side
		right[n - 1] = 1;
		// from right to left
		for (int i = n - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				right[i] = right[i + 1] + 1; // RIGHT DEMAND: one more than the child on the right
			} else {
				right[i] = 1;
			}
		}

		int[] candies = new int[n];
		for (int i = 0; i < n; i++) {
			candies[i] = max(left[i], right[i]); // TAKE the larger: both sides are satisfied
		}
		return Arrays.stream(candies).sum(); // RETURN: the total
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 2 };
		int[] a2 = { 1, 2, 2 };

		System.out.println(candy(a1)); // 5
		System.out.println(candy(a2)); // 4
	}
}
