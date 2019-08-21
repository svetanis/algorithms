package com.svetanis.algorithms.search.binary.math;

public final class FloorSquareRootBinarySearchRecursive {

  // Given an integer x, find square root of it.
  // If x is not a perfect square, then return floor(√x).

  public static int sqrt(int x) {
    return sqrt(x, 0, x);
  }

  private static int sqrt(int x, int start, int end) {
    if (x == 0 || x == 1) {
      return x;
    }

    int mid = start + (end - start) / 2;
    if (start > end) {
      return mid;
    }

    if (mid * mid == x) {
      return mid;
    } else if (x < mid * mid) {
      return sqrt(x, start, mid - 1);
    } else {
      return sqrt(x, mid + 1, end);
    }
  }

  public static void main(String[] args) {
    System.out.println(sqrt(11));
  }
}