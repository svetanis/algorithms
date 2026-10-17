package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 948. Bag of Tokens
//
// Input: token values and a starting power; the score starts at 0. Each token may be
// played at most once, in either of two ways: face up costs its value in power and
// gains 1 score (only with enough power); face down gains its value in power and
// costs 1 score (only with score at least 1).
// Return: the largest score reached at any moment.
//
// The one idea: a point of score is worth the same whichever token buys or sells it,
// so buy low and sell high. After sorting, BUY a point with the cheapest token left;
// when that is unaffordable, SELL a point for the largest token left and try again.
// The best score is RECORDED after every buy, because a sale lowers the score and the
// score at the end need not be the best one: a last sale can leave nothing worth
// buying.
// The loop runs while low <= high: a single token left can still be bought.
//
// Time: O(n log n) -- the sort; the walk is O(n), each token is played at most once.
// Space: O(1) besides the sort, which reorders the input array.

public final class BagOfTokens {

	public static int bagOfTokens(int[] tokens, int power) {
		Arrays.sort(tokens);                       // SORT: cheapest on the left, largest on the right
		int max = 0;
		int score = 0;
		int low = 0;                               // START: the cheapest token left
		int high = tokens.length - 1;              // START: the largest token left
		while (low <= high) {                      // STOP: every token is played
			if (power >= tokens[low]) {
				power -= tokens[low++];            // BUY: one point with the cheapest token
				score++;
				max = Math.max(max, score);        // RECORD: a later sale lowers the score
			} else if (score > 0) {
				power += tokens[high--];           // SELL: one point for the largest token
				score--;
			} else {
				break;                             // STOP: cannot buy, and nothing to sell
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] tokens1 = { 100 };
		System.out.println(bagOfTokens(tokens1, 50));  // 0

		int[] tokens2 = { 200, 100 };
		System.out.println(bagOfTokens(tokens2, 150)); // 1

		int[] tokens3 = { 100, 200, 300, 400 };
		System.out.println(bagOfTokens(tokens3, 200)); // 2
	}
}
