package com.svetanis.algorithms.slidingwindow.string;

// 1208. Get Equal Substrings Within Budget
//
// Input: two strings s and target of the same length, and a budget maxCost.
// Changing s's character at position i into target's character there costs the
// distance between the two character codes, |s[i] - target[i]|.
// Return: the length of the longest substring of s that can be changed into the
// matching substring of target for a total cost of at most maxCost.
// The method assumes equal lengths; a shorter target throws.
//
// The one idea: each position has a fixed cost, and no cost is negative, so adding
// a position can only raise a window's total and dropping one can only lower it.
// That is the longest-window loop: add the new position's cost, and while the
// total is over budget drop positions from the left. What remains is the longest
// affordable window ending at right.
//
// Siblings: none -- no other file in the repo solves 1208.
//
// Time: O(n) -- each position enters the window once and leaves at most once.
// Space: O(1) -- one running cost.

public final class EqualSubStrsWithinBudget {

	public static int ess(String s, String target, int maxCost) {
		int cost = 0;
		int left = 0;
		int maxLen = 0;
		for (int right = 0; right < s.length(); right++) {
			cost += Math.abs(s.charAt(right) - target.charAt(right)); // ADD: position right's cost
			while (cost > maxCost) { // SHRINK: while over budget
				cost -= Math.abs(s.charAt(left) - target.charAt(left));
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1); // MEASURE: affordable again
		}
		return maxLen;
	}

	public static void main(String[] args) {
		System.out.println(ess("abcd", "bcdf", 3)); // 3
		System.out.println(ess("abcd", "cdef", 3)); // 1
		System.out.println(ess("abcd", "acde", 0)); // 1
	}
}
