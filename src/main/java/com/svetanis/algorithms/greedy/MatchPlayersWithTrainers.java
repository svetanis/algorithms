package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2410. Maximum Matching of Players With Trainers
//
// Input: players[i] -- the ability of player i; trainers[j] -- the training capacity of trainer j.
// Player i can be matched with trainer j when players[i] <= trainers[j]; each player and each
// trainer is matched at most once. Returns the largest number of matches. On LeetCode:
// 1 <= players.length, trainers.length <= 10^5, 1 <= players[i], trainers[j] <= 10^9.
//
// The one idea: sort both, then walk the trainers from the weakest up, with i on the weakest
// player still unmatched. A trainer too weak for player i is too weak for every player after i
// as well -- they are all at least as able -- so the trainer is skipped. A trainer strong enough
// for player i is matched with player i: giving that trainer to a stronger player instead would
// leave player i needing a trainer at least this strong, so it could never make more matches.
// Each decision is final, and the walk never goes back.
//
// Time: O(n log n + m log m) -- n = players.length, m = trainers.length: the two sorts. The walk
// moves j forward on every step, so it takes at most m steps.
// Space: O(1) -- beyond the sorts, which work in place: both arrays come back sorted.
//
// Siblings: AssignCookies (455. Assign Cookies) is the same problem with children for players and
// cookies for trainers, and the same code.

public final class MatchPlayersWithTrainers {

	public static int matchPlayers(int[] players, int[] trainers) {
		int n = players.length;
		int m = trainers.length;
		if (n == 0 || m == 0) { // BASE: nobody to match
			return 0;
		}
		Arrays.sort(players); // SORT: weakest player first
		Arrays.sort(trainers); // SORT: weakest trainer first
		int count = 0;
		int i = 0, j = 0; // i -- the next player waiting; j -- the next trainer
		while (i < n && j < m) {
			if (trainers[j] >= players[i]) { // TAKE: strong enough for player i
				count++;
				i++; // player i is matched; the next player waits
			}
			j++; // the trainer is matched, or too weak for every player left and skipped
		}
		return count; // RETURN: matches
	}

	public static void main(String[] args) {
		int[] p1 = { 4, 7, 9 };
		int[] t1 = { 8, 2, 5, 8 };
		System.out.println(matchPlayers(p1, t1)); // 2

		int[] p2 = { 1, 1, 1 };
		int[] t2 = { 10 };
		System.out.println(matchPlayers(p2, t2)); // 1
	}
}
