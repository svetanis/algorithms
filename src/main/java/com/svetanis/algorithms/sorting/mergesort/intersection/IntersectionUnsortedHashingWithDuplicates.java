package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

//["dog", "dog"] => 2
//["dog", "dog", "dog", "dog", "dog"] => 5

//Return: ["dog", "dog"]

public final class IntersectionUnsortedHashingWithDuplicates {

  public static ImmutableList<String> intersection(String[] a1, String[] a2) {
    // Time Complexity: O(n + m)

    List<String> list = newArrayList();
    Map<String, Integer> map1 = freqMap(a1);
    Map<String, Integer> map2 = freqMap(a2);
    for (String str : map1.keySet()) {
      if (map2.get(str) != null) {
        int freq = min(map1.get(str), map2.get(str));
        if (freq == 1) {
          list.add(str);
        } else {
          String[] common = new String[freq];
          fill(common, str);
          list.addAll(asList(common));
        }
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String[] a1 = { "dog", "dog" };
    String[] a2 = { "dog", "dog", "dog", "dog", "dog" };

    print(intersection(a1, a2));
  }
}