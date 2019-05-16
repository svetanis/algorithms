package com.svetanis.algorithms.dp.math.permutationcoefficient;

public class PermuteCoeffSpaceEfficient {

  // P(n, k) = n!/(n - k)!
  public static int permute(int n, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    int Fn = 1;
    int Fk = 1;
    for (int i = 1; i <= n; i++) {
      Fn *= i;
      if (i == n - k) {
        Fk = Fn;
      }
    }
    return Fn / Fk;
  }

  public static void main(String[] args) {
    int n = 10;
    int k = 5;
    System.out.println(permute(n, k));
  }
}
