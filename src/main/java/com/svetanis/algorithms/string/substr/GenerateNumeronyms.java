package com.svetanis.algorithms.string.substr;

import java.util.ArrayList;
import java.util.List;

// No LeetCode problem: generate all the numeronyms of a word.
//
// Input: a word str of length n. A numeronym keeps the first and the last letter and replaces one
// stretch of at least two of the inner letters with the number of letters it replaced ("nailed"
// gives "n4d" and "na3d"). Returns every numeronym, one for each choice of stretch; a word of 3
// letters or fewer has no stretch of two inner letters, so the answer is empty.
//
// The one idea: a numeronym is fixed by the length len of the stretch and the index i where it
// starts. The first letter stays, so i >= 1, and the last letter stays, so the stretch ends
// before index n - 1, i.e. i + len <= n - 1. Two loops over len (2 up to n - 2) and i (1 up to
// n - 1 - len) visit each choice once; the numeronym is the front str[0 .. i - 1], then len,
// then the back str[i + len ..].
//
// Time: O(n^3) -- (n - 3) * (n - 2) / 2 numeronyms, each built in O(n).
// Space: O(n^3) -- the answer holds all of them.

public final class GenerateNumeronyms {

  public static List<String> numeronyms(String str) {
    int n = str.length();
    List<String> list = new ArrayList<>();

    if (n <= 3) {
      return list; // BASE: no stretch of two inner letters
    }

    // first and last chars always included: len = 2 : n - 2
    for (int len = 2; len <= n - 2; len++) { // LENGTH: how many letters the number replaces
      for (int i = 1; i <= n - 1 - len; i++) { // START: where the stretch begins
        String ss = str.substring(0, i) + len + str.substring(i + len);
        list.add(ss); // RECORD: front, the number, back
      }
    }
    return list;
  }

  public static void main(String[] args) {
    String str = "nailed";
    System.out.println(numeronyms(str)); // [n2led, na2ed, nai2d, n3ed, na3d, n4d]
    System.out.println(numeronyms("abc")); // []
  }
}
