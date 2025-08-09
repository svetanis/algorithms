package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2410. Maximum Matching of Players With Trainers
// same as 455. Assign Cookies

public final class MatchPlayersWithTrainers {
	// Time Complexity: O(n log n + m log m)

	public static int matchPlayers(int[] players, int[] trainers) {
		int n = players.length;
		int m = trainers.length;
		if (n == 0 || m == 0) {
			return 0;
		}
		Arrays.sort(players);
		Arrays.sort(trainers);
		int count = 0;
		int i = 0, j = 0;
		while (i < n && j < m) {
			if (trainers[j] >= players[i]) {
				count++;
				i++;
			}
			j++;
		}
		return count;
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
