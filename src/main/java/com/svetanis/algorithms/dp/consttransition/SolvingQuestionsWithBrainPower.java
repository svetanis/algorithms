package com.svetanis.algorithms.dp.consttransition;

// 2140. Solving Questions With Brainpower

public final class SolvingQuestionsWithBrainPower {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static long mostPoints(int[][] questions) {
		int n = questions.length;
		Long[] dp = new Long[n];
		return dfs(questions, 0, dp);
	}

	private static long dfs(int[][] questions, int index, Long[] dp) {
		if (index >= questions.length) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int[] question = questions[index];
		int points = question[0];
		int skip = index + question[1] + 1;
		long incl = points + dfs(questions, skip, dp);
		long excl = dfs(questions, index + 1, dp);
		return dp[index] = Math.max(incl, excl);
	}

	public static void main(String[] args) {
		int[][] g1 = { { 3, 2 }, { 4, 3 }, { 4, 4 }, { 2, 5 } };
		System.out.println(mostPoints(g1)); // 5

		int[][] g2 = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 } };
		System.out.println(mostPoints(g2)); // 7
	}
}
