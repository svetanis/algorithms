package com.svetanis.algorithms.recursive.backtracking;

import static com.svetanis.java.base.utils.Print.print;

// Given a chess board, print all sequences of moves
// of a knight on a chessboard such that the knight
// visits every square only once

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

public final class KnightTour {

  private static final int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
  private static final int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

  public static void knight(int n) {
    int[][] visited = new int[n][n];
    knight(visited, 0, 0, 1);
  }

  private static void knight(int[][] visited, int x, int y, int pos) {
    int n = visited.length;
    visited[x][y] = pos;

    if (pos >= n * n) {
      print(visited);
      visited[x][y] = 0; // backtrack
      return;
    }
    // try all next moves from the current x, y
    for (int k = 0; k < dx.length; k++) {
      int nextX = x + dx[k];
      int nextY = y + dy[k];
      if (isValid(visited, nextX, nextY)) {
        knight(visited, nextX, nextY, pos + 1);
      }
    }
    visited[x][y] = 0; // backtrack
  }

  private static boolean isValid(int[][] visited, int x, int y) {
    int n = visited.length;
    return x >= 0 && x < n && y >= 0 && y < n && visited[x][y] == 0;
  }

  public static void main(String[] args) {
    knight(5);
  }
}
