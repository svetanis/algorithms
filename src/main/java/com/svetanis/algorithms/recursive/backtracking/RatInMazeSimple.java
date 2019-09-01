package com.svetanis.algorithms.recursive.backtracking;

import static com.svetanis.java.base.utils.Print.print;

// A Maze is given as N*N binary matrix of blocks 
// where source block is the upper left most block i.e., maze[0][0] 
// and destination block is lower rightmost block i.e., maze[N-1][N-1]. 
// A rat starts from source and has to reach the destination. 
// The rat can move only in two directions: forward and down.
// In the maze matrix, 0 means the block is a dead end and 1 means 
// the block can be used in the path from source to destination. 

public final class RatInMazeSimple {

  public static void solveMaze(int[][] maze) {
    int n = maze.length;
    int m = maze[0].length;
    int[][] visited = new int[n][m];
    if (!solveMaze(maze, visited, 0, 0)) {
      System.out.println("no solution exists");
    }
    print(visited);
  }

  private static boolean solveMaze(int[][] maze, int[][] visited, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;

    // if (x, y is goal) return true
    if (x == n - 1 && y == m - 1) {
      visited[x][y] = 1;
      return true;
    }

    // check if maze[x][y] is valid
    if (valid(maze, x, y)) {
      // mark x, y as part
      // of solution path
      visited[x][y] = 1;

      // move forward in x direction
      if (solveMaze(maze, visited, x + 1, y)) {
        return true;
      }

      // if x moving in x direction
      // doesn't give solution then
      // move down in y direction
      if (solveMaze(maze, visited, x, y + 1)) {
        return true;
      }

      // if none of the above
      // movements work then BACKTRACK:
      // unmark x, y as part of solution path
      visited[x][y] = 0;
      return false;
    }
    return false;
  }

  private static boolean valid(int[][] maze, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;
    return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1;
  }

  public static void main(String[] args) {
    int[][] maze = { //
        { 1, 0, 0, 0 }, //
        { 1, 1, 0, 1 }, //
        { 0, 1, 0, 0 }, //
        { 1, 1, 1, 1 }//
    };//
    solveMaze(maze);
  }
}
