package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class WordBreakRecursive {

  // the parameter for contains is str.substr(0, i)
  // str.substr(0, i) which is prefix (of input string) of length i.
  // we first check whether current prefix is in dictionary
  // then we recursively check for remaining string str.substring(i)
  // which is suffix of length n - i

  public static boolean isWordBreak(String str, List<String> dict) {
    int n = str.length();

    // base case
    if (n == 0) {
      return true;
    }

    // try all prefixes of lengths from 1 to n
    for (int i = 1; i <= n; ++i) {
      boolean in = dict.contains(str.substring(0, i));
      if (in && isWordBreak(str.substring(i), dict)) {
        return true;
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
