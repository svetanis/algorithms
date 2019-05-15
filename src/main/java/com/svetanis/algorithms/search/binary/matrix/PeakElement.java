package com.svetanis.algorithms.search.binary.matrix;

import static java.lang.Integer.MIN_VALUE;

import com.svetanis.java.base.Pair;

// An element is a peak element if it is greater than or  
// equal to its four neighbors, left, right, top and bottom. 

public final class PeakElement {

  public static int peak(int[][] matrix) {
    // Time Complexity: O(rows * log(col))

    int n = matrix.length;
    int m = matrix[0].length;
    int mid = m / 2;
    return peak(matrix, n, m, mid);
  }

  private static int peak(int[][] matrix, int r, int c, int mid) {
    Pair<Integer, Integer> pair = colMax(matrix, mid);
    int max = pair.getLeft();
    int index = pair.getRight();

    if (mid == 0 || mid == c - 1) {
      return max;
    }

    boolean one = max >= matrix[index][mid - 1];
    boolean two = max >= matrix[index][mid + 1];
    if (one && two) {
      return max;
    }

    // max is less than its left
    if (max < matrix[index][mid - 1]) {
      return peak(matrix, r, c, mid - mid / 2);
    }
    // max is less than its right
    return peak(matrix, r, c, mid + mid / 2);
  }

  private static Pair<Integer, Integer> colMax(int[][] matrix, int mid) {
    int n = matrix.length;
    int max = MIN_VALUE;
    int index = -1;
    for (int i = 0; i < n; i++) {
      if (max < matrix[i][mid]) {
        max = matrix[i][mid];
        index = i;
      }
    }
    return Pair.build(max, index);
  }

  public static void main(String[] args) {
    int[][] matrix = { //
        { 10, 8, 10, 10 }, //
        { 14, 13, 12, 11 }, //
        { 15, 9, 11, 21 }, //
        { 16, 17, 19, 20 }//
    };//
    System.out.println(peak(matrix));
  }
}
