package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseRecursive {

  public static int count(int n) {
    // time complexity: O(3^n)

    // base case
    if (n < 0) {
      return 0;
    } else if (n == 0) {
      return 1;
    } else { // recursive call
      int one = count(n - 1);
      int two = count(n - 2);
      int three = count(n - 3);
      return one + two + three;
    }
  }

  public static void main(String[] args) {
    int n = 3;
    System.out.println(count(n));
  }
}
