package com.svetanis.algorithms.search.binary.math;

import static java.lang.Math.abs;

public final class SquareRoot {

  public static double sqrt(double n, double e) {
    // decide the search range according to n
    double left = n < 1.0 ? n : 1.0;
    double right = n < 1.0 ? 1.0 : n;

    // keep searching if lower < upper
    while (right - left > e) {
      double mid = left + (right - left) / 2;
      double sqr = mid * mid;
      if (abs(sqr - n) <= e) {
        return mid;
      } else if (sqr - n > e) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    System.out.println(sqrt(4.0, 0.000000001));
    System.out.println(sqrt(4, 0.000001));
  }
}