package com.svetanis.algorithms.dp.sum.max.subseq;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Arrays.maxElement;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.System.arraycopy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.svetanis.java.base.Pair;

public class MaxSumIncreasingSubSeqMultimap {

  public static Pair<Integer, ImmutableList<Integer>> maxSum(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] sum = new int[n];
    arraycopy(a, 0, sum, 0, n);

    Multimap<Integer, Integer> mm = LinkedHashMultimap.create();
    mm.put(0, a[0]);
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i] && sum[i] < sum[j] + a[i]) {
          sum[i] = sum[j] + a[i];
          mm.putAll(i, mm.get(j));
        }
      }
      mm.put(i, a[i]);
    }
    Pair<Integer, Integer> pair = maxElement(sum);
    return Pair.build(pair.getRight(), newList(mm.get(pair.getRight())));
  }

  public static void main(String[] args) {
    int[] a = { 1, 101, 2, 3, 100, 4, 5 };
    Pair<Integer, ImmutableList<Integer>> pair = maxSum(a);
    System.out.println(pair.getLeft());
    print(pair.getRight());
  }
}