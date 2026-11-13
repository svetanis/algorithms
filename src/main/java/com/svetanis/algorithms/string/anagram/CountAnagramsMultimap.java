package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Given a set of words find the number of anagram for each word in given set of words.
//
// Not a LeetCode problem. Input: a list of words. Returns: a map, word -> how many OTHER
// words of the list are its anagrams, in the order the words first appear. A word that
// appears twice counts its copy as an anagram, and appears once in the map.
//
// The one idea: give every word a KEY that is equal for exactly its anagrams -- its letters,
// sorted -- and group the words by key. A word's group then holds the word and all its
// anagrams, so its count is the group's size minus one, the word itself.
//
// Siblings -- the same grouping by sorted key, read out differently:
//   GroupAnagrams49       -- LeetCode 49: returns the groups themselves
//   GroupAnagrams49Simple -- LeetCode 49 with a different key: the 26 letter counts
//   GroupAnagramsMultimap -- prints only the groups of two or more, each joined by spaces
//   SortAnagramsMultimap  -- returns one list with each group's words next to each other

public final class CountAnagramsMultimap {
  // Time Complexity: O(m * n log n) for m words of length up to n: each word's key is a sort
  // of its letters, made once to group and once to look the group up
  // Space Complexity: O(m * n), the keys, and the groups holding every word once

  public static Map<String, Integer> count(List<String> words) {
    Map<String, List<String>> groups = asMap(words); // GROUP: key -> the words with that key
    Map<String, Integer> result = new LinkedHashMap<>(); // RESULT: word -> count, in first-appearance order
    for (String word : words) { // WALK: word arrives
      result.put(word, groups.get(sort(word)).size() - 1); // COUNT: its group, minus the word itself
    }
    return result; // RETURN
  }

  private static Map<String, List<String>> asMap(List<String> list) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : list) { // WALK: word arrives
      String key = sort(str); // KEY: the word's letters, sorted
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str); // GROUP: word joins its key's list
    }
    return map; // RETURN
  }

  private static String sort(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars); // SORT: anagrams become the same letters in the same order
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    List<String> list = build();
    System.out.println(count(list));
  }

  private static List<String> build() {
    List<String> list = new ArrayList<>();
    list.add("geeksquiz");
    list.add("geeksforgeeks");
    list.add("abcd");
    list.add("forgeeksgeeks");
    list.add("zuiqkeegs");
    list.add("logarithmic");
    list.add("algorithmic");
    list.add("cat");
    list.add("dog");
    list.add("tac");
    list.add("god");
    list.add("act");
    list.add("gdo");
    list.add("book");
    list.add("table");
    return list;
  }
}
