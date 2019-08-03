package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.newMap;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public final class LetterCombinations {

  private final Map<Character, String> map;

  LetterCombinations() {
    this.map = build();
  }

  public ImmutableList<String> combinations(String digits) {
    List<String> list = newArrayList();
    recursion(digits, 0, "", list);
    return newList(list);
  }

  private void recursion(String digits, int start, String str, List<String> list) {
    int n = digits.length();
    if (str.length() > digits.length()) {
      return;
    } else if (str.length() == n) {
      list.add(str);
      return;
    }
    for (int i = start; i < n; ++i) {
      String letters = map.get(digits.charAt(i));
      for (int j = 0; j < letters.length(); ++j) {
        recursion(digits, i + 1, str + letters.charAt(j), list);
      }
    }
  }

  private ImmutableMap<Character, String> build() {
    Map<Character, String> map = newHashMap();
    map.put('0', " ");
    map.put('1', "");
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
    return newMap(map);
  }

  public static void main(String[] args) {
    LetterCombinations lc = new LetterCombinations();
    List<String> combinations = lc.combinations("23");
    printLines(combinations);
  }
}
