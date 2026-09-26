package com.svetanis.algorithms.math;

// 292. Nim Game

// With 1, 2 or 3 stones you take them all and win. With 4, whatever you
// take leaves the other player 1, 2 or 3 -- you lose. With 5, 6 or 7 you
// leave them 4. And so on: whoever faces a multiple of 4 loses, because
// whatever they take, the other takes 4 minus that and hands a multiple of
// 4 back.

public final class CanWinNim {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static boolean canWin(int n) {
		return n % 4 != 0;
	}

	public static void main(String[] args) {
		System.out.println(canWin(6)); // true
		System.out.println(canWin(4)); // false
	}
}