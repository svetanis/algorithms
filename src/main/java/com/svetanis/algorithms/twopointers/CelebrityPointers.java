package com.svetanis.algorithms.twopointers;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

// 277. Find the Celebrity

// you're at a party with n people
// each labeled with a unique number
// between 0 and n - 1. your goal is
// to identify a possible celebrity
// among them. a celebrity is defined
// by two conditions: 
// 1. every other person at the party
// knows the celebrity
// 2. the celebrity does not know 
// anyone else at the party

// Two pointer approach:
// if left knows right,
// then left can't be a celebrity
// because according to rule 2
// celebrity doesn't know anyone

// if left doesn't know right,
// then right can't be a celebrity
// because according to rule 1
// everyone knows the celebrity

public final class CelebrityPointers {
	// Time Complexity: O(n)

	public static Optional<Integer> celebrity(Celebrity c) {
		// first pass: identify a celebrity candidate
		int candidate = candidate(c);
		// second pass: validate the candidate
		boolean celebrity = isCelebrity(c, candidate);
		return celebrity ? of(candidate) : absent();
	}

	private static int candidate(Celebrity c) {
		int left = 0;
		int right = c.size() - 1;
		while (left < right) {
			if (c.knows(left, right)) {
				left++;
			} else {
				right--;
			}
		}
		return left;
	}

	private static boolean isCelebrity(Celebrity c, int candidate) {
		// check if candidate is actually celebrity of not
		for (int i = 0; i < c.size(); i++) {
			// celebrity candidate knows someone
			boolean one = c.knows(candidate, i);
			// someone doesn't know celebrity candidate
			boolean two = !c.knows(i, candidate);
			if ((i != candidate) && (one || two)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
		Celebrity c1 = new Celebrity(grid1);
		System.out.println(celebrity(c1));

		int[][] grid2 = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };
		Celebrity c2 = new Celebrity(grid2);
		System.out.println(celebrity(c2));
	}

	private static final class Celebrity {

		private final int n;
		private final int[][] grid;

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
