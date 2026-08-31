package com.svetanis.algorithms.backtracking.grid;

// MOVES: right and down only, so a coordinate never decreases and a cycle
// cannot be built -- which is why there is no visited marker anywhere in here.
// REPORTS: how many paths, combined with `+`: each call returns its own count
// and the caller adds them. Arriving at the last cell is worth 1.

public final class CountAllUniquePathsBacktrack {

  public static int count(int[][] grid) {
    return count(grid, 0, 0);
  }

  public static int count(int[][] grid, int row, int col) {

    int n = grid.length;
    int m = grid[0].length;

    // destination cell
    if (row == n - 1 && col == m - 1) {
      return 1;
    }

    // illegal cell
    if (row > n - 1 || col > m - 1) {
      return 0;
    }

    int right = count(grid, row + 1, col);
    int down = count(grid, row, col + 1);
    return right + down;
  }

  public static void main(String[] args) {
    int[][] grid = { //
        { 1, 2, 3 }, //
        { 4, 5, 6 }, //
        { 7, 8, 9 }  //
    };
    System.out.println(count(grid));
  }
}
