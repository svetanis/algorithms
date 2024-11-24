package com.svetanis.algorithms.dp.grid;

import static java.lang.Math.min;

import com.svetanis.java.base.utils.Triplet;

// Given a binary matrix, find out the maximum size square sub-matrix with all 1s. 

public final class MaxSizeSquareSubMatrix {

  public static void maxSubSquare(int[][] matrix) {
    // Time Complexity: O(m*n)
    int[][] sub = submatrix(matrix);
    Triplet<Integer, Integer, Integer> triplet = getMax(sub);
    print(matrix, triplet);
  }

  private static int[][] submatrix(int[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;

    int[][] sub = new int[n][m];

    // set first column of S[][]
    for (int i = 0; i < n; ++i) {
      sub[i][0] = matrix[i][0];
    }
    // set first row of S[][]
    for (int j = 0; j < m; ++j) {
      sub[0][j] = matrix[0][j];
    }

    // construct other entries of S[][]
    for (int i = 1; i < n; ++i) {
      for (int j = 1; j < m; ++j) {
        if (matrix[i][j] == 1) {
          int one = sub[i][j - 1];
          int two = sub[i - 1][j];
          int three = sub[i - 1][j - 1];
          sub[i][j] = min(min(one, two), three) + 1;
        } else {// if M[i][j] == 0
          sub[i][j] = 0;
        }
      }
    }
    return sub;
  }

  private static Triplet<Integer, Integer, Integer> getMax(int[][] sub) {
    int n = sub.length;
    int m = sub[0].length;

    // find the maximum entry, and
    // indexes of maximum entry in S[][]
    int max = sub[0][0];
    int row = 0;
    int col = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (max < sub[i][j]) {
          max = sub[i][j];
          row = i - 1;
          col = j - 1;
        }
      }
    }
    return Triplet.build(max, row, col);
  }

  private static void print(int[][] matrix, Triplet<Integer, Integer, Integer> triplet) {
    int max = triplet.getLeft();
    int row = triplet.getMid();
    int col = triplet.getRight();
    // print maximum size submatrix
    for (int i = row; i > row - max; --i) {
      for (int j = col; j > col - max; --j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] M = { //
        { 0, 1, 1, 0, 1 }, //
        { 1, 1, 0, 1, 0 }, //
        { 0, 1, 1, 1, 0 }, //
        { 1, 1, 1, 1, 0 }, //
        { 1, 1, 1, 1, 1 }, //
        { 0, 0, 0, 0, 0 }//
    };//
    maxSubSquare(M);
  }
}
