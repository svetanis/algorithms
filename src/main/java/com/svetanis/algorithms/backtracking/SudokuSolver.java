package com.svetanis.algorithms.backtracking;

import static com.svetanis.java.base.utils.IntWrapper.newIntWrapper;
import static com.svetanis.java.base.utils.Print.print;

import com.svetanis.java.base.utils.IntWrapper;

public final class SudokuSolver {

  private final static int N = 9;

  public boolean sudoku(int[][] grid) {

    // row and col passed by reference
    IntWrapper row = newIntWrapper();
    IntWrapper col = newIntWrapper();

    // if there is no unassigned location, we are done
    if (!isEmpty(grid, row, col)) {
      return true;
    }

    // consider digits from 1 to 9
    for (int num = 1; num <= N; ++num) {
      // if looks promising
      if (isSafe(grid, row, col, num)) {
        // make tentative assignment
        grid[row.value][col.value] = num;

        // return if success
        if (sudoku(grid)) {
          return true;
        }
        // failure, backtrack
        grid[row.value][col.value] = 0;
      }
    }
    return false;
  }

  private boolean isEmpty(int[][] grid, IntWrapper row, IntWrapper col) {
    for (row.value = 0; row.value < N; ++row.value) {
      for (col.value = 0; col.value < N; ++col.value) {
        if (grid[row.value][col.value] == 0) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isSafe(int[][] grid, IntWrapper row, IntWrapper col, int num) {
    boolean one = !usedInRow(grid, row, num);
    boolean two = !usedInCol(grid, col, num);
    int r = row.value - row.value % 3;
    int c = col.value - col.value % 3;
    boolean three = !usedInBox(grid, r, c, num);
    return one && two && three;
  }

  private boolean usedInRow(int[][] grid, IntWrapper row, int num) {
    for (int col = 0; col < N; ++col) {
      if (grid[row.value][col] == num) {
        return true;
      }
    }
    return false;
  }

  private boolean usedInCol(int[][] grid, IntWrapper col, int num) {
    for (int row = 0; row < N; ++row) {
      if (grid[row][col.value] == num) {
        return true;
      }
    }
    return false;
  }

  private boolean usedInBox(int[][] grid, int row, int col, int num) {
    for (int r = 0; r < 3; ++r) {
      for (int c = 0; c < 3; ++c) {
        if (grid[r + row][c + col] == num) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // 0 means unassigned cells
    int[][] grid = { //
        { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, //
        { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, //
        { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, //
        { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, //
        { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, //
        { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, //
        { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, //
        { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, //
        { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };//

    SudokuSolver s = new SudokuSolver();
    if (s.sudoku(grid)) {
      print(grid);
    } else {
      System.out.println("no solution");
    }
  }
}
