package com.svetanis.algorithms.recursive.backtracking;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

import java.awt.Point;

// Given a rectangular path in the form of binary matrix,
// find the length of longest possible route from src to dst
// of the matrix by moving to only non-zero adjacent positions
// i.e. route can be formed from positions having their value as 1.
// note there should not be any cycles in the output path

public final class LongestPathInMaze {

  public static int solveMaze(int[][] maze, Point src, Point dst) {
    int n = maze.length;
    int m = maze[0].length;
    int[][] visited = new int[n][m];
    return solveMaze(maze, visited, src.x, src.y, dst, MIN_VALUE, 0);
  }

  private static int solveMaze(int[][] maze, int[][] visited, int x, int y, Point dst, int max, int dist) {

    if (x == dst.x && y == dst.y) {
      return max(max, dist);
    }

    visited[x][y] = 1;

    // go down
    if (valid(maze, visited, x + 1, y)) {
      max = solveMaze(maze, visited, x + 1, y, dst, max, dist + 1);
    }

    // go up
    if (valid(maze, visited, x - 1, y)) {
      max = solveMaze(maze, visited, x - 1, y, dst, max, dist + 1);
    }

    // go right
    if (valid(maze, visited, x, y + 1)) {
      max = solveMaze(maze, visited, x, y + 1, dst, max, dist + 1);
    }

    // go left
    if (valid(maze, visited, x, y - 1)) {
      max = solveMaze(maze, visited, x, y - 1, dst, max, dist + 1);
    }

    visited[x][y] = 0; // backtrack

    return max;
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
    System.out.println(solveMaze(maze, src, dst));
  }
}
