package com.svetanis.algorithms.bits.popcount;

// 461. Hamming Distance

public final class HammingDistance {
  // Time Complexity: O(1)

  public static int hammingDist(int a, int b) {
  	return Integer.bitCount(a ^ b); // a ^ b has a 1 exactly where a and b differ
  }

  public static void main(String[] args) {
    System.out.println(hammingDist(5, 11));
  }
}

