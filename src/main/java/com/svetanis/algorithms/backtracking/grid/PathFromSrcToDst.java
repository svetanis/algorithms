package com.svetanis.algorithms.backtracking.grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

// Given nxn matrix of positive integers,
// find path from its first cell to the last one
//
// Input: an n x n matrix of positive integers. From a cell holding v, one move goes exactly v
// cells up, down, left or right, and must land inside the matrix on a cell that is not already on
// the path. Returns the cells of a path from the top-left cell to the bottom-right cell, in
// order, or an empty list if there is none. No LeetCode problem.
//
// The one idea: the number in a cell is the length of the jump made from it, so a cell has at
// most four places to go. The walk adds the cell to the path, stops with true at the last cell,
// and otherwise tries the four jumps in turn; a jump is taken only onto a cell not yet on the
// path. When all four fail, the cell is taken off the path again and the caller tries its next
// jump.
//
// MOVES: NOT a step to a neighbour. `dx[i] * val` multiplies the step by the
// value in the cell being left, so this jumps. From a cell holding 7 you land
// seven columns or seven rows away. Everything else -- the marker, the undo,
// the four directions -- is the ordinary grid walk.
// REPORTS: whether a path exists, combined with `||`: the first `true` returns
// immediately and the rest of the loop is skipped.
//
// Time: O(4^(n * n) * n * n) -- a path has at most n * n cells with up to four jumps from each,
// and no failed cell is remembered; each jump also scans the path with contains().
// Space: O(n * n) -- the path, and a recursion at most n * n deep.

public final class PathFromSrcToDst {

  private static final int[] dx = { -1, 0, 0, 1 };
  private static final int[] dy = { 0, -1, 1, 0 };

  public static List<Point> path(int[][] grid) {
    Point src = new Point(0, 0);
    List<Point> path = new ArrayList<>();
    path(grid, path, src);
    return path;
  }

  private static boolean path(int[][] grid, List<Point> path, Point point) {
    int n = grid.length;
    path.add(point); // TAKE: the cell goes on the path
    if (point.x == n - 1 && point.y == n - 1) { // BASE: the last cell
      return true;
    }
    int val = grid[point.x][point.y]; // the jump length from this cell
    for (int i = 0; i < dx.length; i++) {
      int x = point.x + dx[i] * val;
      int y = point.y + dy[i] * val;
      Point next = new Point(x, y);
      if (valid(path, next, n) && path(grid, path, next)) { // WALK: jump, stop at the first success
        return true;
      }
    }
    path.remove(path.size() - 1); // UNDO: every jump failed, take the cell off the path
    return false;
  }

  private static boolean valid(List<Point> path, Point p, int n) {
    return p.x >= 0 && p.x < n && p.y >= 0 && p.y < n && !path.contains(p);
  }

  public static void main(String[] args) {
    int[][] grid = { //
        { 7, 1, 3, 5, 3, 6, 1, 1, 7, 5 }, //
        { 2, 3, 6, 1, 1, 6, 6, 6, 1, 2 }, //
        { 6, 1, 7, 2, 1, 4, 7, 6, 6, 2 }, //
        { 6, 6, 7, 1, 3, 3, 5, 1, 3, 4 }, //
        { 5, 5, 6, 1, 5, 4, 6, 1, 7, 4 }, //
        { 3, 5, 5, 2, 7, 5, 3, 4, 3, 6 }, //
        { 4, 1, 4, 3, 6, 4, 5, 3, 2, 6 }, //
        { 4, 4, 1, 7, 4, 3, 3, 1, 4, 2 }, //
        { 4, 4, 5, 1, 5, 2, 3, 5, 3, 5 }, //
        { 3, 6, 3, 5, 2, 2, 6, 4, 2, 1 } //
    };//
    for (Point point : path(grid)) {
      System.out.println(point); // the path, one point per line, from Point[x=0,y=0] to Point[x=9,y=9]
    }
    System.out.println(); // a blank line ends the list
  }
}
