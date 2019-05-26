package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class CountAnagrams {

  private static final int MAX = 256;

  public static int count(String text, String patt) {
    // Time complexity: O(n)

    int n = text.length();
    int m = patt.length();
    char[] txt = text.toCharArray();
    char[] pat = patt.toCharArray();

    int[] count = new int[MAX];

    for (int i = 0; i < m; i++) {
      count[pat[i]]++;
      count[txt[i]]--; // first window of text
    }

    int counter = 0;
    if (allZero(count)) {
      counter++;
    }

    for (int i = m; i < n; i++) {
      // add current char to current window
      count[txt[i]]--;

      // remove the first char of prev window
      count[txt[i - m]]++;

      // compare counts of current window
      // of text with counts of pattern[]
      if (allZero(count)) {
        counter++;
      }
    }
    return counter;
  }

  private static boolean allZero(int[] count) {
    for (int i = 0; i < MAX; i++) {
      if (count[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    ImmutableList<Pair<String, String>> pairs = pairs();
    for (Pair<String, String> pair : pairs) {
      System.out.println(count(pair.getLeft(), pair.getRight()));
    }
  }

  private static ImmutableList<Pair<String, String>> pairs() {
    List<Pair<String, String>> list = newArrayList();
    list.add(Pair.build("forxxorfxdorf", "for"));
    list.add(Pair.build("AAABABAA", "AABA"));
    return newList(list);
  }
}
