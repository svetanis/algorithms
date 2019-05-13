package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.Strings.trim;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;

public final class WordBreakAllCombinationsBottomUpMultimap {

  public static ImmutableList<String> wb(String str, List<String> dict) {
    int n = str.length();
    Multimap<Integer, String> mm = ArrayListMultimap.create();
    mm.put(0, "");
    for (int i = 1; i <= n; i++) {
      List<String> list = newArrayList();
      for (int j = 0; j < i; j++) {
        String ss = str.substring(j, i);
        if (dict.contains(ss)) {
          list.addAll(transform(mm.get(j), s -> trim(Joiner.on(" ").join(s, ss))));
        }
      }
      mm.putAll(i, list);
    }
    return newList(mm.get(n));
  }

  public static void main(String[] args) {
    printLines(wb("iamsuperlady", dictionary()));
  }
}
