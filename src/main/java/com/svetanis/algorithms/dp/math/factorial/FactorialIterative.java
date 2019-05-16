package com.svetanis.algorithms.dp.math.factorial;

public final class FactorialIterative {

  public static int factorial(int n) {

    if (n < 0) {
      return -1;
    } else if (n == 0 || n == 1) {
      return 1;
    }
    
    int f = 1;
    for (int i = 2; i <= n; i++) {
      f = f * i;
    }
    return f;
  }

  public static void main(String[] args) {
    System.out.println(factorial(5));
  }
}