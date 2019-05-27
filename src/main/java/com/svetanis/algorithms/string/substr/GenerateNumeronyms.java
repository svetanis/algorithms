package com.svetanis.algorithms.string.substr;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a string word of length n, generate all possible numeronyms.
// A numeronym is a word where a number is used to form an abbreviation.

public final class GenerateNumeronyms {

  public static ImmutableList<String> numeronyms(String str) {
    int n = str.length();
    List<String> list = newArrayList();

    if(n <= 3) {
      return newList();
    }
    
    // first and last chars always included: len = 2 : n - 2
    for (int len = 2; len <= n - 2; len++) {
      for (int i = 1; i <= n - 1 - len; i++) {
        String ss = str.substring(0, i) + len + str.substring(i + len);
        list.add(ss);
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String str = "nailed";
    print(numeronyms(str));
  }
}

// n4d
// na3d
// n3ed
// n2led
// na2ed
// nai2d
