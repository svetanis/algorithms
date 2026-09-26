package com.svetanis.algorithms.math.prime;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Nums.isDivisible;
import static com.svetanis.java.base.utils.Nums.isEven;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.sqrt;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Prime factors of n by trial division, smallest first, with repeats:
//   315 = 3 * 3 * 5 * 7 -> [3, 3, 5, 7]
// Any split a * b = n has one side <= sqrt(n), so trying divisors up
// to sqrt(n) finds every factor except possibly one prime above it.

public final class PrimeFactors {
  // Time complexity: O(sqrt(n))
  // Space complexity: O(log n), at most log2(n) factors

  private static final String NOT_POSITIVE = "n must be >= 1, was %d";

  public static ImmutableList<Integer> primeFactors(int n) {
    // 0 is even and 0 / 2 is 0, so step 1 would never end;
    // a negative n would lose its odd factors: -12 gave [2, 2]
    if (n < 1) {
      throw new IllegalArgumentException(NOT_POSITIVE.formatted(n));
    }
    List<Integer> list = newArrayList();

    // 1. while n is divisible by 2
    // add 2 and divide by 2
    while (isEven(n)) {
      list.add(2);
      n = n / 2;
    }

    // 2. n must be odd at this point.
    // loop i = 3, 5, 7, ... up to sqrt(n):
    // while i divides n, add i and divide n by i
    for (int i = 3; i <= sqrt(n); i = i + 2) {
      while (isDivisible(n, i)) {
        list.add(i);
        n = n / i;
      }
    }

    // 3. what is left, if above 2, is a prime factor
    // larger than sqrt of the original n: 14 -> [2, 7]
    if (n > 2) {
      list.add(n);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    print(primeFactors(315)); // 3 3 5 7
    print(primeFactors(789)); // 3 263
    print(primeFactors(20)); // 2 2 5
    print(primeFactors(100)); // 2 2 5 5
    print(primeFactors(1)); // (empty line)
  }
}
