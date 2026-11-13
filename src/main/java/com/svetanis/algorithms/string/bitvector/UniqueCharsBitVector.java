package com.svetanis.algorithms.string.bitvector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Keep the strings whose letters are all different, using an int as a row of 26 flags
//
// Input: a list of strings of lowercase letters a-z; any other character is outside what
// the flags can hold.
// Returns: the strings of the list in which no letter appears twice, in their original
// order.
//
// The one idea: one flag per letter, and the flags live in the bits of one int. Bit c of
// checker is 1 when the letter number c ('a' is 0, 'z' is 25) has been seen in this
// string. Read the characters left to right: a bit that is already 1 means a repeat and
// the string is dropped; otherwise set the bit and go on. The length guard below is
// looser than needed -- a string longer than 26 letters must repeat one anyway, and the
// walk finds it.
//
// Time: O(total length of the strings) -- one pass over each string, two bit operations
// per character, and at most 26 characters are read before a repeat is certain.
// Space: O(1) besides the answer -- one int per string.
//
// Siblings: FirstRepeatedChar -- the same flags, for one string, returning where the
// first repeat is.

public final class UniqueCharsBitVector {

  private static final int N = 256;

  public static List<String> unique(List<String> list) {
    List<String> result = new ArrayList<>();
    for (String str : list) {
      if (isUnique(str)) {
        result.add(str);                       // KEEP: no letter repeats
      }
    }
    return result;
  }

  private static boolean isUnique(String str) {
    int n = str.length();

    if (n > N) {
      return false;                            // RETURN false: too long to have all-different letters
    }

    int checker = 0;                           // FLAGS: bit c is 1 once letter c was seen
    for (int i = 0; i < n; ++i) {
      int c = str.charAt(i) - 'a';
      int bit = checker & (1 << c);            // TEST: is letter c already in the set
      if (bit > 0) {
        return false;                          // RETURN false: seen before
      }
      checker |= (1 << c);                     // SET: add letter c to the set
    }
    return true;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("abcde", "hello", "apple", "kite", "padle");
    for (String str : unique(list)) {
      System.out.println(str); // abcde, then kite, then padle
    }
  }
}