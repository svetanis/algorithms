package com.svetanis.algorithms.search.binary.math;

public final class FloorSquareRootBinarySearchIterative {

// Given an integer x, find square root of it. 
// If x is not a perfect square, then return floor(√x).
  
  public static int sqrt(int x) {
    long start = 0;
    long end = x;
    if (x == 0 || x == 1) {
      return x;
    }

    long floor = -1;
    while (start <= end) {
      long mid = start + (end - start) / 2;
      if (mid * mid == x) {
        return new Long(mid).intValue();
      }
      if (x < mid * mid) {
        end = mid - 1;
      } else {
        start = mid + 1;
        floor = mid;
      }
    }
    return new Long(floor).intValue();
  }

  public static void main(String[] args) {
    System.out.println(sqrt(11));
  }
}