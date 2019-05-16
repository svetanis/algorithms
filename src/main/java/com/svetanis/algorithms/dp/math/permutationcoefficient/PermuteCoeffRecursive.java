package com.svetanis.algorithms.dp.math.permutationcoefficient;

import static com.svetanis.algorithms.dp.math.factorial.FactorialRecursive.factorial;

public final class PermuteCoeffRecursive {

//  The number of permutations on a set of n elements is given by n!

//  The Permutation Coefficient represented by P(n, k) is used to represent 
//  the number of ways to obtain an ordered subset having k elements from a set of n elements.
  
  // P(n, k) = P(n - 1, k) + k * P(n - 1, k - 1)

  public static int permute(int n, int k) {
    // Time Complexity: exponential

    // base case
    if (n == k) {
      return factorial(n);
    } else if (k == 0) {
      return 1;
    } else if (k == 1) {
      return n;
    }
    return permute(n - 1, k) + k * permute(n - 1, k - 1);
  }

  public static void main(String[] args) {
    int n = 10;
    int k = 5;
    System.out.println(permute(n, k));
  }
}
