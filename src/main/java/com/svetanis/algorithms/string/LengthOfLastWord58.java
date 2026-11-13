package com.svetanis.algorithms.string;

// 58. Length of Last Word
//
// Input: a string s of English letters and spaces, with at least one word. A word is a run of
// letters with no space inside it. Returns the length of the last word. On LeetCode:
// 1 <= s.length <= 10^4.
//
// The one idea: look only at the end of the string. Spaces after the last word are not part of
// any word, so first walk left past them; end is then the last letter of the last word. Then
// walk left again until the next space (or the start of the string): start stops one before the
// word's first letter, so the length is end - start.
//
// Time: O(n) -- both pointers only move left, over each character at most once.
// Space: O(1).

public final class LengthOfLastWord58 {

  private static int len(String s) {
    int end = s.length() - 1;
    while (end >= 0 && s.charAt(end) == ' ') { // SKIP: spaces after the last word
      end--;
    }
    int start = end;
    while (start >= 0 && s.charAt(start) != ' ') { // WALK: left over the letters of the word
      start--;
    }
    return end - start; // RETURN: start is the space before the word, or -1
  }

  public static void main(String[] args) {
    System.out.println(len("Hello World")); // 5
    System.out.println(len("    fly me    to   the moon  ")); // 4
    System.out.println(len("luffy is still joyboy")); // 6
  }
}