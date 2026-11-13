package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Print the anagrams of a list of words together -- not a LeetCode problem; the grouping is
// LeetCode 49's, which GroupAnagrams49 carries.
//
// Input: a list of words. Returns: one string per group of two or more words that are
// anagrams of each other, the group's words joined by single spaces in the order they arrive.
// A word with no anagram in the list is left out. The groups come in any order.
//
// The one idea: give every word a KEY that is equal for exactly its anagrams -- its letters,
// sorted -- and group the words by key in a map, key -> the words with that key. Then read
// the groups out, skipping those of one word.
//
// Siblings -- the same grouping, read out differently:
//   GroupAnagrams49       -- LeetCode 49: every group, including those of one word, as lists
//   GroupAnagrams49Simple -- LeetCode 49 with a different key: the 26 letter counts
//   SortAnagramsMultimap  -- one list with each group's words next to each other
//   CountAnagramsMultimap -- for each word, how many other words are its anagrams

public final class GroupAnagramsMultimap {
  // Time Complexity: O(m * n log n) for m words of length up to n: each word's key is a sort
  // of its letters; reading the groups out touches every word once more
  // Space Complexity: O(m * n), the keys, the groups and the joined strings each hold every
  // word at most once

  public static List<String> group(List<String> list) {
    Map<String, List<String>> mm = asMap(list); // GROUP: key -> the words with that key
    return asList(mm); // RETURN
  }

  private static Map<String, List<String>> asMap(List<String> list) {
    Map<String, List<String>> mm = new HashMap<>();
    for (String str : list) { // WALK: word arrives
      String sorted = sort(str); // KEY: the word's letters, sorted
      mm.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str); // GROUP: word joins its key's list
    }
    return mm; // RETURN
  }

  private static List<String> asList(Map<String, List<String>> map) {
    List<String> lists = new ArrayList<>();
    for (String key : map.keySet()) { // READ: each group
      List<String> list = map.get(key);
      if (list.size() > 1) { // KEEP: only a word that has an anagram
        lists.add(String.join(" ", list)); // READ: the group as one line
      }
    }
    return lists; // RETURN
  }

  private static String sort(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars); // SORT: anagrams become the same letters in the same order
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    List<String> list = build();
    group(list).forEach(System.out::println);
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