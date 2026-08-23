package com.svetanis.algorithms.search.binary.matrix;

import static java.lang.Integer.MIN_VALUE;

import com.svetanis.java.base.Pair;

// An element is a peak element if it is greater than or  
// equal to its four neighbors, left, right, top and bottom. 

public final class PeakElement {

  public static int peak(int[][] matrix) {
    // Time Complexity: O(rows * log(col))

    int m = matrix[0].length;
    return peak(matrix, 0, m - 1);
  }

  // carry BOTH column bounds. deriving the next column from mid alone
  // -- mid - mid/2 -- is a no-op at mid == 1, so the recursion never ends
  private static int peak(int[][] matrix, int low, int high) {
    int mid = low + (high - low) / 2;
    Pair<Integer, Integer> pair = colMax(matrix, mid);
    int max = pair.getLeft();
    int index = pair.getRight();

    // max is the largest in its own column, so only the
    // left and right neighbours are left to beat it.
    // a column at the edge has no neighbour on that side, which counts as beaten
    int lastCol = matrix[0].length - 1;
    boolean atLeastLeft = mid == 0 || max >= matrix[index][mid - 1];
    boolean atLeastRight = mid == lastCol || max >= matrix[index][mid + 1];
    if (atLeastLeft && atLeastRight) {
      return max;
    }

    // max is less than its left
    if (mid > 0 && max < matrix[index][mid - 1]) {
      return peak(matrix, low, mid - 1);
    }
    // max is less than its right
    return peak(matrix, mid + 1, high);
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
