package com.svetanis.algorithms.dp.rodcut;

import static com.svetanis.java.base.utils.Nums.max;

//Given a rope of length n meters, 
//cut the rope in different parts of integer lengths in a way 
//that maximizes product of lengths of all parts. 

//maxProd(n) : the maximum product for a rope of length n

//maxProd(n) = max(i*(n-i), maxProdRec(n-i)*i) for all i in {1, 2, 3 .. n}

public final class MaxProductCutDynamic {

  public static int maxProduct(int n) {
    // time complexity: O(n^2)

    int[] dp = new int[n + 1];
    dp[0] = dp[1] = 0;

    // build the table dp[] in bottom up manner
    // and return the last entry from the table
    for (int i = 1; i <= n; i++) {
      int max = 0;
      for (int j = 2; j <= i / 2; j++) {
        max = max(max, (i - j) * j, j * dp[i - j]);
      }
      dp[i] = max;
    }
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(maxProduct(2)); // 1
    System.out.println(maxProduct(3)); // 2
    System.out.println(maxProduct(10));

  
  }
}
