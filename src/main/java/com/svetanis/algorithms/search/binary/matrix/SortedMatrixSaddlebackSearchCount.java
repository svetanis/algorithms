package com.svetanis.algorithms.search.binary.matrix;

public final class SortedMatrixSaddlebackSearchCount {

//  Find the count of a given integer x in the 2D sorted array.

  public static int search(int[][] matrix, int x) {
    // time complexity: O(n*m)

    int n = matrix.length;
    int row = 0;
    int col = n - 1; 
    int count = 0;

    while (col >= 0 && row < n) {
      if (matrix[row][col] < x) {
        ++row;
      } else if (matrix[row][col] > x) {
        --col;
      } else { // matrix[row][col] == x
        int c = col;
        while (c >= 0 && matrix[row][c] == x) {
          ++count;
          --c;
        }
        ++row;
      }
    }
    return count;
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
