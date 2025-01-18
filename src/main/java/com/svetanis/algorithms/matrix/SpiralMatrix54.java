package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.List;

// 54. Spiral Matrix

public final class SpiralMatrix54 {
  // Time Complexity: O(n * m)
  // Space Complexity: O(n * m)

  private static final int[] dx = { 0, 1, 0, -1 };
  private static final int[] dy = { 1, 0, -1, 0 };

  public static List<Integer> spiral(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int row = 0, col = 0;
    int direction = 0;
    boolean[][] visited = new boolean[n][m];
    List<Integer> list = new ArrayList<>();
    for (int count = n * m; count > 0; count--) {
      list.add(grid[row][col]);
      visited[row][col] = true;
      int x = row + dx[direction];
      int y = col + dy[direction];
      if (!valid(x, y, n, m) || visited[x][y]) {
        direction = (direction + 1) % 4;
        x = row + dx[direction];
        y = col + dy[direction];
      }
      row = x;
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
    System.out.println(spiral(g1)); // 1,2,3,6,9,8,7,4,5
    int[][] g2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
    System.out.println(spiral(g2)); // 1,2,3,4,8,12,11,10,9,5,6,7
  }
}