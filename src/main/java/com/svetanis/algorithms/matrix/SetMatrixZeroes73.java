package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 73. Set Matrix Zeroes

public final class SetMatrixZeroes73 {
  // Time Complexity: O(n * m)
  // Space Complexity: O(1)

  public static void setZeros(int[][] grid) {
    boolean firstRow = firstRowHasZero(grid);
    boolean firstCol = firstColHasZero(grid);
    doMarkers(grid);
    setZeros(grid, firstRow, firstCol);
  }

  private static void setZeros(int[][] grid, boolean firstRow, boolean firstCol) {
    int n = grid.length;
    int m = grid[0].length;
    for (int r = 1; r < n; r++) {
      for (int c = 1; c < m; c++) {
        if (grid[r][0] == 0 || grid[0][c] == 0) {
          grid[r][c] = 0;
        }
      }
    }
    if (firstRow) {
      for (int c = 0; c < m; c++) {
        grid[0][c] = 0;
      }
    }
    if (firstCol) {
      for (int r = 0; r < n; r++) {
        grid[r][0] = 0;
      }
    }
  }

  private static void doMarkers(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    for (int r = 1; r < n; r++) {
      for (int c = 1; c < m; c++) {
        if (grid[r][c] == 0) {
          grid[r][0] = 0;
          grid[0][c] = 0;
        }
      }
    }
  }

  private static boolean firstColHasZero(int[][] grid) {
    int n = grid.length;
    boolean firstCol = false;
    // check if first col has any zeros
    for (int r = 0; r < n; r++) {
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
    // check if first row has any zeros
    for (int c = 0; c < m; c++) {
      if (grid[0][c] == 0) {
        firstRow = true;
        break;
      }
    }
    return firstRow;
  }

  public static void main(String[] args) {
    int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
    setZeros(grid1); // [1,0,1],[0,0,0][1,0,1]
    Print.print(grid1);
    int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
    setZeros(grid2); // [0,0,0,0],[0,4,5,0],[0,3,1,0]
    Print.print(grid2);
  }
}