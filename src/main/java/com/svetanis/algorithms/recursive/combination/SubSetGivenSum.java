package com.svetanis.algorithms.recursive.combinations;

public final class SubSetGivenSum {

  // Time Complexity: O(2^n)
  
  public static boolean isSum(int[] a, int sum) {
    return isSum(a, 0, sum, false);
  }

  private static boolean isSum(int[] a, int i, int sum, boolean oneIncl) {

    if (i >= a.length) {
      return sum == 0 && oneIncl;
    }

    // exclude
    if(isSum(a, i + 1, sum, oneIncl)) {
      return true;
    }
    // include
    return isSum(a, i + 1, sum - a[i], true);
  }

  public static void main(String[] args) {
    int[] a = { 1 };
    System.out.println(isSum(a, 0)); // false

    int[] a2 = { 10, 20 };
    System.out.println(isSum(a2, 0)); // false

    int[] a3 = { 0 };
    System.out.println(isSum(a3, 0)); // true

    int[] a4 = { -10, 10 };
    System.out.println(isSum(a4, 0)); // true

    int[] a5 = { -2, 2, 2, 1, 2, 3 };
    System.out.println(isSum(a5, 0)); // true

  }
}
