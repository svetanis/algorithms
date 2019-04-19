package com.svetanis.algorithms.recursive.combinations;

public final class CountSubSetsGivenSum {

  public static int count(int[] a, int sum) {
    return count(a, 0, sum, false);
  }

  private static int count(int[] a, int i, int sum, boolean atLeastOneIncl) {
    // Time Complexity: O(2^n)

    // order of base cases matters
    if (sum == 0 && atLeastOneIncl) {
      return 1;
    }

    if (i == a.length) {
      return 0;
    }

    int excl = count(a, i + 1, sum, atLeastOneIncl);
    int incl = count(a, i + 1, sum - a[i], true);
    return excl + incl;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6 };
    System.out.println(count(a, 10));

    int[] a0 = { 1 };
    System.out.println(count(a0, 0)); // false

    int[] a2 = { 10, 20 };
    System.out.println(count(a2, 0)); // false

    int[] a3 = { 0 };
    System.out.println(count(a3, 0)); // true

    int[] a4 = { -10, 10 };
    System.out.println(count(a4, 0)); // true

    int[] a5 = { -2, 2, 2, 1, 2, 3 };
    System.out.println(count(a5, 0)); // true

    int[] a6 = { -5, -10 };
    System.out.println(count(a6, -15)); // true
    
    int[] a7 = { 8, -11 };
    System.out.println(count(a7, 8)); // true
  }
}
