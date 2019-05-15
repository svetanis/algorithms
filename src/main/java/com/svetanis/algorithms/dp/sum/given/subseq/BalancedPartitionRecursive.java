package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.algorithms.dp.sum.given.subseq.GivenSumSubSeqRecursive.isSum;
import static com.svetanis.java.base.utils.Arrays.sum;
import static com.svetanis.java.base.utils.Nums.isOdd;

public final class BalancedPartitionRecursive {

  public static boolean isPartition(int[] a) {
    // time complexity: O(2^n)

    int sum = sum(a);

    // if sum is odd there can't be
    // two subsets with equal sum
    if (isOdd(sum)) {
      return false;
    }

    // find if there is a subset with
    // sum equal to half of total sum
    return isSum(a, sum / 2);
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 5, 9, 12 };
    System.out.println(isPartition(a));
  }
}
