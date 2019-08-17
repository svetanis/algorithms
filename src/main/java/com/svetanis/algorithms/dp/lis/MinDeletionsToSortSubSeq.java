package com.svetanis.algorithms.dp.lis;

import static com.svetanis.algorithms.dp.lis.LisLenBottomUp.lis;

// Given a number sequence, 
// find the min num of elements 
// that should be deleted to make 
// the remaining sequence sorted

public final class MinDeletionsToSortSubSeq {

  public static int minDeletions(int[] a) {
    int n = a.length;
    int lis = lis(a);
    return n - lis;
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 2, 3, 6, 10, 1, 12 };
    System.out.println(minDeletions(a1));

    int[] a2 = { -4, 10, 3, 7, 15 };
    System.out.println(minDeletions(a2));

    int[] a3 = { 3, 2, 1, 0 };
    System.out.println(minDeletions(a3));

  }
}