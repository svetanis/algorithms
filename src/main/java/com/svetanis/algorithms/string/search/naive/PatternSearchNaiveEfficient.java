package com.svetanis.algorithms.string.search.naive;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class PatternSearchNaiveEfficient {

  public static ImmutableList<Integer> search(String text, String patt) {

    int i = 0, j;
    int n = text.length();
    int m = patt.length();

    List<Integer> list = newArrayList();
    while (i <= n - m) {
      for (j = 0; j < m; ++j) {
        if (text.charAt(i + j) != patt.charAt(j)) {
          break;
        }
      }

      // if pat[0 .. M - 1] == txt[i, i + 1, .... i + m - 1]
      if (j == m) {
        list.add(i);
        i += m;
      } else if (j == 0) {
        i += 1;
      } else {
        i += j; // slide the pattern by j
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    ImmutableList<Pair<String, String>> pairs = build();
    for (Pair<String, String> pair : pairs) {
      print(search(pair.getLeft(), pair.getRight()));
    }
  }

  private static ImmutableList<Pair<String, String>> build() {
    List<Pair<String, String>> list = newArrayList();
    list.add(Pair.build("AABAACAADAABAAABAA", "AABA"));
    list.add(Pair.build("ABCEABCDABCEABCD", "ABCD"));
    return copyOf(list);
  }
}
