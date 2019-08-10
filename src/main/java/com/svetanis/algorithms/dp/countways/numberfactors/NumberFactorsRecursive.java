package com.svetanis.algorithms.dp.countways.numberfactors;

public final class NumberFactorsRecursive {

  // Given a number ‘n’, implement a method
  // to count how many possible ways there are
  // to express ‘n’ as the sum of 1, 3, or 4.

  public static int count(int n) {
    // time complexity: O(3^n)

    // base case
    if (n < 3) {
      return 1;
    }

    if (n == 3) {
      return 2;
    }

    int one = count(n - 1);
    int three = count(n - 3);
    int four = count(n - 4);
    return one + three + four;
  }

  public static void main(String[] args) {
    System.out.println(count(4));
    System.out.println(count(5));
    System.out.println(count(6));
  }
}
