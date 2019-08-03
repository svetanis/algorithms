package com.svetanis.algorithms.recursive.permutation.generate;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// Given a positive integer number N. 
// The task is to generate all the binary strings of N bits. 

public final class GenerateBinaryStrLenNBacktrack {

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

    list.add(0);
    generate(n, list, lists);
    list.remove(list.size() - 1);

    list.add(1);
    generate(n, list, lists);
    list.remove(list.size() - 1);
  }

  public static void main(String[] args) {
    printLines(generate(2));
  }
}
