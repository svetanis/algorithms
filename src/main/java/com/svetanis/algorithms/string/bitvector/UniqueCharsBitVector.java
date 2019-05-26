package com.svetanis.algorithms.string.bitvector;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.filter;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

public final class UniqueCharsBitVector {

  private static final int N = 256;

  public static ImmutableList<String> unique(Iterable<String> iterable) {
    return filter(iterable, UniquePredicate.INSTANCE);
  }

  private enum UniquePredicate implements Predicate<String> {
    INSTANCE;

    @Override
    public boolean apply(String input) {
      return isUnique(input);
    }

    public static boolean isUnique(String str) {
      // Time complexity: O(n)
      // Space complexity: O(1)

      int n = str.length();

      if (n > N) {
        return false;
      }

      int checker = 0;
      for (int i = 0; i < n; ++i) {
        int c = str.charAt(i) - 'a';
        // getBit: x & (1 << i)
        int bit = checker & (1 << c);
        if (bit > 0) {
          return false;
        }
        // setBit: x | (1 << i)
        checker |= (1 << c);
      }
      return true;
    }

  }

  public static void main(String[] args) {
    List<String> list = build();
    printLines(unique(list));
  }

  private static ImmutableList<String> build() {
    List<String> list = newArrayList();
    list.add("abcde");
    list.add("hello");
    list.add("apple");
    list.add("kite");
    list.add("padle");
    return newList(list);
  }
}