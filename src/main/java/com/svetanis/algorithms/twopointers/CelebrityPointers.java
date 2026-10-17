package com.svetanis.algorithms.twopointers;

import java.util.Optional;

// 277. Find the Celebrity
//
// Input: n people at a party, labelled 0 to n - 1, and knows(a, b) saying whether
// a knows b. A celebrity is known by every other person and knows none of them.
// Return: the celebrity's label, or Optional.empty() if there is none.
//
// The one idea: one call to knows rules out one of two people. Put left on the first
// person and right on the last. If left knows right, left is not the celebrity (a
// celebrity knows nobody); otherwise right is not (everyone knows the celebrity).
// When the two meet, every other person has been ruled out. That proves nobody else
// can be the celebrity, but not that the survivor is one, so a second pass checks
// the survivor against everybody.
//
// Sibling: Celebrity -- the same two passes, carrying one candidate left to right
//   through the guests; it returns -1 instead of an empty Optional and has the
//   LeetCode shape (knows() as an instance method).
//
// Time: O(n) -- at most 3n calls to knows: n - 1 in the first pass, 2n in the second.
// Space: O(1).
// The nested class Celebrity stands in for LeetCode's knows(); inside this file it
// hides the top-level class of the same name.

public final class CelebrityPointers {

	public static Optional<Integer> celebrity(Celebrity c) {
		int candidate = candidate(c);                         // PASS 1: the only person not ruled out
		boolean celebrity = isCelebrity(c, candidate);        // PASS 2: check that person against everyone
		return celebrity ? Optional.of(candidate) : Optional.empty();
	}

	private static int candidate(Celebrity c) {
		int left = 0;                                         // START: the two ends
		int right = c.size() - 1;
		while (left < right) {                                // STOP: one person is left
			if (c.knows(left, right)) {
				left++;                                       // DROP LEFT: a celebrity knows nobody
			} else {
				right--;                                      // DROP RIGHT: everyone knows a celebrity
			}
		}
		return left;
	}

	private static boolean isCelebrity(Celebrity c, int candidate) {
		for (int i = 0; i < c.size(); i++) {
			boolean knowsSomeone = c.knows(candidate, i);
			boolean notKnown = !c.knows(i, candidate);
			if ((i != candidate) && (knowsSomeone || notKnown)) {
				return false;                                 // RETURN false: either one breaks the definition
			}
		}
		return true;                                          // FOUND: knows nobody, known by everybody
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
		Celebrity c1 = new Celebrity(grid1);
		System.out.println(celebrity(c1)); // Optional[1]

		int[][] grid2 = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };
		Celebrity c2 = new Celebrity(grid2);
		System.out.println(celebrity(c2)); // Optional[2]

		int[][] grid3 = { { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		Celebrity c3 = new Celebrity(grid3);
		System.out.println(celebrity(c3)); // Optional.empty
	}

	private static final class Celebrity {

		private final int n;
		private final int[][] grid;                           // grid[a][b] == 1 when a knows b

		public Celebrity(int[][] grid) {
			this.grid = grid;
			this.n = grid.length;
		}

		public int size() {
			return n;
		}

		public boolean knows(int a, int b) {
			return grid[a][b] == 1;
		}
	}
}
