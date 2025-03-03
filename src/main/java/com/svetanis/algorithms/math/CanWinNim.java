package com.svetanis.algorithms.math;

// 292. Nim Game

public final class CanWinNim {

	public static boolean canWin(int n) {
		return n % 4 != 0;
	}

	public static void main(String[] args) {
		System.out.println(canWin(6));
	}
}