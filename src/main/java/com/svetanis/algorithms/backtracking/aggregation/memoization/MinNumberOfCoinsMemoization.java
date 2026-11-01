package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 322. Coin Change
//
// Input: coins, the denominations (1 <= coins.length <= 12, 1 <= coins[i] <= 2^31 - 1), and an
// amount (0 <= amount <= 10^4). Returns the fewest coins that add up to the amount, using each
// denomination as often as needed, or -1 if no combination reaches it.
//
// The one idea: one call answers "starting with sum already paid, what is the fewest coins that
// finish exactly at amount?". The answer depends only on sum, not on which coins were used to get
// there, so one entry of the map holds that answer for one sum -- MAX_VALUE when no coins finish
// from it. Every later route to the same sum reads the entry instead of searching again.
// A coin larger than amount - sum is skipped before sum + coin is formed, because sum + coin can
// exceed the int range for coins near 2^31 - 1.
//
// Time: O(amount * n) -- at most amount distinct sums below the target, each tries n coins.
// Space: O(amount) -- the map, and a recursion up to amount / min coin deep.
//
// Not submittable at the top of the range: with a coin of 1 the recursion is amount frames deep,
// up to 10^4, which is more than a default JVM thread stack holds, so it throws
// StackOverflowError. The answers are right; the cost is the forward-accumulating sum. The
// iterative versions in com.svetanis.algorithms.dp.coins (MinCoinChangeSubmit) have no depth.
//
// Siblings: MinNumberOfCoins is the same recursion with no map, so it is exponential.

public final class MinNumberOfCoinsMemoization {

	public static int coins(int amount, List<Integer> coins) {
		Map<Integer, Integer> map = new HashMap<>();
		int min = dfs(amount, 0, coins, map);
		return min == MAX_VALUE ? -1 : min; // RETURN: MAX_VALUE means no combination
	}

	private static int dfs(int amount, int sum, List<Integer> coins,
			Map<Integer, Integer> map) {
		if (map.containsKey(sum)) { // LOOKUP: this sum was solved before
			return map.get(sum);
		}
		if (sum == amount) { // BASE: paid exactly, no more coins
			return 0;
		}
		if (sum > amount) { // BASE: overshot; unreachable now that oversized coins are skipped
			return MAX_VALUE;
		}
		int min = MAX_VALUE;
		for (int coin : coins) {
			if (coin > amount - sum) { // SKIP: would overshoot; also avoids int overflow of sum + coin
				continue;
			}
			int result = dfs(amount, sum + coin, coins, map); // TAKE: this coin next
			if (result != MAX_VALUE) {
				min = min(min, result + 1); // RECORD: the coin just used counts one
			}
		}
		map.put(sum, min); // RECORD: the answer for this sum, MAX_VALUE included
		return min;
	}

	public static void main(String[] args) {
		System.out.println(coins(11, asList(1, 2, 5))); // 3
		System.out.println(coins(3, asList(4, 5, 3))); // 1
		System.out.println(coins(0, asList(5))); // 0
		System.out.println(coins(1, asList(3))); // -1
		System.out.println(coins(3, asList(2))); // -1
		System.out.println(coins(6249, asList(186, 419, 83, 408))); // 20
	}
}
