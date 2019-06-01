package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.sorting.mergesort.kwaymerge.IntersectionKSortedArrays.isUnival;
import static com.svetanis.algorithms.sorting.mergesort.kwaymerge.KwayMerge.init;
import static com.svetanis.algorithms.sorting.mergesort.kwaymerge.KwayMerge.nextSmallest;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/* 
Find smallest common number in list of sorted arrays.

e.g.
array1: [ 1, 3, 5, 10, 20 ]
array2: [ 2, 4, 5, 10, 20]
array3: [ 2, 4, 10, 20]

common elements => 10 & 20
result => 10

Constraints:
- Any number of arrays
- Each array could be of any size
 */

public final class SmallestCommonElementKSortedArrays {

  public static Optional<Integer> merge(List<List<Integer>> lists) {
    int n = lists.size();
    Queue<Entry<Integer>> queue = new PriorityQueue<>();
    init(queue, lists);
    while (!queue.isEmpty()) {
      if (isUnival(queue, n)) {
        return of(queue.peek().getValue());
      }
      nextSmallest(queue);
    }
    return absent();
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = build();
    System.out.println(merge(lists));
  }

  private static ImmutableList<List<Integer>> build() {
    List<List<Integer>> lists = newArrayList();
    lists.add(newArrayList(1, 3, 5, 10, 20));
    lists.add(newArrayList(2, 4, 5, 10, 10, 20));
    lists.add(newArrayList(2, 4, 10));
    return newList(lists);
  }

}
