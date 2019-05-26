package com.svetanis.algorithms.string.bitvector;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

public final class FirstRepeatedChar {

  public static Optional<Integer> firstRepeated(String str) {
    // Time complexity: O(n)
    // Space complexity: O(1)

    int n = str.length();

    int checker = 0;
    for (int i = 0; i < n; ++i) {
      int c = str.charAt(i) - 'a';
      // getBit: x & (1 << i)
      int bit = checker & (1 << c);
      if (bit > 0) {
        return of(i);
      }
      // setBit: x | (1 << i)
      checker |= (1 << c);
    }
    return absent();
  }

  public static void main(String[] args) {
    String s = "abcfdeacf";
    System.out.println(firstRepeated(s));
  }
}