package com.svetanis.algorithms.backtracking.grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

// Given a 2D array of black and white entries representing
// a maze with designated entrance and exit points,
// find a path from the entrance to the exit, if one exists.
//
// Input: a 2D array where 0 is a white (open) cell and 1 is a black (wall) cell, an entrance and
// an exit, both open. A move is one step up, down, left or right onto an open cell. Returns the
// cells of one path from the entrance to the exit, in order, or an empty list if there is none.
// The maze array is changed: every cell the search enters is set to 1. No LeetCode problem.
//
// The one idea: set each cell to 1 the moment the search enters it, so no cell is entered twice,
// and extend a path of the cells entered on the way. A step onto an open cell is tried in turn in
// each of the four directions; when none leads to the exit, the cell comes off the path.
//
// MOVES: all four neighbours.
// MARKER: it writes into the maze itself and NEVER clears the mark. That looks
// like the missing-undo bug and is not one. For a yes-or-no reachability
// question, a cell that cannot reach the destination cannot reach it from any
// other route either, so leaving the mark on prunes without losing an answer.
// Spelling a word (LC 79) is the case where the mark must come off, because
// there the letters already used are part of the question.
//
// Time: O(n * m) -- every cell is entered at most once, and each entry does four neighbour checks.
// Space: O(n * m) -- the path and the recursion, at most one entry per cell.

public final class PathInMaze {

  public static List<Point> maze(int[][] maze, Point src, Point dst) {

    List<Point> path = new ArrayList<>();
    path.add(src);
    maze[src.x][src.y] = 1; // MARK: the entrance is used
    if (!isPath(maze, src, dst, path)) {
      path.remove(path.size() - 1); // UNDO: no path, the list ends up empty
    }
    return path;
  }

  private static boolean isPath(int[][] maze, Point src, Point dst, List<Point> path) {

    if (src.equals(dst)) { // BASE: the exit
      return true;
    }
    List<Point> points = points();
    for (Point p : points) {
      Point next = new Point(src.x + p.x, src.y + p.y);
      if (isSafe(next, maze)) {
        maze[next.x][next.y] = 1; // MARK: never cleared, see MARKER above
        path.add(next); // TAKE: the cell goes on the path
        if (isPath(maze, next, dst, path)) { // WALK: stop at the first success
          return true;
        }
        path.remove(path.size() - 1); // UNDO: a dead end, off the path (the mark stays)
      }
    }
    return false;
  }

  private static List<Point> points() {
    List<Point> list = new ArrayList<>();
    list.add(new Point(0, 1));
    list.add(new Point(0, -1));
    list.add(new Point(1, 0));
    list.add(new Point(-1, 0));
    return list;
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
    for (Point point : maze(grid, src, dst)) {
      System.out.println(point); // the path, one point per line, from Point[x=0,y=0] to Point[x=3,y=3]
    }
    System.out.println(); // a blank line ends the list
  }

}
