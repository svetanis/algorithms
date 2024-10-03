package com.svetanis.algorithms.twopointers;

public final class Celebrity {

	private final int n;
	private final int[][] grid;

	public Celebrity(int[][] grid) {
		this.grid = grid;
		this.n = grid.length;
	}

	public int size() {
		return n;
	}

	public boolean knows(int a, int b) {
		return grid[a][b] == 1;
	}
}
