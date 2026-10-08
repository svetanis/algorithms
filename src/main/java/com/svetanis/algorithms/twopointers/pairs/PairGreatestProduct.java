package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Math.sqrt;
import static java.util.Arrays.sort;

import java.util.HashMap;
import java.util.Map;

// Greatest Number That Is a Product of Two Array Numbers
//
// Input: an array of positive integers.
// Return: the largest number of the array that equals the product of two numbers of
// the array at two different positions; -1 if there is none.
// Convention the code follows: a number may be one of its own two factors when 1 is
// the other -- {17, 2, 1, 35, 30} gives 35 = 35 * 1 -- but the outer loop never tries
// the two smallest numbers as the product, so a two-number array such as {1, 3} gives
// -1. When every value is at least 2 neither case arises. The source statement would
// settle both.
//
// The one idea: sort, then try the candidates from the largest down, so the first one
// that works is the answer. Of two factors of c, the smaller is at most sqrt(c), so
// only the numbers a[j] <= sqrt(c) are tried as the small factor. The other factor is
// then c / a[j], and a count of every value says whether it is present -- twice
// present when it equals a[j].
//
// Sibling: PairMaxProduct -- the largest product of two numbers, not a number that is one
//
// Time: O(n^2) in the worst case -- the sort, then for each candidate every number up
// to its square root.
// Space: O(n) -- the map. The array is sorted in place: the caller's order is lost.

public final class PairGreatestProduct {

  public static int product(int[] a) {
    int n = a.length;
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1);             // COUNT each value
    }
    sort(a);                                              // SORT: candidates from the largest down
    for (int i = n - 1; i > 1; i--) {                     // the candidate product a[i]
      for (int j = 0; j < i && a[j] <= sqrt(a[i]); j++) { // STOP: a small factor is at most sqrt(a[i])
        if (a[i] % a[j] == 0) {
          int result = a[i] / a[j];                       // the other factor
          int freq = map.getOrDefault(result, 0);         // SEE whether it is present
          if (result != a[j] && freq > 0) {
            return a[i];                                  // FOUND: two different factors
          } else if (result == a[j] && freq > 1) {
            return a[i];                                  // FOUND: a square, from two copies of its root
          }
        }
      }
    }
    return -1;                                            // RETURN: no number is a product
  }

  public static void main(String[] args) {
    int[] a = { 30, 10, 9, 3, 35 };
    System.out.println(product(a)); // 30

    int[] a1 = { 17, 2, 1, 35, 30 };
    System.out.println(product(a1)); // 35

    int[] a2 = { 10, 2, 4, 30, 35 };
    System.out.println(product(a2)); // -1
  }
}
