package com.svetanis.algorithms.sorting.mergesort.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.sorting.mergesort.intervals.LeftComparator.left;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.Stack;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class MergeOverlappingIntervalsStack {

  public static ImmutableList<Pair<Integer, Integer>> merge(List<Pair<Integer, Integer>> list) {
    List<Pair<Integer, Integer>> sorted = sort(list, left());
    Stack<Pair<Integer, Integer>> stack = new Stack<>();
    stack.push(sorted.get(0));
    for (int i = 1; i < sorted.size(); ++i) {
      Pair<Integer, Integer> top = stack.peek();
      int left = sorted.get(i).getLeft();
      int right = sorted.get(i).getRight();
      // if current interval is not overlapping
      // with stack top push it to stack
      if (top.getRight() < left) {
        stack.push(sorted.get(i));
      } else if (top.getRight() < right) {
        // update the ending time of top
        Pair<Integer, Integer> pair = Pair.build(top.getLeft(), right);
        stack.pop();
        stack.push(pair);
      }
    }
    return newList(stack);
  }

  public static void main(String[] args) {
    List<Pair<Integer, Integer>> list1 = newArrayList();
    list1.add(Pair.build(6, 8));
    list1.add(Pair.build(1, 9));
    list1.add(Pair.build(2, 4));
    list1.add(Pair.build(4, 7));
    print(merge(list1));

    List<Pair<Integer, Integer>> list2 = newArrayList();
    list2.add(Pair.build(6, 8));
    list2.add(Pair.build(1, 3));
    list2.add(Pair.build(2, 4));
    list2.add(Pair.build(4, 7));
    print(merge(list2));

    List<Pair<Integer, Integer>> list3 = newArrayList();
    list3.add(Pair.build(1, 3));
    list3.add(Pair.build(7, 9));
    list3.add(Pair.build(4, 6));
    list3.add(Pair.build(10, 13));
    print(merge(list3));
  }
}