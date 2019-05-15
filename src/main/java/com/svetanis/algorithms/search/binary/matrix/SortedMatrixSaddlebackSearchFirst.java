package com.svetanis.algorithms.search.binary.matrix;

import com.svetanis.java.base.Pair;

// Design an efficient algorithm to search for a given integer x in a 2D sorted array a[0..m][0..n].
// It is sorted row-wise and column-wise in ascending order. 
// In case of multiple occurrences, please find the very first occurrence,
// i.e., the occurrence with the smallest value of the row index and 
// at the same time the occurrence with the smallest value of the column index as well.

public final class SortedMatrixSaddlebackSearchFirst {

  public static Pair<Integer, Integer> search(int[][] matrix, int x) {
    // time complexity: O(n + m)

    int n = matrix.length;
    int row = 0;
    int col = n - 1;

    while (row < n && col >= 0 && x != matrix[row][col]) {
      if (matrix[row][col] < x) {
        ++row;
      } else {
        --col;
      }
    }

    while (row < n && col != -1 && x == matrix[row][col]) {
      --col;
    }
    ++col;
    return Pair.build(row, col);
  }

  public static void main(String[] args) {
    int[][] matrix1 = { { 10, 20, 30, 40 }, 
                        { 15, 25, 35, 45 }, 
                        { 27, 29, 37, 48 }, 
                        { 32, 33, 39, 50 } };
    System.out.println(search(matrix1, 29));

    int[][] matrix2 = { { 2, 2, 3, 5 }, 
                        { 3, 4, 5, 6 }, 
                        { 3, 5, 6, 8 }, 
                        { 3, 6, 7, 9 } };
    System.out.println(search(matrix2, 6));

    int[][] matrix3 = { { 2, 2, 3, 5 }, 
                        { 3, 4, 6, 6 }, 
                        { 3, 5, 6, 6 }, 
                        { 3, 6, 6, 9 } };
    System.out.println(search(matrix3, 6));

    int[][] matrix4 = { { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 } };
    System.out.println(search(matrix4, 6));
  }
}
