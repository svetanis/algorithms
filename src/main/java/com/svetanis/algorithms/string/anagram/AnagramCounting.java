package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class AnagramCounting {

  private final static int N = 256;

  public static boolean areAnagrams(String str1, String str2) {
    // Time complexity: O(n)

    if (str1.length() != str2.length()) {
      return false;
    }

    int[] count = new int[N];
    for (int i = 0; i < str1.length(); ++i) {
      count[str1.charAt(i)]++;
      count[str2.charAt(i)]--;
    }

    for (int i = 0; i < N; ++i) {
      if (count[i] != 0) {
        return false;
      }
    }
    return true;
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
