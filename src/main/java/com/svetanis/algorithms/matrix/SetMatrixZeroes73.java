package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 73. Set Matrix Zeroes
//
// Input: an n x m matrix of ints. Wherever a cell holds 0, set its whole row and its whole column
// to 0, in place; returns nothing. On LeetCode: 1 <= m, n <= 200,
// -2^31 <= matrix[i][j] <= 2^31 - 1 -- every int is a legal value, so no value can serve as a mark.
//
// The one idea: the answer needs one yes/no per row (clear it?) and one per column. Instead of two
// new arrays, they are kept in the matrix itself: grid[r][0] == 0 means "clear row r" and
// grid[0][c] == 0 means "clear column c". Writing that 0 never loses data: the cell lies in row r
// (or column c), which is being cleared anyway. Row 0 and column 0 are the storage, so their own
// answer is read first, into two booleans, before any mark lands on them. Then the inner cells are
// cleared from the marks, and row 0 and column 0 last, from the booleans -- clearing them earlier
// would wipe marks still to be read.
//
// Time: O(n * m) -- a scan of row 0 and column 0, then two passes over the inner cells.
// Space: O(1) -- two booleans; the marks live in row 0 and column 0.
//
// Siblings: SetMatrixZeroesBooleanArrays keeps the marks in two boolean arrays, O(n + m), and is
// the easier one to explain first. SetMatrixZeroes marks cells with Integer.MAX_VALUE, which
// LeetCode allows as input, so it is correct only where that value cannot appear.

public final class SetMatrixZeroes73 {

  public static void setZeros(int[][] grid) {
    boolean firstRow = firstRowHasZero(grid); // READ FIRST: row 0's own answer, before marks land
    boolean firstCol = firstColHasZero(grid); // READ FIRST: column 0's own answer
    doMarkers(grid);
    setZeros(grid, firstRow, firstCol);
  }

  private static void setZeros(int[][] grid, boolean firstRow, boolean firstCol) {
    int n = grid.length;
    int m = grid[0].length;
    for (int r = 1; r < n; r++) { // CLEAR: inner cells, from the marks
      for (int c = 1; c < m; c++) {
        if (grid[r][0] == 0 || grid[0][c] == 0) {
          grid[r][c] = 0;
        }
      }
    }
    if (firstRow) { // CLEAR: row 0 last -- its cells were the column marks
      for (int c = 0; c < m; c++) {
        grid[0][c] = 0;
      }
    }
    if (firstCol) { // CLEAR: column 0 last -- its cells were the row marks
      for (int r = 0; r < n; r++) {
        grid[r][0] = 0;
      }
    }
  }

  private static void doMarkers(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    for (int r = 1; r < n; r++) { // WALK: inner cells only
      for (int c = 1; c < m; c++) {
        if (grid[r][c] == 0) {
          grid[r][0] = 0; // MARK: clear row r
          grid[0][c] = 0; // MARK: clear column c
        }
      }
    }
  }

  private static boolean firstColHasZero(int[][] grid) {
    int n = grid.length;
    boolean firstCol = false;
    for (int r = 0; r < n; r++) { // SCAN: column 0 for a zero
      if (grid[r][0] == 0) {
        firstCol = true;
        break;
      }
    }
    return firstCol;
  }

  private static boolean firstRowHasZero(int[][] grid) {
    int m = grid[0].length;
    boolean firstRow = false;
    for (int c = 0; c < m; c++) { // SCAN: row 0 for a zero
      if (grid[0][c] == 0) {
        firstRow = true;
        break;
      }
    }
    return firstRow;
  }

  public static void main(String[] args) {
    int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
    setZeros(grid1);
    System.out.println(Arrays.deepToString(grid1)); // [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
    int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
    setZeros(grid2);
    System.out.println(Arrays.deepToString(grid2)); // [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
  }
}