package com.svetanis.algorithms.backtracking.grid;

// A Maze is given as N*N binary matrix of blocks
// where source block is the upper left most block i.e., maze[0][0]
// and destination block is lower rightmost block i.e., maze[N-1][N-1].
// A rat starts from source and has to reach the destination.
// The rat can move only in two directions: forward and down.
// In the maze matrix, 0 means the block is a dead end and 1 means
// the block can be used in the path from source to destination.
//
// Input: a binary matrix with n rows and m columns (the classic puzzle is square). The rat moves
// one block down or one block right, only onto blocks holding 1; the source and the destination
// must both hold 1 too. Prints "no solution exists" if there is no such path, then prints the
// matrix of the path found: 1 on the blocks of the path, 0 elsewhere (all 0 when there is none).
// No LeetCode problem. The search is exponential, so mazes are small.
//
// The one idea: every path from the source to the destination takes some blocks to the right and
// some down, and a block that cannot reach the destination is a dead end. The search marks the
// block it stands on as part of the path, tries down, then right, and if neither reaches the
// destination it unmarks the block and gives up on it, so the caller tries its other way.
//
// MOVES: down and right only. A coordinate never decreases, so a cycle cannot
// be built and nothing here needs a marker for termination -- `visited` is used
// to RECORD the path for printing, not to prevent revisiting.
// REPORTS: whether a path exists, combined with `||`: the first `true` returns.
//
// Time: O(2^(n + m)) -- two ways on from every block, and a path has n + m - 1 blocks; a dead
// block is not remembered, so it is searched again from each route that reaches it.
// Space: O(n * m) -- the path matrix, and a recursion at most n + m - 1 deep.

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
    if (x == n - 1 && y == m - 1 && maze[x][y] == 1) { // BASE: the destination, and open
      visited[x][y] = 1;
      return true;
    }

    // check if maze[x][y] is valid
    if (valid(maze, x, y)) {
      // mark x, y as part
      // of solution path
      visited[x][y] = 1; // MARK: on the path

      // move forward in x direction
      if (solveMaze(maze, visited, x + 1, y)) { // WALK: down
        return true;
      }

      // if x moving in x direction
      // doesn't give solution then
      // move down in y direction
      if (solveMaze(maze, visited, x, y + 1)) { // WALK: right
        return true;
      }

      // if none of the above
      // movements work then BACKTRACK:
      // unmark x, y as part of solution path
      visited[x][y] = 0; // UNDO: a dead end
      return false;
    }
    return false;
  }

  private static boolean valid(int[][] maze, int x, int y) {
    int n = maze.length;
    int m = maze[0].length;
    return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1;
  }

  // one row per line, every number followed by a space, then a blank line
  private static void print(int[][] matrix) {
    for (int[] row : matrix) {
      for (int value : row) {
        System.out.print(value);
        System.out.print(' '); // each number is followed by a space
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[][] maze = { //
        { 1, 0, 0, 0 }, //
        { 1, 1, 0, 1 }, //
        { 0, 1, 0, 0 }, //
        { 1, 1, 1, 1 }//
    };//
    solveMaze(maze); // prints the path matrix: 1 0 0 0 / 1 1 0 0 / 0 1 0 0 / 0 1 1 1
  }
}
