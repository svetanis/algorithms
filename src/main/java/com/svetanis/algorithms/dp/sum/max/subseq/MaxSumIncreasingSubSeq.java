package com.svetanis.algorithms.dp.sum.max.subseq;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Arrays.maxElement;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.System.arraycopy;

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public class MaxSumIncreasingSubSeq {

  public static Pair<Integer, ImmutableList<Integer>> maxSum(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] sum = new int[n];
    arraycopy(a, 0, sum, 0, n);

    Set<Integer>[] sets = new LinkedHashSet[n];
    // initialize sets for all indexes
    for (int i = 0; i < n; ++i) {
      sets[i] = new LinkedHashSet<>();
    }
    sets[0].add(a[0]);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i] && sum[i] < sum[j] + a[i]) {
          sum[i] = sum[j] + a[i];
          sets[i].addAll(sets[j]);
        }
      }
      sets[i].add(a[i]);
    }

    Pair<Integer, Integer> pair = maxElement(sum);
    return Pair.build(pair.getRight(), newList(sets[pair.getRight()]));
  }

  public static void main(String[] args) {
    int[] a = { 1, 101, 2, 3, 100, 4, 5 };
    Pair<Integer, ImmutableList<Integer>> pair = maxSum(a);
    System.out.println(pair.getLeft());
    print(pair.getRight());
  }
}