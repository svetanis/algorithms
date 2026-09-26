package com.svetanis.algorithms.math;

import com.svetanis.java.base.utils.Print;

// 1033. Moving Stones Until Consecutive

// Sort the stones: x < y < z.
// Most moves: every move shrinks the span z - x by at least 1, and can
// shrink it by exactly 1, so the most is the empty spots between x and z,
// z - x - 2.
// Fewest moves: 0 if they already sit together. 1 if one gap is 1 or 2
// wide -- the far stone can go right next to the pair, or into the single
// empty spot. Otherwise 2: bring each end stone in next to y.

public final class MovingStones {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int[] countMoves(int a, int b, int c) {
		int x = Math.min(a, Math.min(b, c));
		int z = Math.max(a, Math.max(b, c));
		int y = (a + b + c) - x - z;
		if (z - x == 2) {
			return new int[] {0, 0};
		}
		boolean gap1 = y - x < 3;
		boolean gap2 = z - y < 3;
		int minMoves = (gap1 || gap2) ? 1 : 2;
		int maxMoves = z - x - 2;
		return new int[] {minMoves, maxMoves};
	}

	public static void main(String[] args) {
		Print.print(countMoves(1, 2, 5)); // 1 2
		Print.print(countMoves(4, 3, 2)); // 0 0
		Print.print(countMoves(3, 5, 1)); // 1 2
	}
}