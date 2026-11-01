package com.svetanis.algorithms.backtracking.grid;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.awt.Point;

// Given a maze in the form of the binary rectangular matrix,
// find length of the shortest path in maze from given src to dst.
// The path can only be constructed out of cells having value 1
// and at any given moment, we can only move one step in one of the 4 directions
//
// Input: a maze of 1 (open) and 0 (wall), and a source and a destination cell. The source must
// be open. A move is one step up, down, left or right onto an open cell that is not already on
// the route. Returns the number of steps of the shortest such route, or Integer.MAX_VALUE when
// the destination cannot be reached. No LeetCode problem (LC 1091 is the 8-direction variant on an
// n x n grid, where 0 is open); the walk is exponential, so mazes are small.
//
// The one idea: a route is a list of cells that never repeats one, and the same cell can be on
// many different routes. The walk extends the current route one step at a time, marks the cell it
// stands on, and unmarks it on the way out so another route may use it. Each time it stands on
// the destination, the length of that route is one more candidate for the shortest.
//
// MOVES: all four neighbours, so the visited marker is required.
// REPORTS: the shortest route, combined with `min`. `min` and `dist` are
// threaded DOWN as parameters, so the return value is "the shortest seen
// anywhere so far" rather than "the shortest from here" -- which is why this
// file has no memoized twin. A cache keyed on (x, y) would be wrong.
// (BFS answers this in one pass without a marker-and-undo at all; this file is
// here as the backtracking version of the question.)
//
// Time: O(4 * 3^(n * m - 1)) -- every route is a walk that never revisits a cell.
// Space: O(n * m) -- the visited marks and a recursion at most n * m deep.
//
// Siblings: LongestPathInMaze is the same walk keeping the maximum.

public final class ShortestPathInMaze {

  public static int solveMaze(int[][] maze, Point src, Point dst) {
    int n = maze.length;
    int m = maze[0].length;
    int[][] visited = new int[n][m];
    return solveMaze(maze, visited, src.x, src.y, dst, MAX_VALUE, 0);
  }

  private static int solveMaze(int[][] maze, int[][] visited, int x, int y, Point dst, int min, int dist) {

    if (x == dst.x && y == dst.y) { // BASE: a complete route, its length is a candidate
      return min(min, dist);
    }

    visited[x][y] = 1; // MARK: on the current route

    // go down
    if (valid(maze, visited, x + 1, y)) {
      min = solveMaze(maze, visited, x + 1, y, dst, min, dist + 1); // WALK: down
    }

    // go up
    if (valid(maze, visited, x - 1, y)) {
      min = solveMaze(maze, visited, x - 1, y, dst, min, dist + 1); // WALK: up
    }

    // go right
    if (valid(maze, visited, x, y + 1)) {
      min = solveMaze(maze, visited, x, y + 1, dst, min, dist + 1); // WALK: right
    }

    // go left
    if (valid(maze, visited, x, y - 1)) {
      min = solveMaze(maze, visited, x, y - 1, dst, min, dist + 1); // WALK: left
    }

    visited[x][y] = 0; // UNDO: backtrack

    return min; // RETURN: the shortest seen so far
  }

  private static boolean valid(int[][] maze, int[][] visited, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;
    return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1 && visited[x][y] == 0;
  }

  public static void main(String[] args) {
    int[][] maze = { //
        { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 }, //
        { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 }, //
        { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 }, //
        { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 }, //
        { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 }, //
        { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 }, //
        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 }, //
        { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 }, //
        { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 }, //
        { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 } //
    };//
    Point src = new Point(0, 0);
    Point dst = new Point(7, 5);
    System.out.println(solveMaze(maze, src, dst)); // 12
  }
}
