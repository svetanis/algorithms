package com.svetanis.algorithms.recursive;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.lang.String.valueOf;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters, 
// generate all binary strings that can be formed by replacing each wildcard character by ‘0’ or ‘1’.

public final class AllBinaryStringsFromGivenPattern {

  public static ImmutableList<String> generate(String str) {
    List<String> list = newArrayList();
    generate(str.toCharArray(), 0, list);
    return newList(list);
  }

  public static void generate(char[] chars, int index, List<String> list) {
    if (index == chars.length) {
      list.add(valueOf(chars));
      return;
    }

    if (chars[index] == '?') {

      chars[index] = '0';
      generate(chars, index + 1, list);

      chars[index] = '1';
      generate(chars, index + 1, list);

      chars[index] = '?'; // backtrack
      
    } else {
      generate(chars, index + 1, list);
    }
  }

  public static void main(String[] args) {
    String str = "1??0?101";
    printLines(generate(str));
  }
}