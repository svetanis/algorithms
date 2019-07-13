package com.svetanis.algorithms.string.generate;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// Print all possible decimal strings of length n.

public final class GenerateDecimalStrLenNBacktrack {

  public static ImmutableList<String> generate(int n) {
    List<String> lists = newArrayList();
    List<Integer> list = newArrayList();
    generate(n, list, lists);
    return newList(lists);
  }

  private static void generate(int n, List<Integer> list, List<String> lists) {
    if (list.size() == n) {
      lists.add(Joiner.on("").join(list));
      return;
    }

    for (int i = 0; i <= n; i++) {
      list.add(i);
      generate(n, list, lists);
      list.remove(list.size() - 1);
    }
  }

  public static void main(String[] args) {
    printLines(generate(3));
  }
}
