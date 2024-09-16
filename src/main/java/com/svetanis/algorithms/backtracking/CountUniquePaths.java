package com.svetanis.algorithms.backtracking;

// Find the total number of unique paths which the robot
// can take in a given maze to reach the dst from given src

public final class CountUniquePaths {

  public static int solveMaze(int[][] maze) {
    int n = maze.length;
    int m = maze[0].length;
    boolean[][] visited = new boolean[n][m];
    return solveMaze(maze, visited, 0, 0, 0);
  }

  private static int solveMaze(int[][] maze, boolean[][] visited, int x, int y, int count) {
    int n = maze.length;

    if (x == n - 1 && y == n - 1) {
      count++;
      return count;
    }

    visited[x][y] = true;

    // go down (x, y) --> (x + 1, y)
    if (valid(maze, visited, x + 1, y)) {
      count = solveMaze(maze, visited, x + 1, y, count);
    }

    // go up (x, y) --> (x - 1, y)
    if (valid(maze, visited, x - 1, y)) {
      count = solveMaze(maze, visited, x - 1, y, count);
    }

    // go right (x, y) --> (x, y + 1)
    if (valid(maze, visited, x, y + 1)) {
      count = solveMaze(maze, visited, x, y + 1, count);
    }

    // go left (x, y) --> (x, y - 1)
    if (valid(maze, visited, x, y - 1)) {
      count = solveMaze(maze, visited, x, y - 1, count);
    }

    visited[x][y] = false; // backtrack

    return count;
  }

  private static boolean valid(int[][] maze, boolean[][] visited, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;
    return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1 && !visited[x][y];
  }

  public static void main(String[] args) {
    int[][] maze = { //
        { 1, 1, 1, 1 }, //
        { 1, 1, 0, 1 }, //
        { 0, 1, 0, 1 }, //
        { 1, 1, 1, 1 } //
    };//
    System.out.println(solveMaze(maze));
  }
}
