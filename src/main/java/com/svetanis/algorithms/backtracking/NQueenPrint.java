package com.svetanis.algorithms.backtracking;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal

public final class NQueenPrint {

  // Time Complexity: O(n^n)

  public static char[][] queen(int n) {
    char[][] matrix = new char[n][n];
    for (int i = 0; i < n; i++) {
      fill(matrix[i], '-');
    }
    queen(matrix, 0);
    return matrix;
  }

  private static void queen(char[][] matrix, int row) {
    int n = matrix.length;
    if (row == n) {
      print(matrix);
      System.out.println();
      return;
    }
    for (int col = 0; col < n; col++) {
      if (isSafe(matrix, row, col)) {
        matrix[row][col] = 'Q'; // place queen
        queen(matrix, row + 1); // recur for next row
        matrix[row][col] = '-'; // backtrack
      }
    }
  }

  // check if (r, c) is a valid spot for a queen by checking
  // if there is a queen in the same column or diagonal
  // no need to check it for queens in the same row
  private static boolean isSafe(char[][] matrix, int r, int c) {

    for (int row = 0; row < r; row++) {
      if (matrix[row][c] == 'Q') {
        return false;
      }
    }

    for (int row = r, col = c; row >= 0 && col >= 0; row--, col--) {
      if (matrix[row][col] == 'Q') {
        return false;
      }
    }

    for (int row = r, col = c; row >= 0 && col < matrix.length; row--, col++) {
      if (matrix[row][col] == 'Q') {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    queen(4);
  }
}
