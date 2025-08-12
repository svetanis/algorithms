package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 948. Bag of Tokens

public final class BagOfTokens {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int bagOfTokens(int[] tokens, int power) {
		Arrays.sort(tokens);
		int max = 0;
		int score = 0;
		int low = 0;
		int high = tokens.length - 1;
		while (low <= high) {
			if (power >= tokens[low]) {
				power -= tokens[low++];
				score++;
				max = Math.max(max, score);
			} else if (score > 0) {
				power += tokens[high--];
				score--;
			} else {
				break;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] tokens1 = { 100 };
		System.out.println(bagOfTokens(tokens1, 50)); // 0

		int[] tokens2 = { 200, 100 };
		System.out.println(bagOfTokens(tokens2, 150)); // 1

		int[] tokens3 = { 100, 200, 300, 400 };
		System.out.println(bagOfTokens(tokens3, 200)); // 2
	}
}
