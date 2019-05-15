package com.svetanis.algorithms.search.binary.matrix;

import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;

public final class RowWithMaxOnesII {

  public static int findRow(int[][] matrix) {
    // time complexity: O(n log m)

    int n = matrix.length;
    int m = matrix[0].length;

    int index = 0;
    int max = m - firstOccurrence(matrix[0], 0, m - 1, 1);

    // traverse for each row and
    // count number of 1s by finding the index of first 1
    for (int i = 1; i < n; ++i) {
      // count 1s in this row only if 
      // this row has more 1s than max so far
      if (matrix[i][m - max] == 1) {
        int first = firstOccurrence(matrix[i], 0, m - max, 1);
        if (first != -1 && m - first > max) {
          max = m - first;
          index = i;
        }
      }
    }
    return index;
  }

  public static void main(String[] args) {
    int[][] matrix = { { 0, 0, 0, 1 }, 
                       { 0, 1, 1, 1 }, 
                       { 1, 1, 1, 1 }, 
                       { 0, 0, 0, 0 } };
    System.out.println(findRow(matrix));
  }
}