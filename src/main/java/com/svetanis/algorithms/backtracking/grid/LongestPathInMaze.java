package com.svetanis.algorithms.backtracking.grid;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

import java.awt.Point;

// Given a rectangular path in the form of binary matrix,
// find the length of longest possible route from src to dst
// of the matrix by moving to only non-zero adjacent positions
// i.e. route can be formed from positions having their value as 1.
// note there should not be any cycles in the output path
//
// Input: a maze of 1 (open) and 0 (wall), and a source and a destination cell. The source must
// be open. A move is one step up, down, left or right onto an open cell that is not already on
// the route. Returns the number of steps of the longest such route, or Integer.MIN_VALUE when
// the destination cannot be reached. No LeetCode problem; the walk is exponential, so mazes are
// small.
//
// The one idea: a route is a list of cells that never repeats one, and the same cell can be on
// many different routes. The walk extends the current route one step at a time, marks the cell it
// stands on, and unmarks it on the way out so another route may use it. Each time it stands on
// the destination, the length of that route is one more candidate for the longest.
//
// MOVES: all four neighbours, so the visited marker is required.
// REPORTS: the longest route, combined with `max`. Both `max` and `dist` are
// threaded DOWN as parameters, so the return value is "the longest seen anywhere
// so far" rather than "the longest from here" -- which is why there is no
// memoized twin of this file. A cache keyed on (x, y) would be wrong.
//
// Time: O(4 * 3^(n * m - 1)) -- every route is a walk that never revisits a cell.
// Space: O(n * m) -- the visited marks and a recursion at most n * m deep.
//
// Siblings: ShortestPathInMaze is the same walk keeping the minimum.

public final class LongestPathInMaze {

  public static int solveMaze(int[][] maze, Point src, Point dst) {
    int n = maze.length;
    int m = maze[0].length;
    int[][] visited = new int[n][m];
    return solveMaze(maze, visited, src.x, src.y, dst, MIN_VALUE, 0);
  }

  private static int solveMaze(int[][] maze, int[][] visited, int x, int y, Point dst, int max, int dist) {

    if (x == dst.x && y == dst.y) { // BASE: a complete route, its length is a candidate
      return max(max, dist);
    }

    visited[x][y] = 1; // MARK: on the current route

    // go down
    if (valid(maze, visited, x + 1, y)) {
      max = solveMaze(maze, visited, x + 1, y, dst, max, dist + 1); // WALK: down
    }

    // go up
    if (valid(maze, visited, x - 1, y)) {
      max = solveMaze(maze, visited, x - 1, y, dst, max, dist + 1); // WALK: up
    }

    // go right
    if (valid(maze, visited, x, y + 1)) {
      max = solveMaze(maze, visited, x, y + 1, dst, max, dist + 1); // WALK: right
    }

    // go left
    if (valid(maze, visited, x, y - 1)) {
      max = solveMaze(maze, visited, x, y - 1, dst, max, dist + 1); // WALK: left
    }

    visited[x][y] = 0; // UNDO: backtrack

    return max; // RETURN: the longest seen so far
  }

  private static boolean valid(int[][] maze, int[][] visited, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;
    return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1 && visited[x][y] == 0;
  }

  public static void main(String[] args) {
    int[][] maze = { //
        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, //
        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, //
        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, //
        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 }, //
        { 1, 0, 0, 0, 1, 1, 1, 1, 1, 1 }, //
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, //
        { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 }, //
        { 1, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, //
        { 1, 1, 0, 0, 1, 0, 0, 0, 0, 1 }, //
        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 } //
    };//
    Point src = new Point(0, 0);
    Point dst = new Point(5, 7);
    System.out.println(solveMaze(maze, src, dst)); // 22
  }
}
