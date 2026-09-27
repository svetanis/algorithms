package com.svetanis.algorithms.bits;

// 201. Bitwise AND of Numbers Range

// the AND of every number from left to right keeps only the binary
// prefix that left and right share: below it, the range crosses from
// prefix 0111... to prefix 1000..., so every lower slot meets a 0

public final class BitwiseAndInRange201 {
  // Time Complexity: O(log n)
  // Space Complexity: O(1)

  public static int bitwiseAnd(int left, int right) {
    while (left < right) {
      right = right & (right - 1); // drop right's lowest 1 while it is still above left
    }
    return right;
  }

  public static int bitwiseAnd2(int left, int right) {
  	int shift = 0; // slots dropped from both ends so far
    while (left < right) {
    	left >>= 1;
    	right >>= 1;
    	shift++;
    }
    return left << shift; // the shared prefix, moved back into place
  }

  public static void main(String[] args) {
    System.out.println(bitwiseAnd(5, 7)); // 4
    System.out.println(bitwiseAnd(0, 0)); // 0
    System.out.println(bitwiseAnd(1, 2147483647)); // 0

    System.out.println(bitwiseAnd2(5, 7)); // 4
    System.out.println(bitwiseAnd2(0, 0)); // 0
    System.out.println(bitwiseAnd2(1, 2147483647)); // 0
  }
}
