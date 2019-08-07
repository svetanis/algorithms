package com.svetanis.algorithms.dp.rodcut;

import static com.svetanis.java.base.utils.Nums.max;

// Given a rope of length n meters, 
// cut the rope in different parts of integer lengths in a way 
// that maximizes product of lengths of all parts. 

// maxProd(n) : the maximum product for a rope of length n

// maxProd(n) = max(i * (n - i), i * maxProdRec(n - i)) where 1 <= i <= n

public final class MaxProductCutRecursive {

  public static int rodCut(int n) {
    if (n == 0 || n == 1) {
      return 0;
    }
    // make a cut at different places
    // and take the max of all
    int max = 0;
    for (int i = 1; i < n; i++) {
      max = max(max, i * (n - i), i * rodCut(n - i));
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(rodCut(15));
  }
}
