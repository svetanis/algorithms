package com.svetanis.algorithms.dp.math.factorial;

public final class FactorialRecursive {

  public static int factorial(int n) {
    int result = 1;

    if (n < 0) {
      result = -1;
    } else if (n == 0 || n == 1) {
      result = 1;
    } else {
      result = n * factorial(n - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(factorial(5));
  }
}