package com.svetanis.algorithms.backtracking.permutations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a positive integer number N. 
// The task is to generate all the binary strings of N bits. 

public final class GenerateBinaryStrImmutable {

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

    generate(n - 1, str + "0", lists);
    generate(n - 1, str + "1", lists);
  }

  public static void main(String[] args) {
    printLines(generate(2));
  }
}
