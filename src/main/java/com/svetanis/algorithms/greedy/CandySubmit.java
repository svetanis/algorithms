package com.svetanis.algorithms.greedy;

// 135. Candy
//
// Input: ratings[i] -- the rating of child i; the children stand in a row. Every child gets at
// least one candy, and a child rated higher than a neighbour gets more candy than that neighbour.
// Returns the fewest candies needed in total. On LeetCode: 1 <= ratings.length <= 2 * 10^4,
// 0 <= ratings[i] <= 2 * 10^4.
//
// The one idea: read the ratings as runs that rise, runs that fall, and equal neighbours, and add
// each child's candy the moment it is reached. On a rise, each child gets one more than the child
// before it, so the k-th step up costs k + 1, and peak remembers what the child at the top got.
// On an equal rating the new child needs only 1, and every count starts over. On a fall the new
// child is the lowest so far and gets 1, and every earlier child of the fall must now have one
// more than it had, so the d-th step down costs d in all: it is the same as putting a 1 at the
// bottom and raising each of the d - 1 children above it by one. The child at the top of the fall
// must still have more than the child right after it, which now holds d. Once d reaches what the
// top child got on the way up (peak <= decreasing), the top child is raised as well, on this step
// and on every later step of the fall, which is the extra 1. Each step pays for exactly the
// candies that step makes necessary, so nothing is ever taken back.
//
// Time: O(n) -- one pass.
// Space: O(1) -- four counters.
// The total is at most n(n + 1) / 2, about 2 * 10^8, which fits an int. An empty row returns 1
// where 0 is right; LeetCode promises at least one child.
//
// Siblings: Candy holds two methods that fill each child's demand from the left and from the
// right in two passes over an array, and take the larger of the two.

public final class CandySubmit {

	public static int candy(int[] ratings) {
		int increasing = 0; // steps in the current rise
		int decreasing = 0; // children in the current fall, after its top child
		int peak = 0; // what the top child of the current fall got on the way up; 0 acts as 1
		int total = 1; // BASE: the first child gets 1
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i]) { // RISE
				increasing++;
				peak = increasing + 1; // this child gets one more than the child before it
				decreasing = 0;
				total += peak;
			} else if (ratings[i] == ratings[i - 1]) { // EQUAL: this child needs only 1
				peak = 0;
				increasing = 0;
				decreasing = 0;
				total++;
			} else { // FALL
				decreasing++;
				increasing = 0;
				total += decreasing + (peak > decreasing ? 0 : 1); // RAISE the fall, and its top once it is caught up
			}
		}
		return total; // RETURN: the total
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 2 };
		int[] a2 = { 1, 2, 2 };

		System.out.println(candy(a1)); // 5
		System.out.println(candy(a2)); // 4
	}
}
