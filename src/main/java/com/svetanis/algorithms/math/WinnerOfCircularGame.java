package com.svetanis.algorithms.math;

// 1823. Find the Winner of the Circular Game

public final class WinnerOfCircularGame {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int winner(int n, int k) {
		if (n == 1) {
			return 1;
		}
		int winner = (winner(n - 1, k) + k) % n;
		return winner == 0 ? n : winner;
	}

	public static void main(String[] args) {
		System.out.println(winner(5, 2)); // 3
		System.out.println(winner(6, 5)); // 1
	}
}