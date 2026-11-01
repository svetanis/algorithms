package com.svetanis.algorithms.backtracking.grid;

// Given a chess board, print all sequences of moves
// of a knight on a chessboard such that the knight
// visits every square only once
//
// Input: n, the side of an n x n board. The knight starts on the top-left square. Prints every
// tour that visits all n * n squares exactly once (a tour need not end where it began). Each tour
// is printed as the board, every square holding the number of the move that reached it (1 for
// the starting square, n * n for the last). No LeetCode problem. A 5 x 5 board has 304 tours from
// the corner and prints 1824 lines; boards of side 2, 3 and 4 have none and print nothing.
//
// The one idea: a tour is a path of n * n squares, each a knight's move from the one before.
// The board is the record of the path so far: a square holds the number of the move that landed
// on it, and 0 means unused. From the current square, try each of the 8 moves that stays on the
// board and lands on an unused square; when move n * n has been placed, print the board. On the
// way out the square is set back to 0, so the next tour may use it.
//
// dx[] and dy[] define next move of Knight

// valid moves:
// (x + 2, y + 1)
// (x + 1, y + 2)
// (x - 1, y + 2)
// (x - 2, y + 1)
// (x - 2, y - 1)
// (x - 1, y - 2)
// (x + 1, y - 2)
// (x + 2, y - 1)

// MOVES: eight knight moves rather than four neighbours. That is the only
// structural difference from the maze walkers in this package.
// REPORTS: every tour that visits all n*n squares once. The board itself is the
// marker: a square holds the move number that landed on it, and 0 means unused.
//
// Time: O(8^(n * n)) -- at most 8 moves from each of n * n squares along a path.
// Space: O(n * n) -- the board, and a recursion n * n deep.
//
// Siblings: none. The KnightTour files in com.svetanis.algorithms.dp.knight count phone numbers
// dialled with knight moves, a different problem.

public final class KnightTour {

  private static final int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
  private static final int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

  public static void knight(int n) {
    int[][] visited = new int[n][n];
    knight(visited, 0, 0, 1);
  }

  private static void knight(int[][] visited, int x, int y, int pos) {
    int n = visited.length;
    visited[x][y] = pos; // MARK: the move number that landed here

    if (pos >= n * n) { // BASE: every square used, print the tour
      print(visited);
      visited[x][y] = 0; // UNDO: backtrack
      return;
    }
    // try all next moves from the current x, y
    for (int k = 0; k < dx.length; k++) {
      int nextX = x + dx[k];
      int nextY = y + dy[k];
      if (isValid(visited, nextX, nextY)) { // TAKE: on the board and unused
        knight(visited, nextX, nextY, pos + 1);
      }
    }
    visited[x][y] = 0; // UNDO: backtrack, the square is free for other tours
  }

  private static boolean isValid(int[][] visited, int x, int y) {
    int n = visited.length;
    return x >= 0 && x < n && y >= 0 && y < n && visited[x][y] == 0;
  }

  // one row per line, every number followed by a space, then a blank line
  private static void print(int[][] matrix) {
    for (int[] row : matrix) {
      for (int value : row) {
        System.out.print(value);
        System.out.print(' '); // each number is followed by a space
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    knight(5); // prints the 304 tours, each as 5 rows and a blank line
  }
}
