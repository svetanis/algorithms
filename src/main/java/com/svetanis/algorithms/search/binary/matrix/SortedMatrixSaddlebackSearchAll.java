package com.svetanis.algorithms.search.binary.matrix;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// Report all the occurrences of a given integer x in the array a[m, n], i.e., 
// it will report all the row-indices (i) and column indices (j) of the array where x == a[i, j]

public final class SortedMatrixSaddlebackSearchAll {

  public static ImmutableList<Pair<Integer, Integer>> search(int[][] matrix, int x) {
    // time complexity: O(n * m)

    int n = matrix.length;
    int row = 0;
    int col = n - 1;
    List<Pair<Integer, Integer>> list = newArrayList();

    while (col >= 0 && row < n) {
      if (matrix[row][col] < x) {
        row++;
      } else if (matrix[row][col] > x) {
        col--;
      } else { // matrix[row][col] == x
        int c = col;
        while (c >= 0 && matrix[row][c] == x) {
          list.add(Pair.build(row, c));
          c--;
        }
        row++;
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[][] matrix1 = { { 10, 20, 30, 40 }, 
                        { 15, 25, 35, 45 }, 
                        { 27, 29, 37, 48 }, 
                        { 32, 33, 39, 50 } };
    printLines(search(matrix1, 29));

    int[][] matrix2 = { { 2, 2, 3, 5 }, 
                        { 3, 4, 5, 6 }, 
                        { 3, 5, 6, 8 }, 
                        { 3, 6, 7, 9 } };
    printLines(search(matrix2, 6));

    int[][] matrix3 = { { 2, 2, 3, 5 }, 
                        { 3, 4, 6, 6 }, 
                        { 3, 5, 6, 6 }, 
                        { 3, 6, 6, 9 } };
    printLines(search(matrix3, 6));

    int[][] matrix4 = { { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 } };
    printLines(search(matrix4, 6));
  }
}
