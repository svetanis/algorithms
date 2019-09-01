package com.svetanis.algorithms.recursive.backtracking;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.awt.Point;
import java.util.List;

import com.google.common.collect.ImmutableList;

// Given nxn matrix of positive integers,
// find path from its first cell to the last one

public final class PathFromSrcToDst {

  private static final int[] dx = { -1, 0, 0, 1 };
  private static final int[] dy = { 0, -1, 1, 0 };

  public static ImmutableList<Point> path(int[][] grid) {
    Point src = new Point(0, 0);
    List<Point> path = newArrayList();
    path(grid, path, src);
    return newList(path);
  }

  private static boolean path(int[][] grid, List<Point> path, Point point) {
    int n = grid.length;
    path.add(point);
    if (point.x == n - 1 && point.y == n - 1) {
      return true;
    }
    int val = grid[point.x][point.y];
    for (int i = 0; i < dx.length; i++) {
      int x = point.x + dx[i] * val;
      int y = point.y + dy[i] * val;
      Point next = new Point(x, y);
      if (valid(path, next, n) && path(grid, path, next)) {
        return true;
      }
    }
    path.remove(path.size() - 1);
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
    printLines(path(grid));
  }
}
