package com.svetanis.algorithms.backtracking.queens;

import static java.util.Arrays.fill;

// 51. N-Queens (prints each board as rows of '-' and 'Q')
//
// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal
//
// Input: n, the side of the board (1 <= n <= 9 on LeetCode). Prints every solution as an n x n
// board, 'Q' for a queen and '-' for an empty square, each followed by a blank line; nothing is
// printed when n is 2 or 3. The returned matrix is the board the search worked on, and by the
// time it returns every queen has been taken off again, so it is all '-'.
//
// The one idea: two queens cannot share a row, so each row holds exactly one queen. The search
// fills the rows from the top: for row r it tries every column and keeps the ones where the new
// queen has no queen above it in the same column or on either upward diagonal (isSafe), writes a
// 'Q', moves on to row r + 1, and writes '-' back on the way out. Row n reached means n queens
// are placed, so the board is printed.
//
// Time: O(n! * n^2) -- at most n! / (n - k)! placements reach row k (about e * n! in all), and
// each tries n columns at O(n) per isSafe check.
// Space: O(n^2) -- the board; the recursion is n deep.
//
// Siblings: NQueen returns the solutions as lists of columns; NQueensBacktracking is the LC 51
// answer with boolean arrays for the columns and diagonals.

public final class NQueenPrint {

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
    if (row == n) { // BASE: every row has a queen, print the board
      print(matrix);
      System.out.println();
      return;
    }
    for (int col = 0; col < n; col++) {
      if (isSafe(matrix, row, col)) { // TAKE: nothing above attacks (row, col)
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

    for (int row = 0; row < r; row++) { // COLUMN: any queen above in column c
      if (matrix[row][c] == 'Q') {
        return false;
      }
    }

    for (int row = r, col = c; row >= 0 && col >= 0; row--, col--) { // DIAGONAL: up and left
      if (matrix[row][col] == 'Q') {
        return false;
      }
    }

    for (int row = r, col = c; row >= 0 && col < matrix.length; row--, col++) { // DIAGONAL: up and right
      if (matrix[row][col] == 'Q') {
        return false;
      }
    }

    return true;
  }

  // one row per line, every square followed by a space, then a blank line
  private static void print(char[][] matrix) {
    for (char[] row : matrix) {
      for (char square : row) {
        System.out.print(square);
        System.out.print(' '); // each square is followed by a space
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    queen(4); // prints the 2 boards
  }
}
