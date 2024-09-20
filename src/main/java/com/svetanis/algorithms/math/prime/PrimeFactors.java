package com.svetanis.algorithms.math.prime;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Nums.isDivisible;
import static com.svetanis.java.base.utils.Nums.isEven;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.sqrt;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PrimeFactors {

  public static ImmutableList<Integer> primeFactors(int n) {
    List<Integer> list = newArrayList();

    // 1. while n is divisible by 2
    // print 2 and divide by 2
    while (isEven(n)) {
      list.add(2);
      n = n / 2;
    }

    // 2. n must be odd at this point.
    // start loop from i = 3 t sqrt(n)
    // while i divides n, print i,
    // divide n by i, increment by 2
    for (int i = 3; i <= sqrt(n); i = i + 2) {
      // while i divides n, print i and divide n
      while (isDivisible(n, i)) {
        list.add(i);
        n = n / i;
      }
    }

    // 3. this condition is to handle the case
    // when n is a prime number greater than 2
    if (n > 2) {
      list.add(n);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    print(primeFactors(315));
    print(primeFactors(789));
    print(primeFactors(20));

  }
}