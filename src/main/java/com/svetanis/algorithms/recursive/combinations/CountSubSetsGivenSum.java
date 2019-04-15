package com.svetanis.algorithms.recursive.combinations;

public final class CountSubSetsGivenSum {

  public static int count(int[] a, int sum) {
    return count(a, 0, sum);
  }

  private static int count(int[] a, int i, int sum) {
    // Time Complexity: O(2^n)
    
    // order of base cases matters
    if (sum == 0) {
      return 1;
    }

    if (sum < 0 || i == a.length) {
      return 0;
    }

    int excl = count(a, i + 1, sum);
    int incl = count(a, i + 1, sum - a[i]);
    return excl + incl;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6 };
    System.out.println(count(a, 10));
  }
}
