package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.List;
import java.util.TreeSet;

// The k Largest Numbers, with a TreeSet of Size k
//
// Takes an array of n numbers and k, 1 <= k <= n. Returns the k largest, each copy of a
// repeated number counted on its own, in ascending order.
//
// The same bounded walk as the min-heap sibling, with a TreeSet in the heap's place: first()
// is the smallest of the k kept so far, the one to throw out when something bigger arrives. A
// set keeps one copy of each value, so every number goes in tagged with its index; two copies
// of 10 are then two different entries, and both can be among the k largest.
//
// Siblings -- four ways to take the k largest or smallest in this package:
//   TopKLargestMinHeap      a min-heap capped at k; O(n log k), heap order
//   TopKLargestTreeSet      the same cap with a TreeSet of (value, index)          (this file)
//   TopKLargestQuickSelect  partition until the kth largest is in place; O(n) average,
//                           reorders the input, returns its first k positions unordered
//   TopKSmallestHeapAll     the k SMALLEST: every number into a min-heap, then k polls;
//                           O(n log n), ascending

public final class TopKLargestTreeSet {

  public static List<Integer> topK(int a[], int k) {
    // Time Complexity: O(n log k) -- each number costs at most one removal and one insert in
    // a tree of k entries
    // Space Complexity: O(k), the tree

    // ORDER: by value, then by index, so equal values are separate entries
    TreeSet<Item> ts = new TreeSet<>(comparingInt(Item::value).thenComparingInt(Item::index));
    for (int i = 0; i < a.length; i++) {
      if (ts.size() < k || a[i] > ts.first().value()) { // FILL, or BEATS the smallest kept
        if (ts.size() == k) {
          ts.pollFirst(); // EVICT the smallest kept
        }
        ts.add(new Item(a[i], i)); // KEEP: a new entry even when the value is already in
      }
    }
    return ts.stream().map(Item::value).toList(); // REPORT ascending
  }

  // one number of the input: its value and where it stood
  private record Item(int value, int index) {
  }

  public static void main(String[] args) {
    int[] a = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
    System.out.println(topK(a, 4)); // [8, 10, 10, 128]
  }
}