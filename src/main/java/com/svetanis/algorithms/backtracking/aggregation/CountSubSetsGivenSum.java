package com.svetanis.algorithms.backtracking.aggregation;

public final class CountSubSetsGivenSum {

  public static int count(int[] a, int sum) {
    return count(a, 0, sum, false);
  }

  private static int count(int[] a, int i, int sum, boolean atLeastOneIncl) {
    // Time Complexity: O(2^n)

    // the count is settled only at the end of the array. returning 1 as soon
    // as sum hits 0 stops the walk there, so a subset that reaches the target
    // early and could still take a 0 -- or a pair like +4 and -4 -- is counted
    // once instead of once per extension. with every value 1 or more that can
    // never happen, which is why the shortcut looked correct.
    if (i == a.length) {
      return sum == 0 && atLeastOneIncl ? 1 : 0;
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
