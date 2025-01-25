package com.svetanis.algorithms.bits.xor;

// 201. Bitwise AND of Numbers Range

public final class BitwiseAndInRange201 {
  // Time Complexity: O(log n)
  // Space Complexity: O(1)

  public static int bitwiseAnd(int left, int right) {
    while (left < right) {
      right = right & (right - 1);
    }
    return right;
  }

  public static void main(String[] args) {
    System.out.println(bitwiseAnd(5, 7)); // 4
    System.out.println(bitwiseAnd(0, 0)); // 0
    System.out.println(bitwiseAnd(1, 2147483647)); // 0
  }
}
