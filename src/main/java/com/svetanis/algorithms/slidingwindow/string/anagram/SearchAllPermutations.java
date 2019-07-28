package com.svetanis.algorithms.slidingwindow.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class SearchAllPermutations {

  private static final int MAX = 256;

  public static ImmutableList<Integer> search(String text, String patt) {
    // Time complexity: O(n)

    int n = text.length();
    int m = patt.length();
    char[] txt = text.toCharArray();
    char[] pat = patt.toCharArray();

    char[] countP = new char[MAX]; // store count of all chars of pattern
    char[] countT = new char[MAX]; // store count of current window of text

    for (int i = 0; i < m; i++) {
      countP[pat[i]]++;
      countT[txt[i]]++; // first window of text
    }

    List<Integer> list = newArrayList();
    for (int i = m; i < n; i++) {
      // compare counts of current window
      // of text with counts of pattern[]
      if (Arrays.equals(countT, countP)) {
        list.add(i - m);
      }
      // add current char to current window
      countT[txt[i]]++;

      // remove the first char of prev window
      countT[txt[i - m]]--;
    }
    // check for the last window in text
    if (Arrays.equals(countT, countP)) {
      list.add(n - m);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    ImmutableList<Pair<String, String>> pairs = pairs();
    for (Pair<String, String> pair : pairs) {
      print(search(pair.getLeft(), pair.getRight()));
    }
  }

  private static ImmutableList<Pair<String, String>> pairs() {
    List<Pair<String, String>> list = newArrayList();
    list.add(Pair.build("BACDGABCDA", "ABCD"));
    list.add(Pair.build("AAABABAA", "AABA"));
    return newList(list);
  }
}
