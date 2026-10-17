package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 2491. Divide Players Into Teams of Equal Skill
//
// Input: the skills of an even number of players.
// Return: split the players into teams of two so that every team's skills add up to
// the same total, and return the sum over all teams of the product of the two skills
// (the team's "chemistry"); return -1 if no such split exists.
//
// The one idea: after sorting, the weakest player must team with a strongest one.
// Every team totals the same T. The weakest skill w needs a partner of T - w; the
// strongest skill s needs a partner of T - s, which is at least w, so s <= T - w.
// And T - w is somebody's skill, so T - w <= s. Hence T = w + s, and the two ends of
// the sorted array are a team. Remove them and the same holds for the rest, so
// pairing the ends inward is the only possible split; if any pair misses T, there is
// no split at all.
//
// Time: O(n log n) -- the sort; the walk is O(n).
// Space: O(1) besides the sort, which reorders the input array.
// The total is a long: up to 5 * 10^4 teams, each with a product up to 10^6.

public final class DividePlayers {

	public static long dividePlayers(int[] skills) {
		Arrays.sort(skills);                              // SORT: weakest left, strongest right
		int n = skills.length;
		int left = 0;                                     // START: the two ends
		int right = n - 1;
		long total = 0;
		long target = skills[0] + skills[n - 1];          // SETTLE: every team must total this
		while (left < right) {                            // STOP: every player is on a team
			if (skills[left] + skills[right] != target) {
				return -1;                                // RETURN -1: the only possible pair misses the total
			}
			total += skills[left] * skills[right];        // ADD this team's chemistry
			left++;                                       // DROP LEFT and DROP RIGHT: a team is formed
			right--;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 5, 1, 3, 4 };
		System.out.println(dividePlayers(a1)); // 22

		int[] a2 = { 3, 4 };
		System.out.println(dividePlayers(a2)); // 12

		int[] a3 = { 1, 1, 2, 3 };
		System.out.println(dividePlayers(a3)); // -1
	}
}
