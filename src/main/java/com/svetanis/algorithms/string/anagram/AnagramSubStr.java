package com.svetanis.algorithms.string.anagram;

// Valid anagram, by crossing characters off -- not a LeetCode file; the question is
// LeetCode 242, which AnagramCounting carries.
//
// Input: two strings str1 and str2. Returns: true when str2 is an anagram of str1 -- the same
// characters, each the same number of times, in any order. Two empty strings are anagrams.
//
// The one idea: take the characters of str1 one at a time and cross each off str2 -- find
// one copy of it and cut it out. A character with no copy left means str2 holds fewer of it,
// so not an anagram. With equal lengths, if every character of str1 is crossed off, str2 is
// used up exactly.
//
// Siblings -- the same question, answered three other ways:
//   AnagramCounting -- counts each character in a fixed array of 256 slots: O(n)
//   AnagramHashing  -- the same counting, kept in a HashMap from character to count: O(n)
//   AnagramSorting  -- sorts both strings' characters and compares them: O(n log n)

public final class AnagramSubStr {
  // Time Complexity: O(n^2), for each of n characters a search of str2 and a new string
  // built without the character, each O(n)
  // Space Complexity: O(n), the shortened copy of str2 held at any one time

  public static boolean areAnagrams(String str1, String str2) {
    if (str1.length() != str2.length()) { // LENGTH: different lengths are never anagrams
      return false;
    }
    for (char c : str1.toCharArray()) { // WALK: each character of str1
      int index = str2.indexOf(c); // FIND: a copy of it still in str2
      if (index != -1) {
        String first = str2.substring(0, index);
        String second = str2.substring(index + 1);
        str2 = first + second; // CROSS OFF: str2 without that one copy
      } else {
        return false; // MISSING: str2 has run out of this character
      }
    }
    return str2.isEmpty(); // RETURN: every character of str2 was matched
  }

  public static void main(String[] args) {
    for (String[] pair : pairs()) {
      String w1 = pair[0];
      String w2 = pair[1];
      System.out.println(areAnagrams(w1, w2));
    }
  }

  private static String[][] pairs() {
    return new String[][] {
        { "anagramm", "nagaramm" }, // true
        { "aaca", "aca" }, // false
        { "apple", "papel" }, // true
        { "carrot", "tarroc" }, // true
        { "hello", "llloh" }, // false
    };
  }
}
