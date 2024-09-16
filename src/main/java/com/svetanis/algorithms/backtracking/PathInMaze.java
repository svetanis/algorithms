package com.svetanis.algorithms.backtracking;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.awt.Point;
import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a 2D array of black and white entries representing 
// a maze with designated entrance and exit points, 
// find a path from the entrance to the exit, if one exists.

public final class PathInMaze {

  public static ImmutableList<Point> maze(int[][] maze, Point src, Point dst) {

    List<Point> path = newArrayList();
    path.add(src);
    maze[src.x][src.y] = 1;
    if (!isPath(maze, src, dst, path)) {
      path.remove(path.size() - 1);
    }
    return newList(path);
  }

  private static boolean isPath(int[][] maze, Point src, Point dst, List<Point> path) {

    if (src.equals(dst)) {
      return true;
    }
    List<Point> points = points();
    for (Point p : points) {
      Point next = new Point(src.x + p.x, src.y + p.y);
      if (isSafe(next, maze)) {
        maze[next.x][next.y] = 1;
        path.add(next);
        if (isPath(maze, next, dst, path)) {
          return true;
        }
        path.remove(path.size() - 1);
      }
    }
    return false;
  }

  private static ImmutableList<Point> points() {
    List<Point> list = newArrayList();
    list.add(new Point(0, 1));
    list.add(new Point(0, -1));
    list.add(new Point(1, 0));
    list.add(new Point(-1, 0));
    return newList(list);
  }

  private static boolean isSafe(Point p, int[][] maze) {
    int r = maze.length;
    int c = maze[0].length;
    return p.x >= 0 && p.x < r && p.y >= 0 && p.y < c && maze[p.x][p.y] == 0;
  }

  public static void main(String[] args) {

    int[][] grid = { //
        { 0, 0, 1, 1 }, //
        { 1, 0, 0, 1 }, //
        { 0, 1, 0, 0 }, //
        { 1, 1, 0, 0 } //
    };//

    Point src = new Point(0, 0);
    Point dst = new Point(3, 3);
    printLines(maze(grid, src, dst));
  }

}
