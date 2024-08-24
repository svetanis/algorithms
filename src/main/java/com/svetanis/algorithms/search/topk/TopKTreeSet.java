package com.svetanis.algorithms.search.topk;

import static com.google.common.collect.Sets.newTreeSet;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.TreeSet;

import com.google.common.collect.ImmutableList;

public final class TopKTreeSet {

  public static ImmutableList<Integer> topK(int a[], int k) {
    // Time Complexity: O(n log k)

    TreeSet<Integer> ts = newTreeSet();
    for (int item : a) {
      if (ts.size() < k || item > ts.first()) {
        if (ts.size() == k) {
          ts.pollFirst(); // remove smallest 
        }
        ts.add(item);
      }
    }
    return newList(ts);
  }

  public static void main(String[] args) {
    int[] a = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
    System.out.println(topK(a, 4));
  }
}