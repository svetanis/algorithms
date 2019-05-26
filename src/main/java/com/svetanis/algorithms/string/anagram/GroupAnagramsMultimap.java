package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Chars.sort;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public final class GroupAnagramsMultimap {

  public static List<String> group(List<String> list) {
    ImmutableMultimap<String, String> mm = asMultimap(list);
    return asList(mm);
  }

  private static ImmutableMultimap<String, String> asMultimap(List<String> list) {
    Multimap<String, String> mm = ArrayListMultimap.create();
    for (String str : list) {
      String sorted = sort(str);
      mm.put(sorted, str);
    }
    return newMultimap(mm);
  }

  private static List<String> asList(Multimap<String, String> multimap) {
    List<String> lists = newArrayList();
    for (String key : multimap.keySet()) {
      List<String> list = newList(multimap.get(key));
      if (list.size() > 1) {
        lists.add(Joiner.on(' ').join(list));
      }
    }
    return newList(lists);
  }

  public static void main(String[] args) {
    List<String> list = build();
    printLines(group(list));
  }

  private static ImmutableList<String> build() {
    List<String> list = newArrayList();
    list.add("geeksquiz");
    list.add("geeksforgeeks");
    list.add("abcd");
    list.add("forgeeksgeeks");
    list.add("zuiqkeegs");
    list.add("logarithmic");
    list.add("algorithmic");
    list.add("cat");
    list.add("dog");
    list.add("tac");
    list.add("god");
    list.add("act");
    list.add("gdo");
    list.add("book");
    list.add("table");
    return newList(list);
  }
}