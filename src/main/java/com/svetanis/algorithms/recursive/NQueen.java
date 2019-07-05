package com.svetanis.algorithms.recursive;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class NQueen {

  // Time Complexity: O(n^n)

  public static ImmutableList<ImmutableList<Integer>> queen(int n) {
    List<ImmutableList<Integer>> lists = newArrayList();
    Integer[] a = new Integer[n];
    queen(0, a, lists);
    return newList(lists);
  }

  private static void queen(int row, Integer[] a, List<ImmutableList<Integer>> lists) {
    int n = a.length;
    if (row == n) {
      lists.add(newList(a));
    } else {
      for (int col = 0; col < n; col++) {
        if (isSafe(a, row, col)) {
          a[row] = col; // place queen
          queen(row + 1, a, lists);
        }
      }
    }
  }

  // check if (r, c) is a valid spot for a queen by checking
  // if there is a queen in the same column or diagonal
  // no need to check it for queens in the same row
  private static boolean isSafe(Integer[] a, int row, int col) {
    for (int r = 0; r < row; r++) {
      int c = a[r];
      // check if rows have a queen in the same column
      if (col == c) {
        return false;
      }
      // check diagonals: if the distance between the columns equals
      // the distance between the rows, then they're in the same diagonal
      int dx = row - r;
      int dy = abs(c - col);
      if (dy == dx) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<ImmutableList<Integer>> lists = queen(4);
    printBoards(lists);
    System.out.println(lists.size());
  }

  private static void printBoards(List<ImmutableList<Integer>> boards) {
    for (int i = 0; i < boards.size(); ++i) {
      List<Integer> board = boards.get(i);
      printBoard(board);
    }
  }

  private static void printBoard(List<Integer> columns) {
    int n = columns.size();
    System.out.println("----------------");
    for (int i = 0; i < n; ++i) {
      System.out.print("|");
      for (int j = 0; j < n; ++j) {
        if (columns.get(i) == j) {
          System.out.print("Q|");
        } else {
          System.out.print(" |");
        }
      }
      System.out.println("\n----------------");
    }
    System.out.println("");
  }
}
