package com.svetanis.algorithms.search.binary.unbounded;

// Given a function ‘int f(unsigned int x)’ 
// which takes a non-negative integer ‘x’ as input and returns an integer as output. 
// The function is monotonically increasing with respect to value of x, i.e., 
// the value of f(x+1) is greater than f(x) for every input x. 
// Find the value ‘n’ where f() becomes positive for the first time. 
// Since f() is monotonically increasing, values of f(n+1), f(n+2),… 
// must be positive and values of f(n-2), f(n-3), .. must be negative.

public final class MonotonicallyIncreasingFunction {
  // returns the value x where above function f() becomes
  // positive first time
  public static int firstPositive() {
    // when first value itself is positive
    if (f(0) > 0) {
      return 0;
    }

    // find 'high' for binary search by repeated doubling
    int i = 1;
    while (f(i) <= 0) {
      i = i * 2;
    }

    // call binary search
    return binarySearch(i / 2, i);
  }

  // searches first positive value of f(i) where low <= i <= high
  private static int binarySearch(int low, int high) {
    if (high >= low) {

      int mid = low + (high - low) / 2;

      // if f(mid) is greater than 0 and one of the
      // following two conditions is true:
      // 1. mid is equal to low
      // 2. f(mid- 1) is negative
      if (f(mid) > 0 && (mid == low || f(mid - 1) <= 0)) {
        return mid;
      }

      // if f(mid) is smaller than or equal to 0
      if (f(mid) <= 0) {
        return binarySearch((mid + 1), high);
      } else { // f(mid) > 0
        return binarySearch(low, (mid - 1));
      }
    }
    return -1;
  }

  // take an example function as f(x) = x^2 - 10*x - 20
  private static int f(int x) {
    return x * x - 10 * x - 20;
  }

  public static void main(String[] args) {
    System.out.println(firstPositive());
  }
}