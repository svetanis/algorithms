package com.svetanis.algorithms.dp.interval;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// you and a friend are playing a game 
// with n coins arranged in a straight line,
// where each coin has a distinct value.
// starting with you, both players take turns
// picking one coin from either end of the line
// and adding its value to their individual scores
// once a coin is picked up, it's removed from the line
// given that your friend plays optimally to achieve
// the highest score, determine your max possible score

// choose the case that gives the greatest 
// score or minimizes the opponent's score
// max(sum(l,r) - maxScore(l+1, r), sum(l, r) - maxScore(l, r-1))
// or
// sum(l,r) - min(maxScore(l+1,r), maxScore(l,r-1))

public final class CoinGamePrefixSum {
	// Time Complexity: O(2^n)

	public static int game(List<Integer> coins) {
		int n = coins.size();
		List<Integer> prefixSum = prefixSum(coins);
		return maxScore(1, n, coins, prefixSum);
	}

	private static List<Integer> prefixSum(List<Integer> coins) {
		List<Integer> prefixSum = new ArrayList<>();
		prefixSum.add(0);
		for (int i = 1; i <= coins.size(); i++) {
			prefixSum.add(prefixSum.get(i - 1) + coins.get(i - 1));
		}
		return prefixSum;
	}

	private static int maxScore(int left, int right, 
			List<Integer> coins, List<Integer> prefixSum) {
		int sum = prefixSum.get(right) - prefixSum.get(left - 1);
		if (left == right) {
			return sum;
		}
		int leftPick = maxScore(left + 1, right, coins, prefixSum);
		int rightPick = maxScore(left, right - 1, coins, prefixSum);
		return sum - min(leftPick, rightPick);
	}

	public static void main(String[] args) {
		System.out.println(game(asList(8, 15, 3, 7))); // 22
		System.out.println(game(asList(2, 2, 2, 2))); // 4
		System.out.println(game(asList(20, 30, 2, 2, 2, 10))); // 42
		System.out.println(game(asList(25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10))); // 140

		System.out.println(game(asList(5, 3, 4, 5))); // 9
		System.out.println(game(asList(4, 4, 9, 4))); // 13
		System.out.println(game(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))); // 55
		System.out.println(game(asList(1, 2, 9999, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))); // 10104
	}
}
