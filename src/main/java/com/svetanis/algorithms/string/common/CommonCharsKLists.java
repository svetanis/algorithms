package com.svetanis.algorithms.string.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Characters common to all of k strings (GeeksforGeeks)
//
// Input: a list of strings. A character counts once per string however often it repeats
// inside it, and the list may hold the same string more than once.
// Returns: the characters that appear in every string, each once, in increasing order; an
// empty list when there are none or the list is empty.
//
// The one idea: keep a running set of candidates, the characters seen in every string so
// far. Start it with the characters of the first string; for each further string keep
// only the candidates that string also holds (retainAll). What survives the last string
// is in every string. A TreeSet makes the answer come out in increasing order.
//
// Time: O(L log c) -- every character of every string is added to a set once, L the total
// length and c the number of distinct characters; each retain step costs the size of the
// candidate set.
// Space: O(c) -- the candidate set and one set per string.
//
// Siblings: LeetCode 1002 (Find Common Characters) asks the multiset version: a character
// counts as often as the fewest times it appears in any string.

public final class CommonCharsKLists {

  public static List<Character> common(List<String> list) {
    Set<Character> candidates = new TreeSet<>();
    if (list.isEmpty()) {
      return new ArrayList<>(candidates);      // RETURN: no strings, nothing is common
    }
    candidates.addAll(charsOf(list.get(0)));   // START: every character of the first string
    for (String str : list) {
      candidates.retainAll(charsOf(str));      // KEEP only the characters this string holds too
    }
    return new ArrayList<>(candidates);        // RETURN: the characters in all of them
  }

  private static Set<Character> charsOf(String str) {
    Set<Character> chars = new TreeSet<>();
    for (char c : str.toCharArray()) {
      chars.add(c);                            // RECORD: a character once, however often it repeats
    }
    return chars;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("geeksforgeeks", "gemkstones", "acknowledges", "aguelikes");
    System.out.println(common(list));                        // [e, g, k, s]
    System.out.println(common(Arrays.asList("abc", "abc"))); // [a, b, c]
    System.out.println(common(Arrays.asList("abc", "xyz"))); // []
  }
}
