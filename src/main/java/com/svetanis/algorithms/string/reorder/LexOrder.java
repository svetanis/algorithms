package com.svetanis.algorithms.string.reorder;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.Splitters.checkedSplit;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

public final class LexOrder {

  public static ImmutableList<String> order(Iterable<String> iterable) {
    Multimap<String, String> mm = asMultimap(iterable);
    List<String> list = newArrayList();
    for (String key : mm.keySet()) {
      List<String> values = newList(mm.get(key));
      String val = key + ":" + values.size() + "," + values.get(values.size() - 1);
      list.add(val);
    }
    return newList(list);
  }

  private static ImmutableMultimap<String, String> asMultimap(Iterable<String> list) {
    Multimap<String, String> mm = TreeMultimap.create();
    for (String str : list) {
      Iterator<String> iter = checkedSplit(' ', str, 2).iterator();
      String key = iter.next();
      String val = iter.next();
      mm.put(key, val);
    }
    return newMultimap(mm);
  }

  public static void main(String[] args) {
    List<String> list = newArrayList("mark zuckerberg", "tim cook", "mark twain");
    printLines(order(list));
  }
}
