package com.svetanis.algorithms.recursive.backtracking;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.awt.Point;

// Given a maze in the form of the binary rectangular matrix,
// find lenght of the shortest path in maze from given src to dst.
// The path can only be constructed out of cells having value 1
// and at any given moment, we can only move one step in one of the 4 directions

public final class ShortestPathInMaze {

  public static int solveMaze(int[][] maze, Point src, Point dst) {
    int n = maze.length;
    int m = maze[0].length;
    int[][] visited = new int[n][m];
    return solveMaze(maze, visited, src.x, src.y, dst, MAX_VALUE, 0);
  }

  private static int solveMaze(int[][] maze, int[][] visited, int x, int y, Point dst, int min, int dist) {

    if (x == dst.x && y == dst.y) {
      return min(min, dist);
    }

    visited[x][y] = 1;

    // go down
    if (valid(maze, visited, x + 1, y)) {
      min = solveMaze(maze, visited, x + 1, y, dst, min, dist + 1);
    }

    // go up
    if (valid(maze, visited, x - 1, y)) {
      min = solveMaze(maze, visited, x - 1, y, dst, min, dist + 1);
    }

    // go right
    if (valid(maze, visited, x, y + 1)) {
      min = solveMaze(maze, visited, x, y + 1, dst, min, dist + 1);
    }

    // go left
    if (valid(maze, visited, x, y - 1)) {
      min = solveMaze(maze, visited, x, y - 1, dst, min, dist + 1);
    }

    visited[x][y] = 0; // backtrack

    return min;
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
    System.out.println(solveMaze(maze, src, dst));
  }
}
