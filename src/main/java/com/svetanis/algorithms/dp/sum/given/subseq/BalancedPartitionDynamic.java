package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.algorithms.dp.sum.given.subseq.GivenSumSubSeqBottomUp.isSum;
import static com.svetanis.java.base.utils.Arrays.sum;
import static com.svetanis.java.base.utils.Nums.isOdd;

// Partition problem is to determine whether a given set can be partitioned  
// into two subsets such that the sum of elements in both subsets is same. 

public final class BalancedPartitionDynamic {

  public static boolean isPartition(int[] a) {
    // time complexity: O(sum * n)

    int sum = sum(a);

    // if sum is odd there can't be
    // two subsets with equal sum
    if (isOdd(sum)) {
      return false;
    }

    return isSum(a, sum / 2);
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 5, 9, 12 };
    System.out.println(isPartition(a));
  }
}
