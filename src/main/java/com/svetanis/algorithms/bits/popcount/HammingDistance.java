package com.svetanis.algorithms.bits.popcount;

// 461. Hamming Distance
//
// Input: two ints a and b. Returns the number of slots in which a and b have different binary
// digits. On LeetCode: 0 <= x, y <= 2^31 - 1.
//
// The one idea: ^ gives 1 in a slot exactly when the two digits there differ and 0 when they
// agree, so a ^ b has a 1 in every slot where a and b differ and nowhere else. The distance is the
// number of 1s in a ^ b, which Integer.bitCount counts.
//
// Time: O(1) -- Integer.bitCount is a fixed handful of operations.
// Space: O(1).
//
// Siblings: TotalHammingDistance (477) adds this distance up over every pair of an array.

public final class HammingDistance {
  // Time Complexity: O(1)

  public static int hammingDist(int a, int b) {
  	return Integer.bitCount(a ^ b); // COUNT: a ^ b has a 1 exactly where a and b differ
  }

  public static void main(String[] args) {
    System.out.println(hammingDist(5, 11)); // 3
    System.out.println(hammingDist(1, 4)); // 2
    System.out.println(hammingDist(3, 1)); // 1
  }
}

