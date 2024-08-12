package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.algorithms.sorting.mergesort.kwaymerge.KwayMerge.nextSmallest;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

/* 
Find common numbers in list of sorted arrays.

e.g.
array1: [ 1, 3, 5, 10, 20 ]
array2: [ 2, 4, 5, 10, 20]
array3: [ 2, 4, 10, 20]

common elements => 10 & 20

Constraints:
- Any number of arrays
- Each array could be of any size
 */

public final class IntersectionKSortedArrays {

  public static ImmutableList<Integer> merge(List<List<Integer>> lists) {
    int n = lists.size();
    Queue<Entry<Integer>> queue = init(lists);
    List<Integer> list = newArrayList();
    while (!queue.isEmpty()) {
      if (isUnival(queue, n)) {
        list.add(queue.peek().getValue());
        readNext(queue);
      } else {
        nextSmallest(queue);
      }
    }
    return newList(list);
  }

  private static <C extends Comparable<C>> Queue<Entry<C>> init(List<List<C>> lists) {
	  Queue<Entry<C>> queue = new PriorityQueue<>();
	  for (List<C> list : lists) {
      Iterator<C> iter = list.iterator();
      if (iter.hasNext()) {
        queue.offer(new Entry<>(iter.next(), iter));
      }
    }
    return queue;
  }


  public static <C extends Comparable<C>> boolean isUnival(Queue<Entry<C>> queue, int n) {
    List<C> list = transform(queue, e -> e.getValue());
    return list.size() == n && newHashSet(list).size() == 1;
  }

  private static <C extends Comparable<C>> Queue<Entry<C>> readNext(Queue<Entry<C>> queue) {
    int size = queue.size();
    while (size-- > 0) {
      nextSmallest(queue);
    }
    return queue;
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
