package com.svetanis.algorithms.dp.math.catalan;

public final class CatalanRecursive {

  // recursive
  public static int catalan(int n) {
    // Time Complexity: exponential

    // base case
    if (n <= 1) {
      return 1;
    }
    
    // catalan(n) is sum of
    // catalan(i) * catalan(n - i - 1)
    int result = 0;
    for (int i = 0; i < n; i++) {
      result += catalan(i) * catalan(n - i - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(catalan(i));
    }
  }
}

