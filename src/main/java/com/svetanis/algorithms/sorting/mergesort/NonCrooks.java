package com.svetanis.algorithms.sorting.mergesort;

// Array A1[n] contains the names of people who work at Cornell, in alphabetical order.
// Array A2[m] contains the names of people on welfare at Ithaca, in alphabetical order.
// Thus, neither array contains duplicates and both arrays are monotonically increasing.
// Count the number of people who are presumably not crooks: those that appear in at least one array but not in both.

public final class NonCrooks {

  public static int nonCrooks(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;
    int count = 0;
    int i = 0;
    int j = 0;

    while (i < n && j < m) {
      if (a1[i] < a2[j]) {
        i++;
        count++;
      } else if (a1[i] == a2[j]) {
        i++;
        j++;
      } else if (a2[j] < a1[i]) {
        j++;
        count++;
      }
    }
    // add to count number of unprocessed elements
    count += a1.length - i;
    count += a2.length - j;
    return count;
  }

  public static void main(String[] args) {
    int[] ar1 = { 1, 5, 10, 20, 40, 80 };
    int[] ar2 = { 6, 7, 20, 80, 100 };
    System.out.println(nonCrooks(ar1, ar2));

    int[] a3 = { 3, 4, 5, 5, 10 };
    int[] a4 = { 5, 5, 10, 20 };
    System.out.println(nonCrooks(a3, a4));
  }
}
