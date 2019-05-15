package com.svetanis.algorithms.search.binary.matrix;

import com.svetanis.java.base.Pair;

public final class SortedMatrixSaddlebackSearch {

//  Let A be an n x n 2D array where rows and columns are sorted in increasing sorted order. 
//  Design an efficient algorithm that decides whether a number x appears in A.
  
  
  public static Pair<Integer, Integer> search(int[][] matrix, int x) {
    // time complexity: O(n + m)

    int n = matrix.length;
    int m = matrix[0].length;
    int row = 0;
    int col = m - 1;

    if (x < matrix[0][0] || x > matrix[n - 1][m - 1]) {
      return Pair.build(-1, -1);
    }
    
    while (row < n && col >= 0) {
      if (matrix[row][col] == x) {
        return Pair.build(row, col);
      }
      if (matrix[row][col] > x) {
        --col;
      } else { 
        ++row;
      }
    }
    return Pair.build(-1, -1);
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
    System.out.println(search(matrix3, 7));

    int[][] matrix4 = { { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 }, 
                        { 6, 6, 6, 6 } };
    System.out.println(search(matrix4, 6));
  }
}
