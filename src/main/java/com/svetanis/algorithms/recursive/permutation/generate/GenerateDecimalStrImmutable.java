package com.svetanis.algorithms.recursive.permutation.generate;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a positive integer number N. 
// The task is to generate all the binary strings of N bits. 

public final class GenerateDecimalStrImmutable {

  public static ImmutableList<String> generate(int n) {
    List<String> list = newArrayList();
    generate(n, "", list);
    return newList(list);
  }

  private static void generate(int n, String str, List<String> lists) {
    if (n == 0) {
      lists.add(str);
      return;
    }
    for(int i = 0; i <= 9; i++) {
      generate(n - 1, str + i, lists);
    }
  }

  public static void main(String[] args) {
    printLines(generate(3));
  }
}
