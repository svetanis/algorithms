package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;

import java.util.List;

public final class WordBreakCountRecursive {

  public static int wbc(String str, List<String> dict) {
    int n = str.length();
    if (n == 0) {
      return 1;
    }
    int count = 0;
    for (int i = 1; i <= n; ++i) {
      if (dict.contains(str.substring(0, i))) {
        count += wbc(str.substring(i), dict);
      }
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(wbc("iamsuperlady", dictionary()));
  }
}
