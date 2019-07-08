package com.svetanis.algorithms.string;

import static com.svetanis.java.base.Splitters.split;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public final class IndicesOfWordsInText {

  public static ImmutableMultimap<String, Integer> findWords(String txt, List<String> queries) {
    List<String> words = split(' ', txt);
    Multimap<String, Integer> mm = getMultimap(words);
    return doQueries(mm, queries);
  }

  private static ImmutableMultimap<String, Integer> doQueries(Multimap<String, Integer> mm, List<String> queries) {
    Multimap<String, Integer> multi = ArrayListMultimap.create();
    for (String str : queries) {
      if (mm.containsKey(str)) {
        multi.putAll(str, mm.get(str));
      } else {
        multi.put(str, -1);
      }
    }
    return newMultimap(multi);
  }

  private static ImmutableMultimap<String, Integer> getMultimap(List<String> list) {
    Multimap<String, Integer> mm = ArrayListMultimap.create();
    int index = 0;
    for (String word : list) {
      index += word.length() + 1;
      mm.put(word, index);
    }
    return newMultimap(mm);
  }

  public static void main(String[] args) {
    String txt = "you are very very smart";
    List<String> list = asList("you", "are", "very", "handsome");
    print(findWords(txt, list));
  }
}
