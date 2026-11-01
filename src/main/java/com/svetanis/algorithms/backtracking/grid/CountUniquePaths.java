package com.svetanis.algorithms.backtracking.grid;

// Find the total number of unique paths which the robot
// can take in a given maze to reach the dst from given src
//
// Input: a maze of n rows and m columns holding 1 (open) and 0 (wall). The robot starts at the
// top-left cell, which must be open, and moves one step up, down, left or right onto open cells,
// never onto a cell it has already used on the current path. Returns how many different paths
// lead to the bottom-right cell. No LeetCode problem (LC 62 allows only down and right; LC 980
// must also visit every open cell). The number of paths grows exponentially, so mazes are small.
//
// The one idea: a path is a list of cells, so the same cell may be on many different paths. The
// walk extends the current path one cell at a time, marks the cell it stands on as used, and
// unmarks it on the way out so the next path may use it again. Each arrival at the last cell is
// one more path.
//
// MOVES: all four neighbours, so a cycle IS possible and the visited marker is
// what makes the search finite -- marked on the way in, cleared on the way out.
// REPORTS: how many paths. Note it threads `count` DOWN as a parameter and
// returns it again, rather than each call returning its own count. That works,
// but it means the return value is "how many found anywhere so far", not "how
// many from here" -- so this one cannot be memoized on (x, y).
//
// Time: O(4 * 3^(n * m - 1)) -- every path is a walk that never revisits a cell; a cell has at
// most 4 ways on from the start and 3 after that.
// Space: O(n * m) -- the visited marks and a recursion at most n * m deep.

public final class CountUniquePaths {

  public static int solveMaze(int[][] maze) {
    int n = maze.length;
    int m = maze[0].length;
    boolean[][] visited = new boolean[n][m];
    return solveMaze(maze, visited, 0, 0, 0);
  }

  private static int solveMaze(int[][] maze, boolean[][] visited, int x, int y, int count) {
    int n = maze.length;
    int m = maze[0].length;

    if (x == n - 1 && y == m - 1) { // BASE: the last row and the last column
      count++;
      return count;
    }

    visited[x][y] = true; // MARK: used on the current path

    // go down (x, y) --> (x + 1, y)
    if (valid(maze, visited, x + 1, y)) {
      count = solveMaze(maze, visited, x + 1, y, count); // WALK: down
    }

    // go up (x, y) --> (x - 1, y)
    if (valid(maze, visited, x - 1, y)) {
      count = solveMaze(maze, visited, x - 1, y, count); // WALK: up
    }

    // go right (x, y) --> (x, y + 1)
    if (valid(maze, visited, x, y + 1)) {
      count = solveMaze(maze, visited, x, y + 1, count); // WALK: right
    }

    // go left (x, y) --> (x, y - 1)
    if (valid(maze, visited, x, y - 1)) {
      count = solveMaze(maze, visited, x, y - 1, count); // WALK: left
    }

    visited[x][y] = false; // UNDO: backtrack, the cell is free for other paths

    return count; // RETURN: paths found so far
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
    System.out.println(solveMaze(maze)); // 4
  }
}
