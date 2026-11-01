package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.List;

// Every binary string that fits a pattern (not a LeetCode problem)
//
// Input: a string of '0', '1' and '?'. Returns every binary string made by replacing each '?' with
// 0 or 1: "1?0" gives [100, 110]; in order, smaller first. Assumed: the pattern has at most
// about 20 question marks.
//
// The one idea: BinaryStringsOfLengthN.withArrayOverwrite, where only the '?' positions branch: a
// fixed 0 or 1 is passed over. The pattern itself is the char[] of slots; one call decides slot
// index. At a '?' write 0, recurse, write 1, recurse -- then put the '?' back. That restore is
// needed here, unlike in withArrayOverwrite: the '?' is what marks the position as one to branch
// on, and the next time a call reaches it (along the caller's other branch) it must still read '?'.
//
// Time: O(n * 2^q) -- q question marks, so 2^q strings, each copied in n steps.
// Space: O(n) besides the output -- the recursion depth.
//
// Siblings: BinaryStringsOfLengthN.withArrayOverwrite is the same walk with every position a '?'.

public final class AllBinaryStringsFromGivenPattern {
  public static List<String> generate(String pattern) {
    List<String> strings = new ArrayList<>();
    generate(pattern.toCharArray(), 0, strings);
    return strings;
  }

  private static void generate(char[] chars, int index, List<String> strings) {
    if (index == chars.length) { // BASE: every position decided
      strings.add(String.valueOf(chars)); // RECORD: a copy, chars keeps changing
      return;
    }

    if (chars[index] == '?') { // BRANCH: only a '?' has a choice
      chars[index] = '0'; // TAKE 0
      generate(chars, index + 1, strings);
      chars[index] = '1'; // TAKE 1: overwrites the 0
      generate(chars, index + 1, strings);
      chars[index] = '?'; // RESTORE: the caller's other branch must see a '?' here again
    } else {
      generate(chars, index + 1, strings); // PASS OVER: a fixed 0 or 1, nothing to choose
    }
  }

  public static void main(String[] args) {
    System.out.println(generate("1?0")); // [100, 110]
    // [10000101, 10001101, 10100101, 10101101, 11000101, 11001101, 11100101, 11101101]
    System.out.println(generate("1??0?101"));
  }
}
