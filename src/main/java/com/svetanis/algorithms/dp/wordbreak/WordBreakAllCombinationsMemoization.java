package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.Strings.trim;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public final class WordBreakAllCombinationsMemoization {

  public static ImmutableList<String> wb(String str, List<String> dict) {
    Map<Integer, List<String>> map = newHashMap();
    return newList(wb(str, dict, map, 0));
  }

  public static List<String> wb(String str, List<String> dict, Map<Integer, List<String>> map, int left) {
    int n = str.length();
    if (map.get(left) != null) {
      return map.get(left);
    }
    List<String> list = newArrayList();
    if (left == n) {
      list.add("");
    }
    for (int right = left + 1; right <= n; right++) {
      String ss = str.substring(left, right);
      if (dict.contains(ss)) {
        List<String> recursive = wb(str, dict, map, right);
        list.addAll(transform(recursive, s -> trim(Joiner.on(" ").join(ss, s))));
      }
    }
    map.put(left, list);
    return list;
  }

  public static void main(String[] args) {
    printLines(wb("iamsuperlady", dictionary()));
  }
}
