package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class WordBreakDynamic {

  public static boolean isWordBreak(String str, List<String> dict) {

    int n = str.length();

    if (n == 0) {
      return true;
    }

    // create the DP table to
    // store results of subproblems.
    // the value table[i] will be true
    // if str[0 ... i - 1] can be
    // segmented into dictionary words,
    // otherwise false
    boolean[] table = new boolean[n + 1];
    for (int i = 1; i <= n; ++i) {
      // if table[i] is false,
      // then check if current
      // prefix can make it true
      // current prefix is str.substring(0, i)
      if (!table[i] && dict.contains(str.substring(0, i))) {
        table[i] = true;
      }

      // table[i] is true, then check for all
      // substring starting from (i + 1) char
      // and store their results
      if (table[i]) {
        // if we reached the last prefix
        if (i == n) {
          return true;
        }

        for (int j = i + 1; j <= n; ++j) {
          // update table[j] if it is false
          // and can be updated
          // note the parameter passed to
          // contains() is substring starting
          // from index 'i' and length j - i
          if (table[j] == false && dict.contains(str.substring(i, j))) {
            table[j] = true;
          }

          // if we reached the last character
          if (j == n && table[j] == true) {
            return true;
          }
        }
      }
    }

    // if we have tried all prefixes
    // and none of them worked
    return false;
  }

  public static void main(String[] args) {
    List<String> dict = dictionary();
    System.out.println(isWordBreak("ilikesamsung", dict));
    System.out.println(isWordBreak("iiiiiiii", dict));
    System.out.println(isWordBreak("", dict));
    System.out.println(isWordBreak("ilikelikeimangoiii", dict));
    System.out.println(isWordBreak("samsungandmango", dict));
    System.out.println(isWordBreak("samsungandmangok", dict));
  }

  private static ImmutableList<String> dictionary() {
    List<String> dict = newArrayList();
    dict.add("mobile");
    dict.add("samsung");
    dict.add("sam");
    dict.add("sung");
    dict.add("man");
    dict.add("mango");
    dict.add("icecream");
    dict.add("and");
    dict.add("go");
    dict.add("i");
    dict.add("like");
    dict.add("ice");
    dict.add("cream");
    return newList(dict);
  }
}
