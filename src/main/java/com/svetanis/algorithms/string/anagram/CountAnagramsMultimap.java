package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static com.svetanis.java.base.collect.Maps.newMap;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Chars.sort;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

// Given a set of words find the number of anagram for each word in given set of words.

public final class CountAnagramsMultimap {

  public static ImmutableMap<String, Integer> count(List<String> words) {
    Multimap<String, String> multimap = asMultimap(words);
    Map<String, Integer> result = newLinkedHashMap();
    for (String word : words) {
      result.put(word, multimap.get(sort(word)).size() - 1);
    }
    return newMap(result);
  }

  private static ImmutableMultimap<String, String> asMultimap(List<String> list) {
    Multimap<String, String> multimap = ArrayListMultimap.create();
    for (String str : list) {
      String key = sort(str);
      multimap.put(key, str);
    }
    return newMultimap(multimap);
  }

  public static void main(String[] args) {
    List<String> list = build();
    print(count(list));
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
    return copyOf(list);
  }
}
