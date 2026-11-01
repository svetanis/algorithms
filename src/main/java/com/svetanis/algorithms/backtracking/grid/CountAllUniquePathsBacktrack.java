package com.svetanis.algorithms.backtracking.grid;

// 62. Unique Paths
//
// Input: a grid of n rows and m columns (1 <= n, m <= 100 on LeetCode); only its shape is read,
// the values in it are ignored. A robot starts at the top-left cell and may move only down or
// right. Returns how many different paths lead to the bottom-right cell.
//
// The one idea: one call answers "how many paths lead from cell (row, col) to the last cell?".
// A cell has two ways on, down or right, and every path leaves it by exactly one of them, so its
// answer is the sum of the answers of those two cells. The last cell has one path: stand still.
// A step off the grid has none.
//
// MOVES: right and down only, so a coordinate never decreases and a cycle cannot be built -- which
// is why there is no visited marker anywhere in here.
// REPORTS: how many paths, combined with `+`: each call returns its own count and the caller
// adds them. Arriving at the last cell is worth 1.
//
// This is the plain recursion with no cache: the same cell is reached again by every path to it,
// so it is only fit for small grids (LeetCode's 100 x 100 needs the cached or tabulated version).
//
// Time: O(2^(n + m)) -- each call makes two calls and the depth is n + m - 1; the answer itself
// is C(n + m - 2, n - 1), and every path is walked one cell at a time.
// Space: O(n + m) -- the recursion depth.
//
// Siblings: the other versions of 62 are in dp/grid/alluniquepaths. RobotOnGridRecursive is this
// recursion on the grid's two sizes instead of a grid, counting down from (m, n) to an edge row or
// column; RobotOnGridTopDown walks every path with a visited marker; RobotOnGridMemoization caches
// the recursion; RobotOnGridBottomUp fills it as a table and RobotOnGridBottomUpOptimized keeps one
// row of that table.

public final class CountAllUniquePathsBacktrack {

  public static int count(int[][] grid) {
    return count(grid, 0, 0);
  }

  public static int count(int[][] grid, int row, int col) {

    int n = grid.length;
    int m = grid[0].length;

    // BASE: destination cell
    if (row == n - 1 && col == m - 1) {
      return 1;
    }

    // BASE: illegal cell, off the grid
    if (row > n - 1 || col > m - 1) {
      return 0;
    }

    int right = count(grid, row + 1, col); // WALK: row + 1 is a step down
    int down = count(grid, row, col + 1); // WALK: col + 1 is a step right
    return right + down; // RETURN: every path leaves by one of the two
  }

  public static void main(String[] args) {
    int[][] grid = { //
        { 1, 2, 3 }, //
        { 4, 5, 6 }, //
        { 7, 8, 9 }  //
    };
    System.out.println(count(grid)); // 6
  }
}
