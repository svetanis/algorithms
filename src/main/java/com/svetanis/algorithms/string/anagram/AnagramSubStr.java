package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class AnagramSubStr {

  public static boolean areAnagrams(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    for (char c : str1.toCharArray()) {
      int index = str2.indexOf(c);
      if (index != -1) {
        String first = str2.substring(0, index);
        String second = str2.substring(index + 1);
        str2 = first + second;
      } else {
        return false;
      }
    }
    return str2.isEmpty();
  }

  public static void main(String[] args) {
    ImmutableList<Pair<String, String>> pairs = pairs();
    for (Pair<String, String> pair : pairs) {
      String w1 = pair.getLeft();
      String w2 = pair.getRight();
      System.out.println(areAnagrams(w1, w2));
    }
  }

  private static ImmutableList<Pair<String, String>> pairs() {
    List<Pair<String, String>> list = newArrayList();
    list.add(Pair.build("anagramm", "nagaramm")); // true
    list.add(Pair.build("aaca", "aca")); // false
    list.add(Pair.build("apple", "papel")); // true
    list.add(Pair.build("carrot", "tarroc")); // true
    list.add(Pair.build("hello", "llloh")); // false
    return copyOf(list);
  }
}
