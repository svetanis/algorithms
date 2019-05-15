package com.svetanis.algorithms.dp.sum.given.subseq;

public final class GivenSumSubSeqTopDown {
  
  private static final int K = 9;
  private static final int N = 6;
  private static int[][] M = init();
  
  public static int isSum(int[] a, int k) {
    return isSum(a, 0, k);
  }
  
  private static int isSum(int[] a, int i, int k) {
    // Time complexity: O(sum * n)
    
    int n = a.length;
    
    if (k == 0) {
      return 1;
    }

    if (n == 0 && k != 0) {
      return 0;
    }

    if (a[i] > k) {
      return isSum(a, i + 1, k);
    }
    
    if (M[i][k] != -1) {
      return M[i][k];
    }
    
    // 1. include a[i]
    int result = isSum(a, i + 1, k - a[i]);
    if (result != -1) {
      M[i + 1][k - a[i]] = result;
      return result;
    }

    // 2. exclude a[i]
    result = isSum(a, i + 1, k);
    if (result != -1) {
      M[i + 1][k] = result;
      return result;
    }
    
    return result;
  }

  private static int[][] init(){
    int[][] M = new int[N + 1][K + 1];
    for(int i = 0; i <= N; i++) {
      for(int j = 0; j <= K; j++) {
        M[i][j] = -1;
      }
    }
    return M;
  }
  
  public static void main(String[] args) {
    int[] a = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a, K));
  }
}
