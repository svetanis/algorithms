package com.svetanis.algorithms.bits.popcount;

// 1318. Minimum Flips to Make a OR b Equal to c

// every slot is its own small problem: count the flips slot by slot

public final class MinFlips1318 {
  // Time Complexity: O(1)
  // Space Complexity: O(1)

  public static int minFlips(int a, int b, int c) {
    int count = 0;
    for (int i = 0; i < 30; i++) { // a, b, c <= 10^9 < 2^30
      int b1 = (a >> i) & 1;
      int b2 = (b >> i) & 1;
      int b3 = (c >> i) & 1;
      int or = b1 | b2;
      if (or != b3) { // this slot is wrong
        boolean bothOnes = b1 == 1 && b2 == 1;
        count += bothOnes ? 2 : 1; // c wants 0 and both are 1: flip both; else one flip
      }
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(minFlips(2, 6, 5)); // 3
    System.out.println(minFlips(4, 2, 7)); // 1
    System.out.println(minFlips(1, 2, 3)); // 0
  }
}
