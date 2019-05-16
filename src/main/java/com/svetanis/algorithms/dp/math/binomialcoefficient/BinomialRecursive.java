package com.svetanis.algorithms.dp.math.binomialcoefficient;

public final class BinomialRecursive {

  // A binomial coefficient C(n, k) also gives the number of ways,
  // disregarding order, that k objects can be chosen from among n objects;
  // more formally, the number of k-element subsets (or k-combinations) of an
  // n-element set.

  // C(n, 0) = C(n, n) = 1
  // C(n, k) = C(n - 1, k - 1) + C(n - 1, k)
  //           (include n)       (exclude n)

  public static int binomial(int n, int k) {
    // base case
    if (k == 0 || k == n) {
      return 1;
    }

    int incl = binomial(n - 1, k - 1);
    int excl = binomial(n - 1, k);
    return incl + excl;
  }

  public static void main(String[] args) {
    int n = 8;
    int k = 2;
    System.out.println(binomial(n, k));
  }
}
