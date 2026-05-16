package com.svetanis.algorithms.math.pow;

// 342. Power of Four

public final class IsPowerOfFour {
  // Time Complexity: O(log n)

  public static boolean isPowerOfFour(int n) {
    if (n <= 0) {
      return false;
    }
    while (n % 4 == 0) {
      n /= 4;
    }
    return n == 1;
  }

  public static boolean isPowerOf4(int n) {
    if (n <= 0) {
      return false;
    }
    boolean powerOf2 = n != 0 && (n & (n - 1)) == 0;
    boolean isBitInEven = (n & 0xAAAAAAAA) == 0;
    return powerOf2 && isBitInEven;
  }

  public static void main(String[] args) {
    System.out.println(isPowerOfFour(16)); // true
    System.out.println(isPowerOfFour(5)); // false
    System.out.println(isPowerOfFour(1)); // true
  }
}