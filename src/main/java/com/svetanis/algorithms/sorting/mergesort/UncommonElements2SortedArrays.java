package com.svetanis.algorithms.sorting.mergesort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class UncommonElements2SortedArrays {

  public static ImmutableList<Integer> uncommon(List<Integer> list1, List<Integer> list2) {
    int n = list1.size();
    int m = list2.size();
    int i = 0;
    int j = 0;
    
    List<Integer> list = newArrayList();
    while (i < n && j < m) {
      if (list1.get(i) < list2.get(j)) {
        list.add(list1.get(i++));
      } else if (list2.get(j) < list1.get(i)) {
        list.add(list2.get(j++));
      } else {
        i++;
        j++;
      }
    }

    while (i < n) {
      list.add(list1.get(i++));
    }
    while (j < m) {
      list.add(list2.get(j++));
    }
    return newList(list);
  }

  public static void main(String[] args) {
    List<Integer> list1 = newArrayList(10, 20, 30);
    List<Integer> list2 = newArrayList(20, 25, 30, 40, 50);
    print(uncommon(list1, list2));
  }
}
