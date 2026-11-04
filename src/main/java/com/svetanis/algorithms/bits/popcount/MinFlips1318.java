package com.svetanis.algorithms.bits.popcount;

// 1318. Minimum Flips to Make a OR b Equal to c
//
// Input: three ints a, b and c. A flip changes one binary digit of a or of b. Returns the fewest
// flips that make a | b equal to c. On LeetCode: 1 <= a, b, c <= 10^9, so every slot from 30 up is
// 0 in all three and only slots 0 to 29 are read.
//
// The one idea: a | b in one slot depends only on a's and b's digits in that slot, so every slot is
// its own small problem and the answer is the sum over slots. In one slot: if a's digit | b's digit
// already equals c's digit, no flip. If c wants 1 and both are 0, one flip turns either on. If c
// wants 0, every 1 there has to go: one flip if one of them is 1, two if both are.
//
// Time: O(1) -- 30 slots, since 10^9 < 2^30.
// Space: O(1) -- a few ints.

public final class MinFlips1318 {
  // Time Complexity: O(1)
  // Space Complexity: O(1)

  public static int minFlips(int a, int b, int c) {
    int count = 0;
    for (int i = 0; i < 30; i++) { // WALK the slots: a, b, c <= 10^9 < 2^30
      int b1 = (a >> i) & 1; // READ slot i of a, b and c
      int b2 = (b >> i) & 1;
      int b3 = (c >> i) & 1;
      int or = b1 | b2;
      if (or != b3) { // TEST: this slot is wrong
        boolean bothOnes = b1 == 1 && b2 == 1;
        count += bothOnes ? 2 : 1; // FLIP: c wants 0 and both are 1, flip both; else one flip
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
