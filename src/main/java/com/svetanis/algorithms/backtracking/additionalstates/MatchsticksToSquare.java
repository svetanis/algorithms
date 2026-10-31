package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.Arrays;

// 473. Matchsticks to Square
//
// Input: matchsticks -- the lengths of the sticks. Returns whether all of them, each used exactly
// once and none broken, can form the four sides of a square. On LeetCode: 1 <= matchsticks.length
// <= 15, 1 <= matchsticks[i] <= 10^8. The array is sorted in place.
//
// The one idea: the four sides must each be sum / 4 long, so first rule out a total that does not
// divide by 4 and a stick longer than one side. Then place the sticks one at a time, longest
// first, on one of the four sides; dp[i] is the length of side i so far. A stick that would push
// its side past the target is not placed there, and when every stick is placed no side is over
// the target, so the four lengths add up to 4 * target and each must equal it. Longest first
// fails early: the big sticks have the fewest places to go. If side i - 1 is as long as side i
// right now, putting the stick on side i gives the same position as putting it on side i - 1, so
// it is skipped.
//
// Time: O(4^n) -- each stick tries at most four sides, so at most 4^15 (about 10^9) placements
// at the largest n; the early cut and the equal-sides skip leave far fewer. Sorting adds
// O(n log n).
// Space: O(n) -- the recursion is n deep, besides the in-place sort.

public final class MatchsticksToSquare {

	public static boolean makeSquare(int[] matchsticks) {
		int sum = Arrays.stream(matchsticks).sum();
		int max = Arrays.stream(matchsticks).max().getAsInt();
		int side = sum / 4;
		int remainder = sum % 4;
		if (remainder != 0 || side < max) { // RULE OUT: no equal sides, or a stick longer than a side
			return false;
		}
		Arrays.sort(matchsticks); // SORT: ascending, so the walk from the end is longest first
		int[] dp = new int[4]; // SIDES: the length of each side so far
		int n = matchsticks.length;
		return dfs(matchsticks, n - 1, side, dp);
	}

	private static boolean dfs(int[] matchsticks, int index, int target, int[] dp) {
		if (index < 0) { // BASE: every stick placed and no side over the target
			return true;
		}
		for (int i = 0; i < 4; i++) {
			if (i > 0 && dp[i - 1] == dp[i]) { // SKIP: this side looks the same as the one before
				continue;
			}
			dp[i] += matchsticks[index]; // PLACE: the stick on side i
			if (dp[i] <= target && dfs(matchsticks, index - 1, target, dp)) { // CUT: a side over the target
				return true;
			}
			dp[i] -= matchsticks[index]; // UNDO
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 2, 2 };
		System.out.println(makeSquare(a1)); // true
		int[] a2 = { 3, 3, 3, 3, 4 };
		System.out.println(makeSquare(a2)); // false
	}
}
