package com.svetanis.algorithms.matrix;

// 419. Battleships in a Board
//
// Input: grid -- an m x n board; each cell holds 'X' (part of a ship) or '.' (water). A ship is a
// straight line of 'X' cells, 1 x k or k x 1, and two ships never touch along a side: at least
// one water cell lies between them across the row or the column. Returns the number of ships.
// On LeetCode: 1 <= m, n <= 200, each cell is '.' or 'X', and the board is always valid.
//
// The one idea: one cell holds a piece of ship or water. Every ship has exactly one first cell --
// its top cell if it stands up and down, its left cell if it lies across -- and that cell is the
// only one of the ship with water (or the board's edge) both above it and to its left. Every
// other 'X' of the ship has an 'X' above it or to its left. So counting the 'X' cells with no 'X'
// above and no 'X' to the left counts each ship once, in one pass, without marking the board.
// This rests on ships never touching: two touching ships would look like one bent ship.
//
// Time: O(m * n) -- each cell is read once, with the two neighbours above and to the left.
// Space: O(1) -- no visited marks; the board is not changed.

public final class CountBattleships {

	public static int count(char[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int count = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col] == 'X') {
					boolean one = row == 0 || grid[row - 1][col] == '.'; // ABOVE: edge or water
					boolean two = col == 0 || grid[row][col - 1] == '.'; // LEFT: edge or water
					if (one && two) { // FIRST cell of a ship
						count++; // COUNT the ship once
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		char[][] g1 = { { 'X', '.', '.', 'X' }, { '.', '.', '.', 'X' }, { '.', '.', '.', 'X' } };
		System.out.println(count(g1)); // 2
		char[][] g2 = { { '.' } };
		System.out.println(count(g2)); // 0
	}
}
