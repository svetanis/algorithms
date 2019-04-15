package com.svetanis.algorithms.recursive.permutations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class NQueen {

  private static int N = 4;

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

  // check if (row1, col1) is a valid spot for a queen by checking if there is a
  // queen in the same column or diagonal. We don't need to check it for queens
  // in the same row because the calling placeQueen only attempts to place one
  // queen at a time. We know this row is empty
  private static boolean isSafe(Integer[] a, int r, int c) {
    for (int row = 0; row < r; row++) {
      // check if (row, col) invalidates (r, c) as a queen spot
      int col = a[row];
      // check if rows have a queen in the same column
      if (c == col) {
        return false;
      }
      // check diagonals: if the distance between the columns equals
      // the distance between the rows, then they're in the same diagonal
      int deltaRow = r - row;
      int deltaCol = abs(col - c);
      if (deltaCol == deltaRow) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<ImmutableList<Integer>> lists = queen(N);
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
