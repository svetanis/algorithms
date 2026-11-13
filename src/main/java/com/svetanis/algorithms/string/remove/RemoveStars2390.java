package com.svetanis.algorithms.string.remove;

// 2390. Removing Stars From a String
//
// Input: a string s of lowercase letters and '*'. A star removes the closest non-star letter to
// its left, and the star itself. Returns the string after every star has been used. On LeetCode:
// 1 <= s.length <= 10^5, s is lowercase English letters and stars, and every star has a letter to
// remove.
//
// The one idea: the builder sb is the letters kept so far, and the closest letter to the left of
// a star is always sb's last one. Read s left to right; a letter is appended, a star deletes sb's
// last character. sb works as a stack. The length check only guards a star with nothing before
// it, which LeetCode does not allow.
//
// Time: O(n) -- each character is read once; each letter is appended once and deleted at most once.
// Space: O(n) -- the builder.
//
// Siblings: RemoveAllAdjacentDuplicates and RemoveAllAdjacentDuplicates1209 delete the last
// kept letter when the new letter matches it, instead of when it is a star.

public final class RemoveStars2390 {

  public static String remove(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '*') {
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length() - 1); // CANCEL: the star removes the last kept letter
        }
      } else {
        sb.append(c); // KEEP: for now
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(remove("leet**cod*e")); // lecoe
    System.out.println(remove("erase*****")); // ""
  }
}
