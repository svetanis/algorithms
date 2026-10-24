package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 945. Minimum Increment to Make Array Unique
//
// Input: a -- an array of numbers that are 0 or more. One move adds 1 to one element. Returns the
// fewest moves that make every element different. On LeetCode: 1 <= a.length <= 10^5, 0 <= a[i] <=
// 10^5, and the tests are made so that the answer fits in a 32-bit int -- which is why the count is
// an int. An input outside that promise, such as 10^5 copies of 10^5, overflows it.
//
// The one idea: elements only go up, so after sorting give each element, from the smallest up, the
// smallest value that is at least its own and above the value given to the element before it.
// Keeping the sorted order loses nothing: if a smaller element ended up above a larger one, the two
// could trade final values for the same total number of moves, and both would still be at least
// their starting values. In that order each element must end above the one before it, and giving
// it the smallest such value costs the least now and leaves the most room for every element after
// it, so a choice never needs to be undone. minAvailable is the value given to the element just
// placed, and the next element gets the larger of minAvailable + 1 and its own value.
//
// Time: O(n log n) -- the sort; one pass after it.
// Space: O(log n) -- the sort works inside the caller's array (and reorders it); its recursion keeps
// O(log n) frames.

public final class MinIncrement {

	public static int minIncrement(int[] a) {
		Arrays.sort(a); // SORT: place the smallest first
		int totalIncrements = 0;
		int minAvailable = -1; // the value given to the element before; none yet
		for (int num : a) {
			minAvailable = Math.max(minAvailable + 1, num); // PLACE at the smallest free value >= num
			totalIncrements += minAvailable - num; // COUNT the moves it took to get there
		}
		return totalIncrements;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 2 };
		System.out.println(minIncrement(a)); // 1

		int[] a1 = { 3, 2, 1, 2, 1, 7 };
		System.out.println(minIncrement(a1)); // 6
	}
}
