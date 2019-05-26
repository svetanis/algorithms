package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.sort;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class AnagramSorting {

  public static boolean areAnagrams(String str1, String str2) {
    // Time complexity: O(n log n)

    if (str1.length() != str2.length()) {
      return false;
    }

    char[] chars1 = str1.toCharArray();
    char[] chars2 = str2.toCharArray();

    sort(chars1);
    sort(chars2);

    return Arrays.equals(chars1, chars2);
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
