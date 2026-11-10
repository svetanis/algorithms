package com.svetanis.algorithms.matrix.spiral;

import java.util.ArrayList;
import java.util.List;

// 54. Spiral Matrix
//
// Input: grid -- an n x m grid of numbers. Returns every number once, in spiral order: along the
// top row to the right, down the right column, back along the bottom row, up the left column,
// then the same again one ring further in. On LeetCode: 1 <= n, m <= 10,
// -100 <= grid[i][j] <= 100.
//
// The one idea: walk the grid like a robot that only knows straight ahead and turn right. One
// cell of visited[][] says whether that cell's number is already in the answer. The robot starts
// at the top-left facing right and keeps going straight; when the next cell is off the grid or
// already visited, it turns right (right -> down -> left -> up -> right). That turn is exactly
// where the spiral bends, and it takes n * m steps, one per number.
//
// dx[] and dy[] hold the four headings in turning order: dx is the change of row, dy the change
// of column.
//
// Time: O(n * m) -- one step per cell, each O(1).
// Space: O(n * m) auxiliary -- the boolean[n][m] visited array; the answer list besides.
//
// Siblings: SpiralMatrixSpaceEfficient (54) reads the same order with four boundaries instead of
// visited[][], O(1) auxiliary. SpiralMatrixII (59) writes 1 .. n^2 in this order.

public final class SpiralMatrix {

  private static final int[] dx = { 0, 1, 0, -1 }; // right, down, left, up: change of row
  private static final int[] dy = { 1, 0, -1, 0 }; // the same four: change of column

  public static List<Integer> spiral(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int row = 0, col = 0;
    int direction = 0; // HEADING: 0 = right, to start
    boolean[][] visited = new boolean[n][m];
    List<Integer> list = new ArrayList<>();
    for (int count = n * m; count > 0; count--) { // COUNT: one step per number
      list.add(grid[row][col]); // TAKE this cell's number
      visited[row][col] = true;
      int x = row + dx[direction]; // LOOK one cell ahead
      int y = col + dy[direction];
      if (!valid(x, y, n, m) || visited[x][y]) { // TURN right: off the grid or already taken
        direction = (direction + 1) % 4;
        x = row + dx[direction];
        y = col + dy[direction];
      }
      row = x; // STEP; after the last number it may step off the grid, but the loop ends
      col = y;
    }
    return list;
  }

  private static boolean valid(int x, int y, int n, int m) {
    boolean row = x < n && x >= 0;
    boolean col = y < m && y >= 0;
    return row && col;
  }

  public static void main(String[] args) {
    int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    System.out.println(spiral(g1)); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
    int[][] g2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
    System.out.println(spiral(g2)); // [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
  }
}