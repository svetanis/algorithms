package com.svetanis.algorithms.string.bitvector;

import java.util.Optional;

// First repeated character, found with an int used as a row of 26 yes/no flags
//
// Input: a string of lowercase letters a-z; any other character is outside what the flags
// can hold.
// Returns: the index of the first character that has already appeared earlier in the
// string -- the position where the repeat is noticed -- or an empty Optional when every
// character is different. "abcfdeacf" gives 6, the second 'a'.
//
// The one idea: one flag per letter, and the flags live in the bits of one int. Bit c of
// checker is 1 when the letter number c ('a' is 0, 'z' is 25) has been seen. Read the
// next character: if its bit is already 1 it is a repeat and the index is the answer;
// otherwise set the bit and go on. The first time a bit is found set is the first
// repeat, because the string is read left to right.
//
// Time: O(n) -- one pass, two bit operations per character.
// Space: O(1) -- one int, however long the string is.
//
// Siblings: UniqueCharsBitVector asks the same question for a whole list of strings and
// answers only whether any letter repeats. LeetCode 2351 (First Letter to Appear Twice)
// is the same walk returning the letter instead of its index.

public final class FirstRepeatedChar {

  public static Optional<Integer> firstRepeated(String str) {
    int n = str.length();

    int checker = 0;                           // FLAGS: bit c is 1 once letter c was seen
    for (int i = 0; i < n; ++i) {
      int c = str.charAt(i) - 'a';
      int bit = checker & (1 << c);            // TEST: is letter c already in the set
      if (bit > 0) {
        return Optional.of(i);                 // RETURN: seen before, so this is the first repeat
      }
      checker |= (1 << c);                     // SET: add letter c to the set
    }
    return Optional.empty();                   // RETURN: no letter appeared twice
  }

  public static void main(String[] args) {
    System.out.println(firstRepeated("abcfdeacf")); // Optional[6]
    System.out.println(firstRepeated("abc"));       // Optional.empty
    System.out.println(firstRepeated("abca"));      // Optional[3]
  }
}