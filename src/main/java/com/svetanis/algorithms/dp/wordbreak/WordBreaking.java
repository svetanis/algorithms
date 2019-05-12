package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class WordBreaking {

  public static ImmutableList<String> wordBreak(String str, List<String> dict) {
    int n = str.length();

    // T[i] stores the length of the last
    // string which is composed of s(0, i)
    int[] dp = new int[n];
    for (int i = 0; i < n; ++i) {
      // set T[i] if str(0, i) is a valid word
      if (dict.contains(str.substring(0, i + 1))) {
        dp[i] = i + 1;
      }
      // set T[i] if T[j] != 0 and
      // s(j + 1, i) is a valid word
      for (int j = 0; j < i && dp[i] == 0; ++j) {
        String substr = str.substring(j + 1, i + 1);
        if (dp[j] != 0 && dict.contains(substr)) {
          dp[i] = i - j;
        }
      }
    }
    // string can be assembled by valid words
    List<String> list = newArrayList();
    for (int i = 0; i < n; ++i) {
      if (dp[i] != 0) {
        list.add(str.substring(i - dp[i] + 1, i + 1));
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String str = "kickstartisawesome";
    List<String> dict = dictionary();
    print(wordBreak(str, dict));
  }

  private static ImmutableList<String> dictionary() {
    List<String> dict = newArrayList();
    dict.add("kick");
    dict.add("start");
    dict.add("kickstart");
    dict.add("is");
    dict.add("awe");
    dict.add("some");
    dict.add("awesome");
    return newList(dict);
  }

  // kick start is awe some
  // kick start is awesome
  // kickstart is awe some
  // kickstart is awesome

}
