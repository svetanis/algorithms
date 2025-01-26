package com.svetanis.algorithms.backtracking.combinations;

import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.Set;

import com.google.common.collect.ImmutableList;

public final class AllUniqueSubSetsHashSet {

  // Time Complexity: O(n * 2^n)

  public static ImmutableList<String> generate(String str) {
    Set<String> set = newHashSet();
    subset(str, set);
    return newList(set);
  }

  private static void subset(String str, Set<String> set) {
    if (str.length() == 0) {
      return;
    }

    if (!set.contains(str)) {
      set.add(str);
      for (int i = 0; i < str.length(); i++) {
        String substr = str.substring(0, i) + str.substring(i + 1);
        subset(substr, set);
      }
    }
  }

  public static void main(String[] args) {
    String str = "xxyz";
    printLines(generate(str));
  }
}
