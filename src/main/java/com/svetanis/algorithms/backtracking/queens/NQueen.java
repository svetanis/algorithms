package com.svetanis.algorithms.backtracking.queens;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 51. N-Queens (returns each solution as a list of columns, not as strings of '.' and 'Q')
//
// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal
//
// Input: n, the side of the board (1 <= n <= 9 on LeetCode). Returns every solution as a list of
// n numbers: entry r is the column of the queen in row r. n = 2 and n = 3 have no solution
// (an empty list); n = 4 has 2, n = 8 has 92.
//
// The one idea: two queens cannot share a row, so each row holds exactly one queen, and the
// solution is just "which column in each row". The search fills the rows from the top: for row r
// it tries every column and keeps the ones where the new queen shares no column and no diagonal
// with the queens already placed above (isSafe), then moves on to row r + 1. Two queens are on one
// diagonal exactly when their columns differ by as much as their rows do. Row n reached means n
// queens are placed, so the current array is one solution.
//
// Time: O(n! * n^2) -- a queen in row k sits in a column no earlier row used, so at most
// n! / (n - k)! placements reach row k (about e * n! in all), and each tries n columns at O(n)
// per isSafe check.
// Space: O(n) -- the array and the recursion depth; the result itself holds n numbers per solution.
//
// Siblings: NQueensBacktracking is the LC 51 answer (boards of strings, columns and diagonals kept
// in boolean arrays); NQueenPrint prints each board instead of returning it; NQueensII and
// NQueensIIBacktracking only count.

public final class NQueen {

  public static List<List<Integer>> queen(int n) {
    List<List<Integer>> lists = new ArrayList<>();
    Integer[] a = new Integer[n];
    queen(0, a, lists);
    return lists;
  }

  private static void queen(int row, Integer[] a, List<List<Integer>> lists) {
    int n = a.length;
    if (row == n) { // BASE: every row has a queen, record a copy of the columns
      lists.add(new ArrayList<>(Arrays.asList(a)));
    } else {
      for (int col = 0; col < n; col++) {
        if (isSafe(a, row, col)) { // TAKE: no queen above shares the column or a diagonal
          a[row] = col; // place queen
          queen(row + 1, a, lists); // WALK: on to the next row
        }
      }
    }
  }

  // check if (r, c) is a valid spot for a queen by checking
  // if there is a queen in the same column or diagonal
  // no need to check it for queens in the same row
  private static boolean isSafe(Integer[] a, int r, int c) {
    for (int row = 0; row < r; row++) {
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
    List<List<Integer>> lists = queen(4);
    printBoards(lists); // prints the 2 boards
    System.out.println(lists.size()); // 2
  }

  private static void printBoards(List<List<Integer>> boards) {
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
