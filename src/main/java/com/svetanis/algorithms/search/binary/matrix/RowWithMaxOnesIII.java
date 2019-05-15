package com.svetanis.algorithms.search.binary.matrix;

import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;

public final class RowWithMaxOnesIII {

  public static int findRow(int[][] matrix) {
    // time complexity: O(n + m)
    
    int n = matrix.length;
    int m = matrix[0].length;

    // the firstOccurrence() returns index of first 1 in row 0
    // use this index to initialize the index of leftmost 1 seen so far
    int first = firstOccurrence(matrix[0], 0, m - 1, 1) - 1;

    // if 1 is not present in the first row
    if (first == -1) {
      first = m - 1;
    }

    int index = 0;
    for (int i = 1; i < n; ++i) {
      // move left until 0 is found
      while (first >= 0 && matrix[i][first] == 1) {
        first = first - 1;
        index = i;
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