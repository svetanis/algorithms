package com.svetanis.algorithms.math.pow;

// 342. Power of Four

public final class IsPowerOfFour {

  // Divide out 4 while it goes in evenly; a power of four ends at 1.
  // Time Complexity: O(log n)
  // Space Complexity: O(1)
  public static boolean isPowerOfFourByDivision(int n) {
    if (n <= 0) {
      return false;
    }
    while (n % 4 == 0) {
      n /= 4;
    }
    return n == 1;
  }

  // A power of four is a power of two whose single 1-bit sits at an even
  // position: 1 (bit 0), 100 (bit 2), 10000 (bit 4). 0xAAAAAAAA is
  // 1010...1010, 1s at the odd positions 1, 3, 5, ..., so the bit must
  // miss it. 8 = 1000 has its bit at position 3 and is caught.
  // Time Complexity: O(1)
  // Space Complexity: O(1)
  public static boolean isPowerOfFourByMask(int n) {
    if (n <= 0) {
      return false;
    }
    boolean powerOf2 = (n & (n - 1)) == 0;
    boolean isBitInEven = (n & 0xAAAAAAAA) == 0;
    return powerOf2 && isBitInEven;
  }

  public static void main(String[] args) {
    System.out.println(isPowerOfFourByDivision(16)); // true
    System.out.println(isPowerOfFourByDivision(5)); // false
    System.out.println(isPowerOfFourByDivision(1)); // true
    System.out.println(isPowerOfFourByMask(16)); // true
    System.out.println(isPowerOfFourByMask(8)); // false
    System.out.println(isPowerOfFourByMask(1)); // true
  }
}
