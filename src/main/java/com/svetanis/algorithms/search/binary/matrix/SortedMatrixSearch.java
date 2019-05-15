package com.svetanis.algorithms.search.binary.matrix;

import com.svetanis.java.base.Pair;

public final class SortedMatrixSearch {

  public static Pair<Integer, Integer> search(int[][] matrix, int x) {
    // time complexity: O(n*m)

    int n = matrix.length; // number of rows

    if (n == 0) {
      return Pair.build(-1, -1);
    }

    int m = matrix[0].length; // number of columns

    int row = 0;
    int col = 0;
    while (row != n && col != m && x != matrix[row][col]) {
      ++col;
      if (row != n && col == m) {
        ++row;
        col = 0;
      }
    }
    return Pair.build(row, col);
  }

  public static void main(String[] args) {
    int[][] matrix = { //
        { 12, 2, 83, 5 }, //
        { 30, 14, 15, 16 }, //
        { 13, 5, 6, 81 }, //
        { 23, 6, 7, 19 } };//
    System.out.println(search(matrix, 6));
  }
}
