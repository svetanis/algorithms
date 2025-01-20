package com.svetanis.algorithms.search.binary.matrix;

// 74. Search a 2D matrix

public final class Search2DMatrix74 {
  // Time Complexity: O(log(n*m))
  // Space Complexity: O(1)

  public static boolean search(int[][] matrix, int target) {
    int n = matrix.length; // number of rows
    int m = matrix[0].length; // number of columns
    int left = 0;
    int right = n * m - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      // map mid index to 2D position in matrix
      int x = mid / m, y = mid % m;
      if (matrix[x][y] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // left should point to the target element
    return matrix[left / m][left % m] == target;
  }

  public static void main(String[] args) {
    int[][] matrix = { //
        { 1, 3, 5, 7 }, //
        { 10, 11, 16, 20 }, //
        { 23, 30, 34, 60 } };//
    System.out.println(search(matrix, 3)); // true
    System.out.println(search(matrix, 13)); // false
  }
}
