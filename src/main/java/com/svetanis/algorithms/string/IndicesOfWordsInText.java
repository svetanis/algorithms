package com.svetanis.algorithms.string;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Indices of words in a text (not a LeetCode problem; no LeetCode problem asks for this)
//
// Input: txt -- words separated by single spaces; queries -- a list of words. Returns, for each
// query, every position in txt where that word starts, counted in characters from 0, or [-1] if
// it never appears. A query matches a whole word only: "are" does not match inside "careful".
// The result is a map from query word to its list of positions, ordered by word. A word asked
// twice appears once.
//
// The one idea: one entry of the map holds one word and the list of places it starts. One pass
// over the words fills it. A word's position is the sum of the lengths of the words before it
// plus one space after each, so the position is recorded first and only then moved past the word
// and its space. Each query is then one lookup.
//
// Time: O(length of txt + number of queries) -- one pass to build, one lookup each.
// Space: O(number of words) -- one stored position per word.

public final class IndicesOfWordsInText {

  public static Map<String, List<Integer>> findWords(String txt, List<String> queries) {
    List<String> words = asList(txt.split(" "));
    Map<String, List<Integer>> positions = positions(words);
    return doQueries(positions, queries);
  }

  private static Map<String, List<Integer>> doQueries(Map<String, List<Integer>> positions, List<String> queries) {
    Map<String, List<Integer>> result = new TreeMap<>();
    for (String str : queries) { // WALK: one lookup per query
      if (positions.containsKey(str)) {
        result.put(str, positions.get(str)); // FOUND: all the starts of the word
      } else {
        result.put(str, asList(-1)); // MISSING: -1
      }
    }
    return result;
  }

  private static Map<String, List<Integer>> positions(List<String> list) {
    Map<String, List<Integer>> map = new HashMap<>();
    int index = 0; // where the current word starts
    for (String word : list) {
      map.computeIfAbsent(word, w -> new ArrayList<>()).add(index); // RECORD: the start of this word, before moving past it
      index += word.length() + 1; // NEXT: past the word and the one space after it
    }
    return map;
  }

  public static void main(String[] args) {
    String txt = "you are very very smart";
    List<String> list = asList("you", "are", "very", "handsome");
    Map<String, List<Integer>> result = findWords(txt, list);
    for (Map.Entry<String, List<Integer>> e : result.entrySet()) {
      System.out.printf("%s: %s%n", e.getKey(), e.getValue()); // PRINT: one line per word
    }
    // are: [4]
    // handsome: [-1]
    // very: [8, 13]
    // you: [0]
  }
}
