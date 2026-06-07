package com.svetanis.algorithms.math;

import com.svetanis.java.base.utils.Print;

// 1033. Moving Stones Until Consecutive

public final class MovingStones {

	public static int[] countMoves(int a, int b, int c) {
		int x = Math.min(a, Math.min(b, c));
		int z = Math.max(a, Math.max(b, c));
		int y = (a + b + c) - x - z;
		if(z - x == 2) {
			return new int[] {0, 0};
		}
		boolean gap1 = y - x < 3;
		boolean gap2 = z - y < 3;
		int minMoves = (gap1 || gap2) ? 1 : 2;
		int maxMoves = z - x - 2;;
		return new int[] {minMoves, maxMoves};
	}

	public static void main(String[] args) {
		Print.print(countMoves(1, 2, 5)); // 1 2
		Print.print(countMoves(4, 3, 2)); // 0 0
		Print.print(countMoves(3, 5, 1)); // 1 2
	}
}