package com.svetanis.algorithms.sorting.kwaymerge.iterator;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// merge n sorted arrays such that resultant array is also sorted

public final class KwayMerge {
	// Time Complexity: O(n log k)
	// Space Complexity: O(k)

  public static <C extends Comparable<C>> ImmutableList<C> merge(List<List<C>> lists) {
    Queue<Entry<C>> queue = init(lists);
    return merge(queue);
  }
  
  public static <C extends Comparable<C>> ImmutableList<C> merge(Queue<Entry<C>> queue){
    List<C> list = newArrayList();
    while (!queue.isEmpty()) {
      list.add(nextSmallest(queue));
    }
    return newList(list);
  }

  public static <C extends Comparable<C>> C nextSmallest(Queue<Entry<C>> queue) {
    Entry<C> entry = queue.poll();
    C value = entry.getValue();
    if (entry.readNext()) {
      queue.offer(entry);
    }
    return value;
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

  private static ImmutableList<List<Integer>> build() {
    List<List<Integer>> lists = newArrayList();
    lists.add(newArrayList(1, 3, 5, 7, 9));
    lists.add(newArrayList(2, 4, 6, 8, 10));
    lists.add(newArrayList(0, 1, 2, 3, 4, 5));
    return newList(lists); 
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = build();
    print(merge(lists)); // 0 1 1 2 2 3 3 4 4 5 5 6 7 8 9 10 
  }
}
