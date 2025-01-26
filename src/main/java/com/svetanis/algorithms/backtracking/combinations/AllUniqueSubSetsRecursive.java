package com.svetanis.algorithms.backtracking.combinations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Arrays.sort;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public final class AllUniqueSubSetsRecursive {

  // Time Complexity: O(n * 2^n)

  public static ImmutableList<String> generate(String str) {
    char[] in = str.toCharArray();
    sort(in);
    List<Character> out = newArrayList();
    List<String> list = newArrayList();
    subset(new String(in), out, 0, list);
    return newList(list);
  }

  private static void subset(String str, List<Character> out, int i, List<String> list) {
    int n = str.length();
    if (i >= n) {
      list.add(Joiner.on("").join(out));
      return;
    }

    // include current char
    out.add(str.charAt(i));
    subset(str, out, i + 1, list);

    // backtrack
    out.remove(out.size() - 1);

    // skip adjacent duplicates
    while (i < n - 1 && str.charAt(i) == str.charAt(i + 1)) {
      i++;
    }

    // exclude current char
    subset(str, out, i + 1, list);
  }

  public static void main(String[] args) {
    String str = "xxyz";
    printLines(generate(str));
  }
}
