package com.svetanis.algorithms.string.common;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.filterValues;
import static com.google.common.collect.Multimaps.invertFrom;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

// Given n strings, find the common characters in all the strings. 

public final class CommonCharsKLists {

  public static ImmutableList<Character> common(List<String> list) {
    Multimap<String, Character> mm = asMultimap(list);
    Multimap<Character, String> inverted = invertFrom(mm, HashMultimap.create());
    return newList(filterValues(inverted.asMap(), v -> newList(v).size() == list.size()).keySet());
  }

  private static ImmutableMultimap<String, Character> asMultimap(List<String> list) {
    Multimap<String, Character> mm = ArrayListMultimap.create();
    for (String str : list) {
      mm.putAll(str, asList(toObject(str.toCharArray())));
    }
    return newMultimap(mm);
  }

  public static void main(String[] args) {
    List<String> list = init();
    print(common(list));
  }

  private static ImmutableList<String> init() {
    List<String> list = newArrayList();
    list.add("geeksforgeeks");
    list.add("gemkstones");
    list.add("acknowledges");
    list.add("aguelikes");
    return newList(list);
  }
}
