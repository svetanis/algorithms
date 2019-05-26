package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Chars.sort;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public final class SortAnagramsMultimap {

  public static ImmutableList<String> sortAnagrams(List<String> list) {
    Multimap<String, String> mm = asMultimap(list);
    return asList(mm);
  }

  private static ImmutableMultimap<String, String> asMultimap(List<String> list) {
    Multimap<String, String> mm = ArrayListMultimap.create();
    for (String str : sort(list)) {
      String key = sort(str);
      mm.put(key, str);
    }
    return newMultimap(mm);
  }

  private static ImmutableList<String> asList(Multimap<String, String> multimap) {
    List<String> list = newLinkedList();
    for (String key : multimap.keySet()) {
      list.addAll(multimap.get(key));
    }
    return newList(list);
  }

  public static void main(String[] args) {
    List<String> list = build();
    printLines(sortAnagrams(list));
  }

  private static ImmutableList<String> build() {
    List<String> list = newArrayList();
    list.add("apple");
    list.add("banana");
    list.add("carrot");
    list.add("ele");
    list.add("duck");
    list.add("papel");
    list.add("tarroc");
    list.add("cudk");
    list.add("eel");
    list.add("lee");
    return newList(list);
  }
}
