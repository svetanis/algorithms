package com.svetanis.algorithms.dp.math.fib;

// 1137. N-th Tribonacci Number

public final class TribonacciBruteForce {

  public static int trib(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1 || n == 2) {
      return 1;
    }
    return trib(n - 1) + trib(n - 2) + trib(n - 3);
  }

  public static void main(String[] args) {
    System.out.println(trib(4)); // 4
    System.out.println(trib(25)); // 1389537
  }
}