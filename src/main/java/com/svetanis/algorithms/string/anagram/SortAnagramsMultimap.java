package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Sort a list of words so that anagrams are next to each other -- not a LeetCode problem.
//
// Input: a list of words. Returns: the same words, every one kept, reordered so that words
// that are anagrams of each other stand together. Inside a group the words keep the order
// they arrived in; the groups themselves come in any order.
//
// The one idea: give every word a KEY that is equal for exactly its anagrams -- its letters,
// sorted -- and group the words by key in a map, key -> the words with that key. Then lay
// the groups end to end. Nothing is compared between groups, so "sort" here means "gather",
// not "put in alphabetical order".
//
// Siblings -- the same grouping by sorted key, read out differently:
//   GroupAnagrams49       -- LeetCode 49: returns the groups themselves, as lists
//   GroupAnagrams49Simple -- LeetCode 49 with a different key: the 26 letter counts
//   GroupAnagramsMultimap -- only the groups of two or more, each joined by spaces
//   CountAnagramsMultimap -- for each word, how many other words are its anagrams

public final class SortAnagramsMultimap {
  // Time Complexity: O(m * n log n) for m words of length up to n: each word's key is a sort
  // of its letters; laying the groups out touches every word once more
  // Space Complexity: O(m * n), the keys and the groups hold every word once

  public static List<String> sortAnagrams(List<String> list) {
    Map<String, List<String>> mm = asMap(list); // GROUP: key -> the words with that key
    return asList(mm); // RETURN
  }

  private static Map<String, List<String>> asMap(List<String> list) {
    Map<String, List<String>> mm = new HashMap<>();
    for (String str : list) { // WALK: word arrives
      String key = sort(str); // KEY: the word's letters, sorted
      mm.computeIfAbsent(key, k -> new ArrayList<>()).add(str); // GROUP: word joins its key's list
    }
    return mm; // RETURN
  }

  private static List<String> asList(Map<String, List<String>> map) {
    List<String> list = new LinkedList<>();
    for (String key : map.keySet()) { // READ: each group
      list.addAll(map.get(key)); // READ: the group's words, end to end
    }
    return list; // RETURN
  }

  private static String sort(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars); // SORT: anagrams become the same letters in the same order
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    List<String> list = build();
    sortAnagrams(list).forEach(System.out::println);
  }

  private static List<String> build() {
    List<String> list = new ArrayList<>();
    list.add("apple");
    list.add("banana");
    list.add("carrot");
    list.add("ele");
    list.add("duck");
    list.add("papel");
    list.add("tarroc");
    list.add("cudk");
    list.add("eel");
    list.add("lee");
    return list;
  }
}
