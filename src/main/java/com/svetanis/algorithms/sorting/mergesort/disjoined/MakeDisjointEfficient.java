package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import java.util.Set;

public final class MakeDisjointEfficient {

  public static int disjoint(int[] a1, int[] a2) {
    // Time Complexity: O(n + m)
    int m = a2.length;

    Set<Integer> set = newHashSet(toObject(a1));
    int count = 0;
    for (int i = 0; i < m; i++) {
      if (set.contains(a2[i])) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 20, 21, 22 };
    int[] a2 = { 22, 23, 24, 25, 20 };
    System.out.println(disjoint(a1, a2));
  }
}
