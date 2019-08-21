package com.svetanis.algorithms.search.binary.math;

import static com.svetanis.java.base.utils.Nums.isOdd;

public final class ModularExponentiation {

  // (x * y) % p
  // ( x % p ) ( y % p ) == (x * y) % p
  // ( ( x % p ) ( y % p ) ) % p == (x * y) % p
  public static long pow(long x, long y, long p) {

    if (x == 0 && y == 0) {
      return 0;
    }

    // update x if it is >= p
    x = x % p;

    long power = 1;
    while (y > 0) {
      // y is odd (y & 1) == true;
      if (isOdd(y)) {
        power = ((x % p) * power) % p;
      }
      y = y / 2; // y = y >> 1;
      x = (x * x) % p;
    }
    return power < 0 ? power + p : power;
  }

  public static void main(String[] args) {
    System.out.println(pow(0, 0, 1));
    System.out.println(pow(2, 3, 3));
    System.out.println(pow(2, 5, 13));
    System.out.println(pow(2, 5, 13));
  }
}