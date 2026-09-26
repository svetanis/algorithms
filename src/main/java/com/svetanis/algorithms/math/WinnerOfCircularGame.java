package com.svetanis.algorithms.math;

// 1823. Find the Winner of the Circular Game

public final class WinnerOfCircularGame {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	// After the first friend leaves, the game goes on as a circle of n - 1
	// that starts k places further round. So the winner among n is the
	// winner among n - 1, moved k places on: (winner(n - 1) + k) mod n,
	// written with 1..n instead of 0..n-1, which is why 0 becomes n.
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