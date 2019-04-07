package com.svetanis.algorithms.sorting.mergesort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;

public final class KwayMerge {

  public static <C extends Comparable<C>> ImmutableList<C> merge(List<List<C>> lists) {
    // Time Complexity: O(n log k)
    
    Queue<Entry<C>> queue = init(lists);

    List<C> list = newArrayList();
    while (!queue.isEmpty()) {
      Entry<C> entry = queue.poll();
      list.add(entry.getValue());
      if (entry.readNext()) {
        queue.offer(entry);
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

  private static ImmutableList<List<Integer>> build() {
    List<List<Integer>> lists = newArrayList();
    lists.add(newArrayList(1, 3, 5, 7, 9));
    lists.add(newArrayList(2, 4, 6, 8, 10));
    lists.add(newArrayList(0, 1, 2, 3, 4, 5));
    return newList(lists);
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = build();
    print(merge(lists));
  }

  private static class Entry<C extends Comparable<C>> implements Comparable<Entry<C>> {

    private C value;
    private Iterator<C> iter;

    public Entry(C value, Iterator<C> iter) {
      this.value = value;
      this.iter = iter;
    }

    public C getValue() {
      return this.value;
    }

    public boolean readNext() {
      if (iter.hasNext()) {
        value = iter.next();
        return true;
      } else {
        return false;
      }
    }

    @Override
    public int compareTo(Entry<C> other) {
      ComparisonChain chain = ComparisonChain.start();
      chain = chain.compare(value, other.value);
      return chain.result();
    }

    @Override
    public String toString() {
      return value + "";
    }
  }
}